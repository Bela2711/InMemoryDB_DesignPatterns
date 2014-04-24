// Acts as interface in command pattern
//interface for all command classes
public interface Command extends java.io.Serializable {
	public void execute(InventoryInterface inventory);
}
