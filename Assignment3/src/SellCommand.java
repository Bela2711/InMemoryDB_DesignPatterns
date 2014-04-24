// provides implementation for Command interface
// provides execution for sell command

public class SellCommand implements Command {

	private String name;

	public SellCommand(String movieName) {
		name = movieName;
	}

	@Override
	public void execute(InventoryInterface inventory) {
		inventory.sell(name);
	}
}
