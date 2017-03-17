package cs.dal.csci3130.undined.newbackend.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.beanutils.BeanUtils;

import cs.dal.csci3130.undined.newbackend.MenuItem;
import cs.dal.csci3130.undined.newbackend.Restaurant;

public class MenuItemService {
	
	private static long nextId = 0;
	static String[] namePrifixes = {"Bacon N' Egg", "Egg, Lettuce and Tomato", "Double Big Mac", "Filet-O-Fish"};
	static String[] nameSuffixes = {"Burger", "Burrito", "Sandwich", "Sandwich"};
	static String[] descriptions = {"It's delicious!"};
	static float[] price = {(float) 7.99, (float) 6.99, (float) 5.99};
	
	private static MenuItemService instance;
	
	public static MenuItemService createService() {
		if(instance == null) {
			final MenuItemService menuItemService = new MenuItemService();
			
			Random r = new Random();
			
			for(int i = 0; i < 10; i++) {
				MenuItem item = new MenuItem();
				item.setId(nextId++);
				item.setName(namePrifixes[r.nextInt(namePrifixes.length)] + nameSuffixes[r.nextInt(nameSuffixes.length)]);
				item.setDescription(descriptions[r.nextInt(descriptions.length)]);
				item.setPrice(price[r.nextInt(price.length)]);
				
				menuItemService.save(item);
			}
			instance = menuItemService;
		}
		return instance;
	}

	private HashMap<Long, MenuItem> menuItems = new HashMap<>();
	
	private synchronized List<MenuItem> findAll(String stringFilter, int stat) {
		ArrayList<MenuItem> arrayList = new ArrayList<MenuItem>();
		
		for(MenuItem menuItem: menuItems.values()) {
			try {
				boolean passesFilter = (stringFilter == null || stringFilter.isEmpty() 
						|| menuItem.toString().toLowerCase().contains(stringFilter.toLowerCase()));
				if(passesFilter) {
					arrayList.add(menuItem.clone());
				}
			} catch (CloneNotSupportedException ex) {
				Logger.getLogger(MenuItemService.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		
		//Sort the created list
        Collections.sort(arrayList, new Comparator<MenuItem>() {
            @Override
            public int compare(MenuItem o1, MenuItem o2) {
                return (int) (o1.getId() - o2.getId());
            }
        });
        
        return arrayList;
	}
	
	public synchronized long nextID() {
		return nextId++;
	}
	
	public synchronized long count() {
		return menuItems.size();
	}
	
	public synchronized void delete(MenuItem value) {
		menuItems.remove(value.getId());
	}
	
	private synchronized void save(MenuItem entry) {
		try {
			entry = (MenuItem) BeanUtils.cloneBean(entry);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		menuItems.put(entry.getId(), entry);
	}
}
