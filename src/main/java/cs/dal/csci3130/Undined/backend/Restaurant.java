package cs.dal.csci3130.Undined.backend;

import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;

//Class that holds all of the information of a restaurant's restaurant
//														^
//											Lets play spot the ctrl+f replace
public class Restaurant implements Serializable, Cloneable {

    private long id;

    private String restaurantName = "";
    private String foodType = "";
    private String location = "";
    private String hoursOfBusiness = "";
    
   

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
