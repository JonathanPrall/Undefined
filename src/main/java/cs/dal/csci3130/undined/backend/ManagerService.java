package cs.dal.csci3130.undined.backend;

import org.apache.commons.beanutils.BeanUtils;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

// Backend service class. This is just a typical Java backend implementation
// class and nothing Vaadin specific.
public class ManagerService {

    private static ManagerService instance;

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

    private HashMap<Long, Manager> managers = new HashMap<>();
    private long nextId = 0;

    //I think this is the search box
    public synchronized List<Manager> findAll(String stringFilter) {
        ArrayList<Manager> arrayList = new ArrayList<Manager>();
        
        //For each manager
        for (Manager manager : managers.values()) {
            try {
            	//If there is nothing in the search box
            	//or
            	//The Manager.toString() contains the text in the search box (not case sensitive)
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || manager.toString().toLowerCase()
                                .contains(stringFilter.toLowerCase());
                //Add it to the list that will be returned
                if (passesFilter) {
                    arrayList.add(manager.clone());
                }
            
            //If error, log it
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(ManagerService.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
        
        //Sort the created list
        Collections.sort(arrayList, new Comparator<Manager>() {
            @Override
            public int compare(Manager o1, Manager o2) {
                return (int) (o2.getId() - o1.getId());
            }
        });
        
        //Return it
        return arrayList;
    }

    public synchronized long count() {
        return managers.size();
    }

    public synchronized void delete(Manager value) {
        managers.remove(value.getId());
    }

    public synchronized void save(Manager entry) {
        if (entry.getId() == null) {
            entry.setId(nextId++);
        }
        try {
            entry = (Manager) BeanUtils.cloneBean(entry);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        managers.put(entry.getId(), entry);
    }

}
