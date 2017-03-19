package cs.dal.csci3130.undined.backend;

import org.apache.commons.beanutils.BeanUtils;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

// Backend service class. This is just a typical Java backend implementation
// class and nothing Vaadin specific.
public class MenuService {

    private static MenuService instance;

    private HashMap<Long, Menu> menus = new HashMap<>();
    private long nextId = 0;

    //I think this is the search box.
    public synchronized List<Menu> findAll(String stringFilter) {
        ArrayList<Menu> arrayList = new ArrayList<Menu>();
        
        //For each menu
        for (Menu menu : menus.values()) {
            try {
            	//If there is nothing in the search box
            	//or
            	//The Menu.toString() contains the text in the search box (not case sensitive)
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || menu.toString().toLowerCase()
                                .contains(stringFilter.toLowerCase());
                //Add it to the list that will be returned
                if (passesFilter) {
                    arrayList.add(menu.clone());
                }
            
            //If error, log it
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(MenuService.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
        
        //Sort the created list
        Collections.sort(arrayList, new Comparator<Menu>() {
            @Override
            public int compare(Menu o1, Menu o2) {
                return (int) (o2.getId() - o1.getId());
            }
        });
        
        //Return it
        return arrayList;
    }

    public synchronized long count() {
        return menus.size();
    }

    public synchronized void delete(Menu value) {
        menus.remove(value.getId());
    }

    public synchronized void save(Menu entry) {
        if (entry.getId() == null) {
            entry.setId(nextId++);
        }
        try {
            entry = (Menu) BeanUtils.cloneBean(entry);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        menus.put(entry.getId(), entry);
    }

}