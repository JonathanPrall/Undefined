package cs.dal.csci3130.undined.backend;

import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;

/**
 * A simple DTO for the address book example.
 *
 * Serializable and cloneable Java Object that are typically persisted
 * in the database and can also be easily converted to different formats like JSON.
 */

//Class that holds all of the information of a restaurant's manager
public class Manager implements Serializable, Cloneable {

    private Long id;

    private String managerName = "";
    private Long restaurantId;

    //Get and set ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    //Get and set manager name
    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerName(){
    	return managerName;
    }
    
    
    //Get and set restaurant Id
    public Long getRestaurantId(){
    	return restaurantId;
    }
    
    public void setRestaurantId(Long restaurantId){
    	this.restaurantId = restaurantId;
    }

    @Override
    public Manager clone() throws CloneNotSupportedException {
        try {
            return (Manager) BeanUtils.cloneBean(this);
        } catch (Exception ex) {
            throw new CloneNotSupportedException();
        }
    }

    @Override
    public String toString() {
        return managerName;
    }

}
