// maintains data for each movie
public class Movie implements java.io.Serializable {
	private String name;
	private double price;
	private int id;
	private int quantity;

	Movie(String newName, double priceValue, int newId) {
		name = newName;
		price = priceValue;
		id = newId;
		quantity = 1;
	}

	Movie(String newName, double priceValue, int quantityValue, int newId) {
		name = newName;
		price = priceValue;
		id = newId;
		quantity = quantityValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
