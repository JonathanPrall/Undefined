package my.vaadin.Undined.backend;

import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;


//Class that holds all of the information of a restaurants menu
public class Menu implements Serializable, Cloneable {

    private Long id;

    private String menuName = "";
    private ArrayList<MenuItem> menu; 

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
    
    //Add and remove menu items from the menu
    public void addMenuItem(MenuItem item){
    	menu.add(item);
    }
    
    public void addMenuItem(String name, String description, float price){
    	menu.add(new MenuItem(name, description, price));
    }
    
    public void removeMenuItem(MenuItem item){
    	menu.remove(item);
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
