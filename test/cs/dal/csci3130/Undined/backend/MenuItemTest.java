package cs.dal.csci3130.Undined.backend;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class MenuItemTest {
	
	@Test
	public void testMenuItem() {
		boolean except = true;
		float i = 233;
		MenuItem a = new MenuItem("name","description",i);
		boolean result = (a instanceof MenuItem);
		assertFalse(except != result);
	}

	@Test
	public void testGetName() {
		boolean except = true;
		float i = 233;
		MenuItem a = new MenuItem("name","description",i);
		boolean result = a.getName().equals("name");
		assertFalse(except != result);
	}

	@Test
	public void testSetName() {
		boolean except = true;
		float i = 233;
		MenuItem a = new MenuItem("name","description",i);
		a.setName("secondName");
		boolean result = a.getName().equals("secondName");
		assertFalse(except != result);
	}

	@Test
	public void testGetDescription() {
		boolean except = true;
		float i = 233;
		MenuItem a = new MenuItem("name","description",i);
		boolean result = a.getDescription().equals("description");
		assertFalse(except != result);
	}

	@Test
	public void testSetDescription() {
		boolean except = true;
		float i = 233;
		MenuItem a = new MenuItem("name","description",i);
		a.setDescription("secondDescription");
		boolean result = a.getDescription().equals("secondDescription");
		assertFalse(except != result);
	}

	@Test
	public void testGetPrice() {
		boolean except = true;
		float i = 233;
		MenuItem a = new MenuItem("name","description",i);
		boolean result = a.getPrice() == 233;
		assertFalse(except != result);
	}

	@Test
	public void testSetPrice() {
		boolean except = true;
		float i = 233;
		MenuItem a = new MenuItem("name","description",i);
		a.setPrice(332);
		boolean result = a.getPrice() == 332;
		assertFalse(except != result);
	}

}
