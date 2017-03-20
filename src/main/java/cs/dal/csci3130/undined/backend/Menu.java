package cs.dal.csci3130.undined.backend;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;


//Class that holds all of the information of a restaurants menu
public class Menu implements Serializable, Cloneable {

    private Long id;

    private List<MenuItem> menu; 

    public Menu() {}
    public Menu(Long id){
    	this.id = id;
    	this.menu = new ArrayList<MenuItem>();
    }
    public Menu(Long id, List<MenuItem> menu){
    	this.id = id;
    	this.menu = menu;
    }
    
    //Get and set ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    //Get the menu
    public List<MenuItem> getMenu(){
    	return menu;
    }
    

    @Override
    public Menu clone() throws CloneNotSupportedException {
        try {
            return (Menu) BeanUtils.cloneBean(this);
        } catch (Exception ex) {
            throw new CloneNotSupportedException();
        }
    }
}
