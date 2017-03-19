package cs.dal.csci3130.undined.backend;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class MenuItemTest {
	
	@Test
	public void testMenuItem() {
		boolean expect = true;
		MenuItem a = new MenuItem(0, "name","description", (float) 1.0);
		boolean result = (a instanceof MenuItem);
		assertFalse(expect != result);
	}
	
	@Test
	public void testGetId() {
		MenuItem a = new MenuItem(0, "name", "desc", (float) 1.0);
		assertEquals(0, a.getId());
	}

	@Test
	public void testSetId() {
		MenuItem a = new MenuItem();
		a.setName("name");
		assertEquals("name", a.getName());
	}

	@Test
	public void testGetName() {
		MenuItem a = new MenuItem(0, "name", "desc", (float) 1.0);
		assertEquals("name", a.getName());
	}

	@Test
	public void testSetName() {
		MenuItem a = new MenuItem();
		a.setName("name");
		assertEquals("name", a.getName());
	}

	@Test
	public void testGetDescription() {
		MenuItem a = new MenuItem(0, "name", "desc", (float) 1.0);
		assertEquals("desc", a.getDescription());
	}

	@Test
	public void testSetDescription() {
		MenuItem a = new MenuItem();
		a.setDescription("desc");
		assertEquals("desc", a.getDescription());
	}

	@Test
	public void testGetPrice() {
		MenuItem a = new MenuItem(0, "name", "desc", (float) 1.0);
		assertEquals((float) 1.0, a.getPrice(), 0);
	}

	@Test
	public void testSetPrice() {
		MenuItem a = new MenuItem();
		a.setPrice((float) 1.0);
		assertEquals(1, a.getPrice(), 0);
	}

}
