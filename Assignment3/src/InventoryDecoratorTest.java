import static org.junit.Assert.*;
import org.junit.Test;

//This class tests InventoryDecorator class and its methods.
//As decorator internally calls methods of Inventory class, it also gets tested here
public class InventoryDecoratorTest {

	// This method tests-
	// 1. Creating inventory decorator object
	// 2. Adding new movies to inventory
	// 3. Finding price of a movie by movie name or movie id
	// 4. Finding number of copies of a movie by movie name or movie id
	// 5. Selling a movie
	// 6. Changing price of a movie

	// This method performs total of 5 operations on InventoryDecoratorObject.
	// A memento is saved after each 3 operations.(For testing purpose a small
	// number 3 is used)
	// After running this operation a memento of inventory till 3 operations is
	// stored.
	// Operations 4 and 5 will be stored as commands in Command File

	@Test
	public void testCreateInventory() {
		double price;
		int quantity;
		InventoryInterface inventoryObject = new Inventory();
		InventoryDecorator inventoryDecorator = new InventoryDecorator(
				inventoryObject);
		// add a movie
		inventoryDecorator.addNewMovie("A Movie", 500.22); // 1
		inventoryDecorator.addNewMovie("A New Movie", 550.22); // 2
		// finding price by passing an ID of a movie
		price = inventoryDecorator.findPrice(2);
		assertEquals(price, 550.22, 0);
		// finding price by passing name of a movie
		price = inventoryDecorator.findPrice("A Movie");
		assertEquals(price, 500.22, 0);
		// finding quantity by passing ID of a movie
		quantity = inventoryDecorator.findQuantity(2);
		assertEquals(quantity, 1);
		// finding quantity by passing name of a movie
		quantity = inventoryDecorator.findQuantity("A Movie");
		assertEquals(quantity, 1);
		// adding a copy of a movie
		inventoryDecorator.addNewCopy("A Movie", 1); // 3 - memento will be
														// saved
														// after this operation.
		quantity = inventoryDecorator.findQuantity("A Movie");
		assertEquals(quantity, 2);
		inventoryDecorator.changePrice("A New Movie", 800); // 4
		price = inventoryDecorator.findPrice("a new movie");
		assertEquals(price, 800, 0);
		inventoryDecorator.sell("A Movie"); // 5
		quantity = inventoryDecorator.findQuantity("A Movie");
		assertEquals(quantity, 1);
		assert (true);
	}

	// This method tests-
	// 1. Restoring back inventory to the memento saved
	// 2. Performing saved commands on the restored inventory object
	// 3. Performing new commands on the restored inventory

	// When restore() is called on Inventory Decorator object , a memento is
	// restored
	// and commands in commandFile are executed on that memento.
	// operation 6 will be then executed and again a memento will be stored
	// as it exceeds the limit on number of commands in commandFile.
	// Then operation 7 will be performed and a command is stored in command
	// file.
	@Test
	public void testRestore() {
		double price;
		int quantity;
		InventoryDecorator inventoryDecorator = new InventoryDecorator();
		// restoring to the saved memento
		inventoryDecorator.restore();
		inventoryDecorator.addNewMovie("Another New Movie", 700); // 6, after this operation
																  // command file gets cleared
		price = inventoryDecorator.findPrice("another new movie");
		assertEquals(price, 700, 0);
		price = inventoryDecorator.findPrice("a new movie");
		assertEquals(price, 800, 0);
		inventoryDecorator.addNewCopy("Another new Movie", 1); // 7
		quantity = inventoryDecorator.findQuantity("Another New Movie");
		assertEquals(quantity, 2);
	}
}
