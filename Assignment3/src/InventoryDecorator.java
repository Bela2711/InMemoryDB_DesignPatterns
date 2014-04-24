import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

// This class is a DECORATOR and provides additional functionality 
// of saving commands performed on inventory object.
// This class also acts as a CARETAKER in Memento pattern
// and INVOKER in Command pattern.
public class InventoryDecorator implements InventoryInterface {

	private InventoryInterface inventory;
	// keeps check on number of commands after a last memento is saved
	private int numberOfCommands;
	private InventoryMemento inventoryMemento;
	private Command recentCommand;

	public InventoryDecorator(InventoryInterface inventoryObject) {
		inventory = inventoryObject;
		numberOfCommands = 0;
		recentCommand = new AddCommand();
	}

	public InventoryDecorator() {
		inventory = new Inventory();
		numberOfCommands = 0;
		recentCommand = new AddCommand();
	}

	@Override
	public void addNewMovie(String name, double price) {
		recentCommand = new AddCommand(name, price);
		inventory.addNewMovie(name, price);
		serializeCommand();
		checkCommand();
	}

	@Override
	public double findPrice(int id) {
		return inventory.findPrice(id);
	}

	@Override
	public double findPrice(String name) {
		return inventory.findPrice(name);
	}

	@Override
	public int findQuantity(int id) {
		return inventory.findQuantity(id);
	}

	@Override
	public int findQuantity(String name) {
		return inventory.findQuantity(name);
	}

	@Override
	public int changePrice(String name, int price) {
		recentCommand = new ChangePriceCommand(name, price);
		serializeCommand();
		checkCommand();
		return inventory.changePrice(name, price);
	}

	@Override
	public void addNewCopy(String name, int numberOfCopies) {
		recentCommand = new AddNewCopyCommand(name,numberOfCopies);
		serializeCommand();
		checkCommand();
		inventory.addNewCopy(name,numberOfCopies);
	}

	@Override
	public int sell(String name) {
		recentCommand = new SellCommand(name);
		serializeCommand();
		checkCommand();
		return inventory.sell(name);

	}

	// maintains two files to store memento
	// if one of the file is empty then it is used to store the memento
	// writes memento in safely manner without removing the old one before
	@Override
	public InventoryMemento createMemento() {
		// Inventory in = (Inventory) inventory;
		// creating memento for Inventory
		inventoryMemento = inventory.createMemento();
		FileOutputStream fileOut;
		// if file1 is empty then write that file
		// and then clear the contents of the file2
		if (checkFileEmpty("D:/inventoryMementoFile1.ser")) {
			try {
				// write file1
				fileOut = new FileOutputStream("D:/inventoryMementoFile1.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(inventoryMemento);
				out.close();
				fileOut.close();
				// clear file2
				fileOut = new FileOutputStream("D:/inventoryMementoFile2.ser");
				PrintWriter writer = new PrintWriter(fileOut);
				writer.close();
				fileOut.close();
			} catch (IOException i) {
				i.printStackTrace();
			}
		}
		// else file2 is empty then write that file
		// and then clear the contents of the file1
		else {
			try {
				// write file2
				fileOut = new FileOutputStream("D:/inventoryMementoFile2.ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(inventoryMemento);
				out.close();
				fileOut.close();
				// clear file1
				fileOut = new FileOutputStream("D:/inventoryMementoFile1.ser");
				PrintWriter writer = new PrintWriter(fileOut);
				writer.close();
				fileOut.close();
			} catch (IOException i) {
				i.printStackTrace();
			}
		}
		return inventoryMemento;
	}

	// restores back inventory to the memento saved in a file
	private void restoreMemento() {
		// if file1 is not empty , restore from that file
		if (!checkFileEmpty("D:/inventoryMementoFile1.ser")) {
			try {
				FileInputStream fileIn = new FileInputStream(
						"D:/inventoryMementoFile1.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				inventoryMemento = (InventoryMemento) in.readObject();
				in.close();
				fileIn.close();
			} catch (IOException i) {
				i.printStackTrace();
				return;
			} catch (ClassNotFoundException c) {
				c.printStackTrace();
				return;
			}
		}
		// else restore from file2
		else {
			try {
				FileInputStream fileIn = new FileInputStream(
						"D:/inventoryMementoFile2.ser");
				ObjectInputStream in = new ObjectInputStream(fileIn);
				inventoryMemento = (InventoryMemento) in.readObject();
				in.close();
				fileIn.close();
			} catch (IOException i) {
				i.printStackTrace();
				return;
			} catch (ClassNotFoundException c) {
				c.printStackTrace();
				return;
			}
		}
		inventory.restoreMemento(inventoryMemento);
	}

	// restores back inventory to the latest state by restoring memento
	// and deserializing the commands stored in a file and executing them on the
	// restored memento
	public void restore() {
		restoreMemento();
		// deserializing and executing commands from command file
		if (!checkFileEmpty("D:/inventoryCommandFile.ser")) {
			try {
				FileInputStream fileIn = new FileInputStream(
						"D:/inventoryCommandFile.ser");
				while (recentCommand != null) {
					ObjectInputStream in = new ObjectInputStream(fileIn);
					recentCommand = (Command) in.readObject();
					recentCommand.execute(inventory);
					numberOfCommands++;
				}
				fileIn.close();
			} catch (IOException i) {
				return;
			} catch (ClassNotFoundException c) {
				c.printStackTrace();
				return;
			}
		}
	}

	// this method gets called after specific number of commands are performed
	// on inventory
	// creates a memento of inventory and hence
	// clears the contents of commandfile
	private void store() {
		createMemento();

		// deleting previous commands in the command file
		FileOutputStream fileOut;
		try {
			fileOut = new FileOutputStream("D:/inventoryCommandFile.ser");
			PrintWriter writer = new PrintWriter(fileOut);
			writer.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// appends command in a commandfile
	private void serializeCommand() {
		try {
			FileOutputStream fileOut = new FileOutputStream(
					"D:/inventoryCommandFile.ser", true);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(recentCommand);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	// if numberOfCommands exceeds specific number of commands
	// here for testing purpose its 3
	// while executing any operation-
	// 1. Command object is created
	// 2. command gets executed
	// 3. increments a count of number of commands
	// 4. if it exceeds a limit it calls store() method
	private void checkCommand() {
		numberOfCommands++;
		if (numberOfCommands > 2) {
			store();
			numberOfCommands = 0;
		}
	}

	// restores back inventory to the memento passed to this method
	@Override
	public void restoreMemento(InventoryMemento memento) {
		inventoryMemento = memento;
		restoreMemento();
	}

	private boolean checkFileEmpty(String filename) {
		String readLine = "Not empty purposely";
		BufferedReader br;
		try {
			File file = new File(filename);
			if (file.exists() && !file.isDirectory()) {
				br = new BufferedReader(new FileReader(filename));
				readLine = br.readLine();
				br.close();
			} else {
				readLine = null;
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (readLine == null) {
			return true;
		}
		return false;
	}

}