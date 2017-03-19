package cs.dal.csci3130.undined.dashboard;

import java.util.*;

import cs.dal.csci3130.undined.newbackend.Restaurant;
public class ManageInfo {
//for managing the restaurant information, open hour, close hour, resturant name,email, phone, number of table and a short description about the restaurant
	private Restaurant rest;
	private int sumtable, available, phone;
	private String des,email;
	
	//set the restaurant name, operating hours,email,phone, number of table available and the description
	public void setRestName(String name)
	{
		rest.setRestaurantName(name);
	}
public void setHour(String op)
	{
		rest.setHoursOfBusiness(op);
	}
	public void setTable(int t)
	{
		sumtable=t;
	}
	public void clean()
	{
		available=sumtable;
	}
	public void setEmail(String email)
	{
		this.email=email;
	}
	public void setPhone(int phone)
	{
		this.phone=phone;
	}
	public void setAvailable(int available)
	{
		this.available=available;
	}
	
	public void setDes(String des)
	{
		this.des=des;
	}
	//return methods
	public String getRestName()
	{
		return rest.getRestaurantName();
	}
	public int getAvailable()
	{
		return available;
	}
	public String getEmail()
	{
		return email;
	}
	public int getPhone()
	{
		return phone;
	}
	public String getDes()
	{
		return des;
	}
	
}
