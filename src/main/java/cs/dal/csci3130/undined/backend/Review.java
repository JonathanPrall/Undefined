package cs.dal.csci3130.undined.backend;

import java.io.Serializable;

import org.apache.commons.beanutils.BeanUtils;

//Class for storing information for an item on a restaurant's menu
public class Review implements Serializable, Cloneable
{
	private Long id;

	private String name = "";
	private String review = "";
	
	//private picture

	public Review() {
	}
	//Creates the menu item with all information
	public Review(String name, String review){
		this.name = name;
		this.review = review;
		
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	//Get and set name
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	//Get and set description
	public String getReview(){
		return review;
	}
	
	public void setReview(String review){
		this.review = review;
	}
	
	@Override
    public Review clone() throws CloneNotSupportedException {
        try {
            return (Review) BeanUtils.cloneBean(this);
        } catch (Exception ex) {
            throw new CloneNotSupportedException();
        }
    }
}
