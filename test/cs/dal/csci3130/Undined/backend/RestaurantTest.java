package cs.dal.csci3130.Undined.backend;

import static org.junit.Assert.*;

import org.junit.Test;

public class RestaurantTest {

	@Test
	public void testRestaurant() {
		boolean except = true;
		Restaurant a = new Restaurant();
		boolean result = (a instanceof Restaurant);
		assertFalse(except != result);
	}

	@Test
	public void testRestaurantLongStringStringStringString() {
		boolean except = true;
		long id = 1;
		Restaurant a = new Restaurant(id,"name","foodType","location","hoursOfBusiness");
		boolean result = (a instanceof Restaurant);
		assertFalse(except != result);
	}

	@Test
	public void testGetStatus() {
		boolean except = true;
		Restaurant a = new Restaurant();
		boolean result = a.getStatus() == 0;
		assertFalse(except != result);
	}

	@Test
	public void testSetStatus() {
		boolean except = true;
		Restaurant a = new Restaurant();
		a.setStatus(1);
		boolean result = a.getStatus() == 1;
		assertFalse(except != result);
	}

	@Test
	public void testSetId() {
		boolean except = true;
		Restaurant a = new Restaurant();
		long id = 1;
		a.setId(id);
		boolean result = a.getId() == id;
		assertFalse(except != result);
	}

	@Test
	public void testGetId() {
		boolean except = true;
		long id = 1;
		Restaurant a = new Restaurant(id,"name","foodType","location","hoursOfBusiness");
		boolean result = a.getId() == id;
		assertFalse(except != result);
	}

	@Test
	public void testSetRestaurantName() {
		boolean except = true;
		Restaurant a = new Restaurant();
		a.setRestaurantName("RestaurantName");
		boolean result = a.getRestaurantName().equals("RestaurantName");
		assertFalse(except != result);
	}

	@Test
	public void testGetRestaurantName() {
		boolean except = true;
		long id = 1;
		Restaurant a = new Restaurant(id,"name","foodType","location","hoursOfBusiness");
		boolean result = a.getRestaurantName().equals("name");
		assertFalse(except != result);
	}

	@Test
	public void testGetFoodType() {
		boolean except = true;
		long id = 1;
		Restaurant a = new Restaurant(id,"name","foodType","location","hoursOfBusiness");
		boolean result = a.getFoodType().equals("foodType");
		assertFalse(except != result);
	}

	@Test
	public void testSetFoodType() {
		boolean except = true;
		Restaurant a = new Restaurant();
		a.setFoodType("foodType");
		boolean result = a.getFoodType().equals("foodType");
		assertFalse(except != result);
	}

	@Test
	public void testGetLocation() {
		boolean except = true;
		long id = 1;
		Restaurant a = new Restaurant(id,"name","foodType","location","hoursOfBusiness");
		boolean result = a.getLocation().equals("location");
		assertFalse(except != result);
	}

	@Test
	public void testSetLocation() {
		boolean except = true;
		Restaurant a = new Restaurant();
		a.setLocation("location");
		boolean result = a.getLocation().equals("location");
		assertFalse(except != result);
	}

	@Test
	public void testGetHoursOfBusiness() {
		boolean except = true;
		long id = 1;
		Restaurant a = new Restaurant(id,"name","foodType","location","hoursOfBusiness");
		boolean result = a.getHoursOfBusiness().equals("hoursOfBusiness");
		assertFalse(except != result);
	}

	@Test
	public void testSetHoursOfBusiness() {
		boolean except = true;
		Restaurant a = new Restaurant();
		a.setHoursOfBusiness("hoursOfBusiness");
		boolean result = a.getHoursOfBusiness().equals("hoursOfBusiness");
		assertFalse(except != result);
	}

	@Test
	public void testClone() {
		boolean except = true;
		Restaurant a = new Restaurant();
		Object b = null;
		try {
			b = a.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		boolean result = (b instanceof Restaurant);
		assertFalse(except != result);
	}

	@Test
	public void testToString() {
		boolean except = true;
		long id = 1;
		Restaurant a = new Restaurant(id,"name","foodType","location","hoursOfBusiness");
		boolean result = a.toString().equals("name");
		assertFalse(except != result);
	}

}
