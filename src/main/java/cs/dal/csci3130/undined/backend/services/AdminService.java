package cs.dal.csci3130.undined.backend.services;

import org.apache.commons.beanutils.BeanUtils;

import cs.dal.csci3130.undined.backend.Admin;
import cs.dal.csci3130.undined.backend.Manager;
import cs.dal.csci3130.undined.backend.Admin;
import cs.dal.csci3130.undined.backend.User;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

// Backend service class. This is just a typical Java backend implementation
// class and nothing Vaadin specific.
public class AdminService {

	private static long nextId = 0;
	static String[] password = {"admin", "manager","user"};
	static String[] firstName = {"James", "Mike", "Tony"};
	static String[] lastName = {"Jackson", "Jackman", "Robert"};
	static String[] phone = {"900-000-0000"};
	
    private static AdminService instance;

    private HashMap<Long, Admin> admins = new HashMap<>();
    

    public static AdminService createService() {
    	if(instance == null) {
    		AdminService adminService = new AdminService();
    		
    		Random r = new Random(0);
    		
    		// admin
    		Admin admin = new Admin();
    		admin.setId(nextId++);
    		admin.setPassword(password[0]);
    		admin.setRole("admin");
    		admin.setFirstName(firstName[r.nextInt(firstName.length)]);
    		admin.setLastName(lastName[r.nextInt(lastName.length)]);
    		admin.setEmail("admin");
    		admin.setPhone(phone[0]);
    		adminService.save(admin);

    		
    		instance = adminService;
    	}
    	return instance;
    }
    
    //I think this is the search box.
    public synchronized List<Admin> findAll(String stringFilter) {
        ArrayList<Admin> arrayList = new ArrayList<Admin>();
        
        //For each user
        for (Admin user : admins.values()) {
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
        Collections.sort(arrayList, new Comparator<Admin>() {
            @Override
            public int compare(Admin o1, Admin o2) {
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
    	for (Admin admin: admins.values()) {
    		if (admin.getEmail().equals(email)) {
    			flag = 1;
    			if (admin.getPassword().equals(password)) {
    				flag = 2;
    			}
    		}
    	}
    	return flag;
    }
    
    public synchronized long count() {
        return admins.size();
    }

    public synchronized void delete(Admin value) {
        admins.remove(value.getId());
    }

    public synchronized void save(Admin entry) {
        if (entry.getId() == null) {
            entry.setId(nextId++);
        }
        try {
            entry = (Admin) BeanUtils.cloneBean(entry);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        admins.put(entry.getId(), entry);
    }

	
}
