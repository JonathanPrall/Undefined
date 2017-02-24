package cs.dal.csci3130.Undined.backend;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class ManagerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testManager() {
		boolean except = true;
		Manager a = new Manager();
		boolean result = (a instanceof Manager);
		assertFalse(except != result);
	}

	@Test
	public void testManagerLongStringLong() {
		boolean except = true;
		long i = 1;
		long j = 2;
		Manager a = new Manager(i,"managerName",j);
		boolean result = (a instanceof Manager);
		assertFalse(except != result);
	}

	@Test
	public void testGetId() {
		boolean except = true;
		long i = 1;
		long j = 2;
		Manager a = new Manager(i,"managerName",j);
		boolean result = a.getId() == i;
		assertFalse(except != result);
	}

	@Test
	public void testSetId() {
		boolean except = true;
		Manager a = new Manager();
		long i = 1;
		a.setId(i);
		boolean result = a.getId() == i;
		assertFalse(except != result);
	}

	@Test
	public void testSetManagerName() {
		boolean except = true;
		Manager a = new Manager();
		String i = "managerName";
		a.setManagerName(i);
		boolean result = a.getManagerName().equals("managerName");
		assertFalse(except != result);
	}

	@Test
	public void testGetManagerName() {
		boolean except = true;
		long i = 1;
		long j = 2;
		Manager a = new Manager(i,"managerName",j);
		boolean result = a.getManagerName().equals("managerName");
		assertFalse(except != result);
	}

	@Test
	public void testGetRestaurantId() {
		boolean except = true;
		long i = 1;
		long j = 2;
		Manager a = new Manager(i,"managerName",j);
		boolean result = a.getRestaurantId() == j;
		assertFalse(except != result);
	}

	@Test
	public void testSetRestaurantId() {
		boolean except = true;
		Manager a = new Manager();
		long i = 1;
		a.setRestaurantId(i);
		boolean result = a.getRestaurantId() == i;
		assertFalse(except != result);
	}

	@Test
	public void testClone() {
		boolean except = true;
		Manager a = new Manager();
		Object b = null;
		try {
			b = a.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		boolean result = (b instanceof Manager);
		assertFalse(except != result);
	}

	@Test
	public void testToString() {
		boolean except = true;
		long i = 1;
		long j = 2;
		Manager a = new Manager(i,"managerName",j);
		boolean result = a.toString().equals("managerName");
		assertFalse(except != result);
	}

}
