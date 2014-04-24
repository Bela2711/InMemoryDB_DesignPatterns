// provides implementation for Command interface
// provides execution for add command
public class AddCommand implements Command {
	private String name;
	private double price;

	public AddCommand(String nameOfMovie, double priceValue) {
		name = nameOfMovie;
		price = priceValue;
	}

	public AddCommand() {
	}

	@Override
	public void execute(InventoryInterface inventory) {
		inventory.addNewMovie(name, price);
	}
}
