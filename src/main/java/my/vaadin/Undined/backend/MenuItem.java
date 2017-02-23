package my.vaadin.Undined.backend;

//Class for storing information for an item on a restaurant's menu
public class MenuItem
{
	private String name = "";
	private String description = "";
	private float price = Float.POSITIVE_INFINITY;
	//private picture
	
	//Creates the menu item with all information
	public MenuItem(String name, String description, float price){
		this.name = name;
		this.description = description;
		this.price = price;
	}
	
	//Get and set name
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	//Get and set description
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	//Get and set price
	public float getPrice(){
		return price;
	}
	
	public void setPrice(float price){
		this.price = price;
	}
	
}
