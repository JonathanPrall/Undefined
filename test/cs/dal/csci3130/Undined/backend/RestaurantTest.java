package cs.dal.csci3130.Undined.backend;

import static org.junit.Assert.*;

import org.junit.Test;

public class RestaurantTest {

	@Test
	public void testRestaurant() {
		boolean expect = true;
		Restaurant a = new Restaurant();
		boolean result = (a instanceof Restaurant);
		assertFalse(expect != result);
	}

	@Test
	public void testRestaurantLongStringStringStringString() {
		boolean expect = true;
		long id = 1;
		Restaurant a = new Restaurant(id,"name","foodType","location","hoursOfBusiness");
		boolean result = (a instanceof Restaurant);
		assertFalse(expect != result);
	}

	@Test
	public void testGetStatus() {
		boolean expect = true;
		Restaurant a = new Restaurant();
		boolean result = a.getStatus() == 0;
		assertFalse(expect != result);
	}

	@Test
	public void testSetStatus() {
		boolean expect = true;
		Restaurant a = new Restaurant();
		a.setStatus(1);
		boolean result = a.getStatus() == 1;
		assertFalse(expect != result);
	}

	@Test
	public void testSetId() {
		boolean expect = true;
		Restaurant a = new Restaurant();
		long id = 1;
		a.setId(id);
		boolean result = a.getId() == id;
		assertFalse(expect != result);
	}

	@Test
	public void testGetId() {
		boolean expect = true;
		long id = 1;
		Restaurant a = new Restaurant(id,"name","foodType","location","hoursOfBusiness");
		boolean result = a.getId() == id;
		assertFalse(expect != result);
	}

	@Test
	public void testSetRestaurantName() {
		boolean expect = true;
		Restaurant a = new Restaurant();
		a.setRestaurantName("RestaurantName");
		boolean result = a.getRestaurantName().equals("RestaurantName");
		assertFalse(expect != result);
	}

	@Test
	public void testGetRestaurantName() {
		boolean expect = true;
		long id = 1;
		Restaurant a = new Restaurant(id,"name","foodType","location","hoursOfBusiness");
		boolean result = a.getRestaurantName().equals("name");
		assertFalse(expect != result);
	}

	@Test
	public void testGetFoodType() {
		boolean expect = true;
		long id = 1;
		Restaurant a = new Restaurant(id,"name","foodType","location","hoursOfBusiness");
		boolean result = a.getFoodType().equals("foodType");
		assertFalse(expect != result);
	}

	@Test
	public void testSetFoodType() {
		boolean expect = true;
		Restaurant a = new Restaurant();
		a.setFoodType("foodType");
		boolean result = a.getFoodType().equals("foodType");
		assertFalse(expect != result);
	}

	@Test
	public void testGetLocation() {
		boolean expect = true;
		long id = 1;
		Restaurant a = new Restaurant(id,"name","foodType","location","hoursOfBusiness");
		boolean result = a.getLocation().equals("location");
		assertFalse(expect != result);
	}

	@Test
	public void testSetLocation() {
		boolean expect = true;
		Restaurant a = new Restaurant();
		a.setLocation("location");
		boolean result = a.getLocation().equals("location");
		assertFalse(expect != result);
	}

	@Test
	public void testGetHoursOfBusiness() {
		boolean expect = true;
		long id = 1;
		Restaurant a = new Restaurant(id,"name","foodType","location","hoursOfBusiness");
		boolean result = a.getHoursOfBusiness().equals("hoursOfBusiness");
		assertFalse(expect != result);
	}

	@Test
	public void testSetHoursOfBusiness() {
		boolean expect = true;
		Restaurant a = new Restaurant();
		a.setHoursOfBusiness("hoursOfBusiness");
		boolean result = a.getHoursOfBusiness().equals("hoursOfBusiness");
		assertFalse(expect != result);
	}

	@Test
	public void testClone() {
		boolean expect = true;
		Restaurant a = new Restaurant();
		Object b = null;
		try {
			b = a.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		boolean result = (b instanceof Restaurant);
		assertFalse(expect != result);
	}

	@Test
	public void testToString() {
		boolean expect = true;
		long id = 1;
		Restaurant a = new Restaurant(id,"name","foodType","location","hoursOfBusiness");
		boolean result = a.toString().equals("name");
		assertFalse(expect != result);
	}

}
