// provides implementation for Command interface
// provides execution for add new copy command

public class AddNewCopyCommand implements Command {

	private String name;
	int numberOfCopies;

	public AddNewCopyCommand(String movieName, int number) {
		name = movieName;
		numberOfCopies= number;
	}

	@Override
	public void execute(InventoryInterface inventory) {
		inventory.addNewCopy(name,numberOfCopies);
	}

}
