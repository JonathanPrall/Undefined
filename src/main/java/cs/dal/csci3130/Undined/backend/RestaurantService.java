package cs.dal.csci3130.Undined.backend;

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

	private static long nextId = 0;
	//Create fake data
	static String[] namePrefixes = {"Mc", "The ", "Vinny's ", "After ", "Jonny's ", "Silver ", "Italian ", "Taco ", "Burger ", "Kentucky Fried "};
    static String[] nameSuffixes = {"Ronalds", "Donalds", "Wendys", "Pizza", "Saves", "Bell", "Trump", "Way", "Lad", "Prince"};
    
    static String[] foodTypes = {"Fast-food", "Italian", "Health", "Vegan", "Vegetarian", "Thai", "Chinese", "Mexican", "French", "Fancy", "Not-fancy"};
    
    static String[] locations = {"234 Something Street", "123 Food Avenue", "555 Eats Lane", "87 Main Lane"};
    
    static String[] openingHours = {"5am", "6am", "11am", "12pm", "8am", "6:30am"};
    static String[] closingHours = { "11pm", "10pm", "11:30pm", "12am", "11:59pm", "1am", "2am"};
	
    static int[] status = {-1,0,1};
	
    private static RestaurantService instance;

    public static RestaurantService createService() {
    	if(instance == null) {
    		
    		final RestaurantService restaurantService = new RestaurantService();
    		
    		Random r = new Random(0);
    		
    		for (int i = 0; i < 10; i++) {
    			
    			Restaurant restaurant = new Restaurant();
    			restaurant.setId(nextId++);
    			restaurant.setRestaurantName(namePrefixes[r.nextInt(namePrefixes.length)] + nameSuffixes[r.nextInt(nameSuffixes.length)]);
    			restaurant.setFoodType(foodTypes[r.nextInt(foodTypes.length)]);
    			restaurant.setLocation(locations[r.nextInt(locations.length)]);
    			restaurant.setHoursOfBusiness(openingHours[r.nextInt(openingHours.length)] + " to " + closingHours[r.nextInt(closingHours.length)]);
    			restaurant.setStatus(status[1]);
    			
    			restaurantService.save(restaurant);
    		}
    		instance = restaurantService;
    	}
    	return instance;
    }

    private HashMap<Long, Restaurant> restaurants = new HashMap<>();
    

    //I think this is the search box
    public synchronized List<Restaurant> findAll(String stringFilter, int stat) {
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
                if (passesFilter && restaurant.getStatus() == stat) {
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
                return (int) (o1.getId() - o2.getId());
            }
        });
        
        //Return it
        return arrayList;
    }

    public synchronized long count() {
        return restaurants.size();
    }

    public synchronized void delete(Restaurant value) {
//        restaurants.remove(value.getId());
    	restaurants.put(value.getId(),value);
    }

    public synchronized void save(Restaurant entry) {
        try {
            entry = (Restaurant) BeanUtils.cloneBean(entry);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        restaurants.put(entry.getId(), entry);
    }

}
