import java.util.ArrayList;
import java.util.List;

// this is a memento class that provides functionality to maintain
// copy of inventory
public class InventoryMemento implements java.io.Serializable{
	
	private List <Movie> moviesState;
	private int idState;
	
	protected InventoryMemento(List<Movie> movies , int id){
				setMoviesState(makeNewList(movies));
				setIdState(id);
	}

	private List<Movie> makeNewList(List<Movie> movies) {
		List <Movie> newListOfMovies = new ArrayList<Movie>();
		for (Movie movie : movies) {
			Movie newMovie= new Movie(movie.getName(), movie.getPrice(),movie.getId());
			newListOfMovies.add(newMovie);
		}
		return newListOfMovies;
	}

	public List <Movie> getMoviesState() {
		return moviesState;
	}

	public void setMoviesState(List<Movie> moviesState) {
		this.moviesState = moviesState;
	}

	public int getIdState() {
		return idState;
	}

	public void setIdState(int idState) {
		this.idState = idState;
	}

}
