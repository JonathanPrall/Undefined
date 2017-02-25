package cs.dal.csci3130.Undined.backend;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class MenuTest {

	@Test
	public void testMenu() {
		boolean except = true;
		Menu a = new Menu();
		boolean result = (a instanceof Menu);
		assertFalse(except != result);
	}

	@Test
	public void testMenuLongStringArrayListOfMenuItem() {
		boolean except = true;
		Long id = (long) 233;
		ArrayList<MenuItem> al = null;
		Menu menu = new Menu(id,"Name",al);
		boolean result = (menu instanceof Menu);
		assertFalse(except != result);
	}

	@Test
	public void testGetId() {
		boolean except = true;
		Long id = (long) 233;
		ArrayList<MenuItem> al = null;
		Menu menu = new Menu(id,"Name",al);
		boolean result = menu.getId() == id;
		assertFalse(except != result);
	}

	@Test
	public void testSetId() {
		boolean except = true;
		Menu menu = new Menu();
		Long id = (long) 233;
		menu.setId(id);
		boolean result = menu.getId() == id;
		assertFalse(except != result);
	}

	@Test
	public void testGetMenuName() {
		boolean except = true;
		Long id = (long) 233;
		ArrayList<MenuItem> al = null;
		Menu menu = new Menu(id,"menuName",al);
		boolean result = menu.getMenuName().equals("menuName");
		assertFalse(except != result);
	}

	@Test
	public void testSetMenuName() {
		boolean except = true;
		Menu menu = new Menu();
		menu.setMenuName("menuName");
		boolean result = menu.getMenuName().equals("menuName");
		assertFalse(except != result);
	}

	@Test
	public void testGetMenu() {
		boolean except = true;
		Long id = (long) 233;
		ArrayList<MenuItem> al = null;
		Menu menu = new Menu(id,"Name",al);
		boolean result = menu.getMenu() == al;
		assertFalse(except != result);
	}

	@Test
	public void testAddMenuItemMenuItem() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testAddMenuItemStringStringFloat() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testRemoveMenuItem() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	public void testClone() {
		boolean except = true;
		Menu a = new Menu();
		Object b = null;
		try {
			b = a.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		boolean result = (b instanceof Menu);
		assertFalse(except != result);
	}

	@Test
	public void testToString() {
		boolean except = true;
		Long id = (long) 233;
		ArrayList<MenuItem> al = null;
		Menu menu = new Menu(id,"menuName",al);
		boolean result = menu.toString().equals("menuName");
		assertFalse(except != result);
	}

}
