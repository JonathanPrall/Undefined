package cs.dal.csci3130.undined.backend;

import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


//Class that holds all of the information of a menus menu
public class Menu implements Serializable, Cloneable {
	
	private static long nextId = 0;
    private Long id;

    private String menuName = "";
    private ArrayList<MenuItem> menu; 

    public Menu(){}
    public Menu(Long id, String menuName,ArrayList<MenuItem> menu){
    	this.id = id;
    	this.menuName = menuName;
    	this.menu = menu;
    }
    
    static String[] name = {"Burger"};
    static String[] description = {"It's a... burger"};
    static float[] price = {(float) 2.50};
    
    
    private static Menu instance;
    
    public static Menu createMenu() {
    	
    	if(instance == null) {
    		
    		final Menu menu = new Menu();
    		
    		Random r = new Random(0);
    		
    		for (int i = 0; i < 10; i++) {
    			
    			MenuItem menuItem = new MenuItem();
    			menuItem.setId(nextId++);
    			menuItem.setName(name[r.nextInt(name.length)]);
    			menuItem.setDescription(description[r.nextInt(description.length)]);
    			menuItem.setPrice(price[r.nextInt(price.length)]);
    			menuItem.setStatus(r.nextInt(3));
    			menu.save(menuItem);
    		}
    		instance = menu;
    	}
    	return instance;
    }
    
    //Get and set ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    //Get and set menu name
    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    
    //Get the menu
    public ArrayList<MenuItem> getMenu(){
    	return menu;
    }
    
    private HashMap<Long, MenuItem> menuItems = new HashMap<>();
    
    public synchronized void save(MenuItem entry) {
        try {
            entry = (MenuItem) BeanUtils.cloneBean(entry);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        menuItems.put(entry.getId(), entry);
    }
    //I think this is the search box
    public synchronized List<MenuItem> findAll(String stringFilter) {
        ArrayList<MenuItem> arrayList = new ArrayList<MenuItem>();
        
        //For each restaurant
        for (MenuItem menuItem : menuItems.values()) {
            try {
            	//If there is nothing in the search box
            	//or
            	//The Menu.toString() contains the text in the search box (not case sensitive)
                boolean passesFilter = (stringFilter == null || stringFilter.isEmpty())
                        || menuItem.toString().toLowerCase()
                                .contains(stringFilter.toLowerCase());
                //Add it to the list that will be returned
                if (passesFilter) {
                    arrayList.add(menuItem.clone());
                }
                
            
            //If error, log it
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(Menu.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
        
        //Sort the created list
        Collections.sort(arrayList, new Comparator<MenuItem>() {
            @Override
            public int compare(MenuItem o1, MenuItem o2) {
                return (int) (o1.getId() - o2.getId());
            }
        });
        
        //Return it
        return arrayList;
    }
    public synchronized List<MenuItem> findAll(int stat) {
        ArrayList<MenuItem> arrayList = new ArrayList<MenuItem>();
        
        //For each restaurant
        for (MenuItem menuItem : menuItems.values()) {
            try {
            	if(menuItem.getStatus() == stat)
            		arrayList.add(menuItem.clone());
            	if(stat == -1)
            		arrayList.add(menuItem.clone());

            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(Menu.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
            
          //Sort the created list
            Collections.sort(arrayList, new Comparator<MenuItem>() {
                @Override
                public int compare(MenuItem o1, MenuItem o2) {
                    return (int) (o1.getId() - o2.getId());
                }
            });
            
            //Return it
        }
        return arrayList;
    }
    
    @Override
    public Menu clone() throws CloneNotSupportedException {
        try {
            return (Menu) BeanUtils.cloneBean(this);
        } catch (Exception ex) {
            throw new CloneNotSupportedException();
        }
    }

    @Override
    public String toString() {
        return menuName;
    }

}
