package cs.dal.csci3130.undined.domain;

import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;

//Class that holds all of the information of a restaurant's restaurant
//														^
//											Lets play spot the ctrl+f replace
public class Restaurant implements Serializable, Cloneable {

    private long id;
    private long menuId;
    
    private String restaurantName = "";
    private String foodType = "";
    private String location = "";
    private String hoursOfBusiness = "";
    
    private int status;
   
    public Restaurant() {
    }
    
    public Restaurant(long id, String name, String foodType, String location, String hoursOfBusiness) {
    	this.setId(id);
    	this.setRestaurantName(name);
    	this.setFoodType(foodType);
    	this.setLocation(location);
    	this.setHoursOfBusiness(hoursOfBusiness);
    }
    
    public long getMenuId()
	{
		return menuId;
	}

	public void setMenuId(long menuId)
	{
		this.menuId = menuId;
	}

	// Get and set status 
	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	//Get and set ID
    public void setId(long id) {
        this.id = id;
    }

    
    public long getId() {
		return id;
	}


	//Get and set restaurant name
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
    
    public String getRestaurantName(){
    	return restaurantName;
    }
    
    
    //Get and set type of food
    public String getFoodType(){
    	return foodType;
    }
    
    public void setFoodType(String foodType){
    	this.foodType = foodType;
    }
    
    
    //Get and set location
    public String getLocation(){
    	return location;
    }
    
    public void setLocation(String location){
    	this.location = location;
    }

    // Get and set hoursOfBusiness
    public String getHoursOfBusiness() {
		return hoursOfBusiness;
	}


	public void setHoursOfBusiness(String hoursOfBusiness) {
		this.hoursOfBusiness = hoursOfBusiness;
	}

    
    @Override
    public Restaurant clone() throws CloneNotSupportedException {
        try {
            return (Restaurant) BeanUtils.cloneBean(this);
        } catch (Exception ex) {
            throw new CloneNotSupportedException();
        }
    }

    @Override
    public String toString() {
        return restaurantName;
    }

}
