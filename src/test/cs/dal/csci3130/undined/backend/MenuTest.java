package cs.dal.csci3130.undined.backend;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class MenuTest {

	@Test
	public void testMenu() {
		boolean expect = true;
		Menu a = new Menu();
		boolean result = (a instanceof Menu);
		assertFalse(expect != result);
	}

	@Test
	public void testMenuLongStringArrayListOfMenuItem() {
		boolean expect = true;
		Long id = (long) 233;
		ArrayList<MenuItem> al = null;
		Menu menu = new Menu(id,"Name",al);
		boolean result = (menu instanceof Menu);
		assertFalse(expect != result);
	}

	@Test
	public void testGetId() {
		boolean expect = true;
		Long id = (long) 233;
		ArrayList<MenuItem> al = null;
		Menu menu = new Menu(id,"Name",al);
		boolean result = menu.getId() == id;
		assertFalse(expect != result);
	}

	@Test
	public void testSetId() {
		boolean expect = true;
		Menu menu = new Menu();
		Long id = (long) 233;
		menu.setId(id);
		boolean result = menu.getId() == id;
		assertFalse(expect != result);
	}

	@Test
	public void testGetMenuName() {
		boolean expect = true;
		Long id = (long) 233;
		ArrayList<MenuItem> al = null;
		Menu menu = new Menu(id,"menuName",al);
		boolean result = menu.getMenuName().equals("menuName");
		assertFalse(expect != result);
	}

	@Test
	public void testSetMenuName() {
		boolean expect = true;
		Menu menu = new Menu();
		menu.setMenuName("menuName");
		boolean result = menu.getMenuName().equals("menuName");
		assertFalse(expect != result);
	}

	@Test
	public void testGetMenu() {
		boolean expect = true;
		Long id = (long) 233;
		ArrayList<MenuItem> al = null;
		Menu menu = new Menu(id,"Name",al);
		boolean result = menu.getMenu() == al;
		assertFalse(expect != result);
	}
/*
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
*/
	@Test
	public void testClone() {
		boolean expect = true;
		Menu a = new Menu();
		Object b = null;
		try {
			b = a.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		boolean result = (b instanceof Menu);
		assertFalse(expect != result);
	}

	@Test
	public void testToString() {
		boolean expect = true;
		Long id = (long) 233;
		ArrayList<MenuItem> al = null;
		Menu menu = new Menu(id,"menuName",al);
		boolean result = menu.toString().equals("menuName");
		assertFalse(expect != result);
	}

}
