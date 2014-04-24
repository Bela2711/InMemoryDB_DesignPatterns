import java.util.ArrayList;
import java.util.List;

// this class manages a list of Movies
// id is an ID a next new movie will hold
// generates unique id for each movie
// this class acts as an ORIGINATOR in Memento pattern 
public class Inventory implements InventoryInterface {
	private List<Movie> movies;
	private int id;

	public Inventory() {
		movies = new ArrayList<Movie>();
		id = 0;
	}

	@Override
	public void addNewMovie(String name, double price) {
		id = id + 1;
		Movie newMovie = new Movie(name, price, id);
		movies.add(newMovie);
	}

	@Override
	public double findPrice(int id) {
		int found = find(id);
		if (found != -1)
			return movies.get(found).getPrice();
		return -1;
	}

	@Override
	public double findPrice(String name) {
		int found = find(name);
		if (found != -1)
			return movies.get(found).getPrice();
		return -1;
	}

	@Override
	public int findQuantity(int id) {
		int found = find(id);
		if (found != -1)
			return movies.get(found).getQuantity();
		return -1;
	}

	@Override
	public int findQuantity(String name) {
		int found = find(name);
		if (found != -1)
			return movies.get(found).getQuantity();
		return -1;
	}

	@Override
	public int changePrice(String name, int price) {
		int found = find(name);
		if (found != -1) {
			movies.get(found).setPrice(price);
			return 1;
		} else
			return -1;
	}

	@Override
	public void addNewCopy(String name, int numberOfCopies) {
		int found = find(name);
		if (found != -1) {
			Movie movieObject = movies.get(found);
			movieObject.setQuantity(movieObject.getQuantity() + numberOfCopies);
		}
	}

	@Override
	public int sell(String name) {
		int found = find(name);
		if (found != -1) {
			Movie movieObject = movies.get(found);
			movieObject.setQuantity(movieObject.getQuantity() - 1);
			return 1;
		}
		return -1;
	}

	// stores current state of inventory
	public InventoryMemento createMemento() {
		return new InventoryMemento(movies, new Integer(id));
	}

	// restores back inventory to the memento object passed to it
	// uses deep copy
	public void restoreMemento(InventoryMemento mementoObject) {
		for (Movie movie : mementoObject.getMoviesState()) {
			Movie newMovie = new Movie(movie.getName(), movie.getPrice(),
					movie.getId());
			movies.add(newMovie);
		}
		id = mementoObject.getIdState();
	}

	private int find(String name) {
		for (Movie value : movies) {
			if (value.getName().toLowerCase().equalsIgnoreCase(name)) {
				return movies.indexOf(value);
			}
		}
		return -1;

	}

	private int find(int id) {
		for (Movie value : movies) {
			if (value.getId() == id) {
				return movies.indexOf(value);
			}
		}
		return -1;
	}
}
