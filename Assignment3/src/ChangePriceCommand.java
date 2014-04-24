// provides implementation for Command interface
// provides execution for change price command

public class ChangePriceCommand implements Command {
	
	private String name;
	private int price;

	public ChangePriceCommand(String nameOfMovie, int priceOfMovie) {
		name = nameOfMovie;
		price = priceOfMovie;
	}

	@Override
	public void execute(InventoryInterface inventory) {
		inventory.changePrice(name, price);
	}

}
