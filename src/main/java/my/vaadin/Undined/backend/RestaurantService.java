package my.vaadin.Undined.backend;

import org.apache.commons.beanutils.BeanUtils;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Separate Java service class.
 * Backend implementation for the address book application, with "detached entities"
 * simulating real world DAO. Typically these something that the Java EE
 * or Spring backend services provide
 */
// Backend service class. This is just a typical Java backend implementation
// class and nothing Vaadin specific.
public class RestaurantService {

	//Create fake data
	static String[] restaurantName = {
			"Bione", "Song's Korean Restaurant", "Wasabi House",
			"Thai Ivory Cuisine", "Blue Olive Greek", "McDonald's"  
	};
	static String[] foodType = {
			"Asia Cuisine", "Europe Cuisine", "Fast food"
	};
	static String[] location = {
			"6300 Quinpool Rd",
			"6249 Quinpool Rd",
			"6403 Quinpool Rd",
			"6324 Quinpool Rd",
			"6196 Quinpool Rd",
			"6303 Quinpool Rd"
	};
	
	
	
    private static RestaurantService instance;

    public static RestaurantService createService() {
    	if(instance == null) {
    		
    		final RestaurantService restaurantService = new RestaurantService();
    		for (int i = 0; i < 6; i++) {
    			Restaurant restaurant = new Restaurant();
    			restaurant.setRestaurantName(restaurantName[i]);
    			if (i < 4) {
    				restaurant.setFoodType(foodType[0]);
    			} else {
    				restaurant.setFoodType(foodType[i-3]);
    			}
    			restaurant.setLocation(location[i]);
    			restaurantService.save(restaurant);
    		}
    		instance = restaurantService;
    	}
    	return instance;
    }

    private HashMap<Long, Restaurant> restaurants = new HashMap<>();
    private long nextId = 0;

    //I think this is the search box
    public synchronized List<Restaurant> findAll(String stringFilter) {
        ArrayList<Restaurant> arrayList = new ArrayList<Restaurant>();
        
        //For each restaurant
        for (Restaurant restaurant : restaurants.values()) {
            try {
            	//If there is nothing in the search box
            	//or
            	//The Restaurant.toString() contains the text in the search box (not case sensitive)
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || restaurant.toString().toLowerCase()
                                .contains(stringFilter.toLowerCase());
                //Add it to the list that will be returned
                if (passesFilter) {
                    arrayList.add(restaurant.clone());
                }
            
            //If error, log it
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(RestaurantService.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
        
        //Sort the created list
        Collections.sort(arrayList, new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant o1, Restaurant o2) {
                return (int) (o2.getId() - o1.getId());
            }
        });
        
        //Return it
        return arrayList;
    }

    public synchronized long count() {
        return restaurants.size();
    }

    public synchronized void delete(Restaurant value) {
        restaurants.remove(value.getId());
    }

    public synchronized void save(Restaurant entry) {
        if (entry.getId() == 0) {
            entry.setId(nextId++);
        }
        try {
            entry = (Restaurant) BeanUtils.cloneBean(entry);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        restaurants.put(entry.getId(), entry);
    }

}
