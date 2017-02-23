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

    private static RestaurantService instance;

    //Code that originally filled the database with fake contacts
    /*
    public static ContactService createDemoService() {
        if (instance == null) {

            final ContactService contactService = new ContactService();

            Random r = new Random(0);
            Calendar cal = Calendar.getInstance();
            for (int i = 0; i < 100; i++) {
                Contact contact = new Contact();
                contact.setFirstName(fnames[r.nextInt(fnames.length)]);
                contact.setLastName(lnames[r.nextInt(fnames.length)]);
                contact.setStartDate(cal.getTime());
                contact.setTask("+ 358 555 " + (100 + r.nextInt(900)));
                cal.set(1930 + r.nextInt(70),
                        r.nextInt(11), r.nextInt(28));
                contact.setEndDate(cal.getTime());
                contactService.save(contact);
            }
            instance = contactService;
        }

        return instance;
    }
	*/

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
        if (entry.getId() == null) {
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
