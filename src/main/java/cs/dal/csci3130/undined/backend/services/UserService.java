package cs.dal.csci3130.undined.backend.services;

import org.apache.commons.beanutils.BeanUtils;

import cs.dal.csci3130.undined.backend.Admin;
import cs.dal.csci3130.undined.backend.Manager;
import cs.dal.csci3130.undined.backend.User;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

// Backend service class. This is just a typical Java backend implementation
// class and nothing Vaadin specific.
public class UserService {

	private static long nextId = 0;
	static String[] password = {"admin", "manager","user"};
	static String[] firstName = {"James", "Mike", "Tony"};
	static String[] lastName = {"Jackson", "Jackman", "Robert"};
	static String[] phone = {"900-000-0000"};
	
    private static UserService instance;

    private HashMap<Long, User> users = new HashMap<>();
    

    public static UserService createService() {
    	if(instance == null) {
    		UserService userService = new UserService();
    		
    		Random r = new Random(0);
    		
    		// users
    		for(int i = 0; i < 5; i++) {
    			User user = new User();
    			user.setId(nextId++);
    			user.setPassword(password[2]);
    			user.setRole("user");
    			user.setFirstName(firstName[r.nextInt(firstName.length)]);
    			user.setLastName(lastName[r.nextInt(lastName.length)]);
    			user.setEmail("user" + Integer.toString(i));
    			user.setPhone(phone[0]);
        		userService.save(user);
    		}
    		instance = userService;
    	}
    	return instance;
    }
    
    //I think this is the search box.
    public synchronized List<User> findAll(String stringFilter) {
        ArrayList<User> arrayList = new ArrayList<User>();
        
        //For each user
        for (User user : users.values()) {
            try {
            	//If there is nothing in the search box
            	//or
            	//The User.toString() contains the text in the search box (not case sensitive)
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || user.toString().toLowerCase()
                                .contains(stringFilter.toLowerCase());
                //Add it to the list that will be returned
                if (passesFilter) {
                    arrayList.add(user.clone());
                }
            
            //If error, log it
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(UserService.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
        
        //Sort the created list
        Collections.sort(arrayList, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return (int) (o1.getId() - o2.getId());
            }
        });
        
        //Return it
        return arrayList;
    }
    
    public synchronized int findOne(String email, String password) {
    	// 0 username not found
    	// 1 username found but password wrong
    	// 2 passed
    	int flag = 0;
    	for (User user: users.values()) {
    		if (user.getEmail().equals(email)) {
    			flag = 1;
    			if (user.getPassword().equals(password)) {
    				flag = 2;
    			}
    		}
    	}
    	return flag;
    }
    public synchronized long count() {
        return users.size();
    }

    public synchronized void delete(User value) {
        users.remove(value.getId());
    }

    public synchronized void save(User entry) {
        if (entry.getId() == null) {
            entry.setId(nextId++);
        }
        try {
            entry = (User) BeanUtils.cloneBean(entry);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        users.put(entry.getId(), entry);
    }

}
