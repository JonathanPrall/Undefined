package dal.cs.csci3130.undined.backend;

import org.apache.commons.beanutils.BeanUtils;

import java.io.Serializable;


//Class that holds all of the information of a user
public class User implements Serializable, Cloneable {

    private Long id;

    private String userName = "";
    private String emailAddress = "";
    private String phoneNumber = "";

    //Get and set ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    //Get and set user name
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    
    //Get and set email
    public String getEmailAddress(){
    	return emailAddress;
    }
    
    public boolean setEmailAddress(String emailAddress){
    	//If the email address has made an attempt to look valid
    	if(emailAddress.contains("@") && emailAddress.contains(".")){
    		//Set email address, return true
    		this.emailAddress = emailAddress;
    		return true;
    	}else{
    		//Otherwise, reject this falsehood
    		return false;
    	}
    }

    
    //Get and set phone number
    public String getPhoneNumber(){
    	return phoneNumber;
    }
    
    public boolean setPhoneNumber(String phoneNumber){
    	//Remove dashes and whitespace
    	phoneNumber = phoneNumber.replace("-", "");
    	phoneNumber = phoneNumber.replace(" ", "");
    	
    	//If only numbers remain, accept the phone number
    	if(phoneNumber.matches("[0-9]+")){
    		this.phoneNumber = phoneNumber;
    		return true;
    	}else{
    		//Otherwise no.
    		return false;
    	}
    }
    
    @Override
    public User clone() throws CloneNotSupportedException {
        try {
            return (User) BeanUtils.cloneBean(this);
        } catch (Exception ex) {
            throw new CloneNotSupportedException();
        }
    }

    @Override
    public String toString() {
        return userName;
    }

}
