package my.vaadin.Undined.backend;

import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;

//Class that holds all of the information of a restaurant's restaurant
//														^
//											Lets play spot the ctrl+f replace
public class Restaurant implements Serializable, Cloneable {

    private Long id;

    private String restaurantName = "";
    private String foodType = "";
    private String location = "";
    private String hoursOfBusiness = "";
    
    //Get and set ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    
    public String getHoursOfBusiness(){
    	return hoursOfBusiness;
    }
    
    public void setHoursOfBusiness(String hoursOfBusiness){
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
