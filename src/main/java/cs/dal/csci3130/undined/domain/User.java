package cs.dal.csci3130.undined.domain;

import java.io.Serializable;

import org.apache.commons.beanutils.BeanUtils;

public class User implements Serializable, Cloneable{
	
	private Long id;
	
	private String password;
	private String role;
	private String firstName;
	private String lastName;
	private String email;// username
	private String phone;
	
	
	public User() {
		role = "user";
	}
	
	public User(Long id, String password, String role, String firstName, String lastName, String email, String phone) {
		this.id = id;
		this.password = password;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public User clone() throws CloneNotSupportedException {
		try {
			return (User) BeanUtils.cloneBean(this);
		} catch (Exception ex) {
			throw new CloneNotSupportedException();
		}
	}
	
}
