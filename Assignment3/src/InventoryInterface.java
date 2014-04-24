// Acts as interface in decorator pattern
// Inventory and InventoryDecorator implement this interface
public interface InventoryInterface {
	public void addNewMovie(String name, double price);

	public int sell(String name);

	public double findPrice(int id);

	public double findPrice(String name);

	public int findQuantity(String name);

	public int findQuantity(int id);

	public int changePrice(String name, int price);

	public void addNewCopy(String name, int numberOfCopies);

	public InventoryMemento createMemento();

	public void restoreMemento(InventoryMemento memento);
}
