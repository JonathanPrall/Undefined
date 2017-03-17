package cs.dal.csci3130.undined.newbackend;

import java.io.Serializable;

import org.apache.commons.beanutils.BeanUtils;

//Class for storing information for an item on a restaurant's menu
public class MenuItem implements Serializable, Cloneable
{
	private Long id;

	private String name = "";
	private String description = "";
	private float price = Float.POSITIVE_INFINITY;
	//private picture

	public MenuItem() {
	}
	//Creates the menu item with all information
	public MenuItem(String name, String description, float price){
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	@Override
    public MenuItem clone() throws CloneNotSupportedException {
        try {
            return (MenuItem) BeanUtils.cloneBean(this);
        } catch (Exception ex) {
            throw new CloneNotSupportedException();
        }
    }
}
