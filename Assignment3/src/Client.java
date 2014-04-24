public class Client {

	public static void main(String[] args) {
		InventoryInterface inventoryObject= new Inventory();
		InventoryDecorator inventoryDecorator= new InventoryDecorator(inventoryObject);
		//inventoryDecorator.restore();
		//inventoryDecorator.addNewMovie("Wake Up Sid", 500);
		//inventoryDecorator.addNewMovie("Dil Chahta hai", 550);
		//inventoryDecorator.addNewMovie("Shaala", 450);		
		//inventoryDecorator.addNewMovie("2 states", 450);		
		//inventoryDecorator.addNewMovie("highway", 600);
		//inventoryDecorator.addNewMovie(":):)", 649);
		//inventoryDecorator.addNewMovie(":*:*", 859);
		//inventoryDecorator.restore();
		//System.out.println(inventoryDecorator.findPrice(2));
		//System.out.println(inventoryDecorator.findQuantity(2));
		//inventoryDecorator.changePrice(3, 329);
		//System.out.println(inventoryDecorator.findPrice(3));
		//inventoryDecorator.addNewCopy("2 states");
		//inventoryDecorator.addNewCopy("2 states");		
		//inventoryDecorator.addNewCopy(2);
		//System.out.println(inventoryDecorator.addNewCopy("Dil Chahta hai",2));
		//inventoryDecorator.sell("Dil Chahta hai");
		//System.out.println(inventoryDecorator.findPrice("Dil Chahta hai"));
		//InventoryMemento memento = inventoryDecorator.createMemento();
		//System.out.println(memento.getMoviesState().get(1).getPrice());
		//inventoryDecorator.restore();
		//System.out.println(inventoryDecorator.findQuantity("2 states"));
		//System.out.println(inventoryDecorator.display());
		//inventoryDecorator.display();
		
		/*inventoryDecorator.addNewMovie("A Movie", 500);
		inventoryDecorator.addNewMovie("A New Movie", 550);		
		inventoryDecorator.addNewCopy("A Movie");
		inventoryDecorator.changePrice("A New Movie", 800);
		inventoryDecorator.sell("A Movie");
		*/	
		
		//inventoryDecorator.restore();
		//inventoryDecorator.addNewMovie("Another New Movie", 700);
		//inventoryDecorator.changePrice("Another New Movie", 858);
		
		//inventoryDecorator.changePrice("Another New Movie", 555);
		//inventoryDecorator.addNewMovie("A Forth Movie", 777);
		//double price = inventoryDecorator.findPrice("another new movie");
			
		//System.out.println(inventoryDecorator.toString());
	}
}
