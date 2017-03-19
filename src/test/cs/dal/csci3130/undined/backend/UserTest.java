package cs.dal.csci3130.undined.backend;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

	@Test
	public void testUser() {
		boolean expect = true;
		User a = new User();
		boolean result = (a instanceof User);
		assertFalse(expect != result);
	}

	@Test
	public void testUserLongStringStringString() {
		boolean expect = true;
		Long id = (long) 233;
		User a = new User(id,"userName","emailAddress","phoneNumber");
		boolean result = (a instanceof User);
		assertFalse(expect != result);
	}

	@Test
	public void testGetId() {
		boolean expect = true;
		Long id = (long) 233;
		User a = new User(id,"userName","emailAddress","phoneNumber");
		boolean result = a.getId() == id;
		assertFalse(expect != result);
	}

	@Test
	public void testSetId() {
		boolean expect = true;
		User a = new User();
		Long id = (long) 233;
		a.setId(id);
		boolean result = a.getId() == id;
		assertFalse(expect != result);
	}

	@Test
	public void testGetUserName() {
		boolean expect = true;
		Long id = (long) 233;
		User a = new User(id,"userName","emailAddress","phoneNumber");
		boolean result = a.getUserName().equals("userName");
		assertFalse(expect != result);
	}

	@Test
	public void testSetUserName() {
		boolean expect = true;
		User a = new User();
		a.setUserName("userName");
		boolean result = a.getUserName().equals("userName");
		assertFalse(expect != result);
	}

	@Test
	public void testGetEmailAddress() {
		boolean expect = true;
		Long id = (long) 233;
		User a = new User(id,"userName","emailAddress","phoneNumber");
		boolean result = a.getEmailAddress().equals("emailAddress");
		assertFalse(expect != result);
	}

	@Test
	public void testSetEmailAddress() {
		boolean expect = true;
		User a = new User();
		a.setEmailAddress("test@test.com");
		boolean result = a.getEmailAddress().equals("test@test.com");
		assertFalse(expect != result);
	}

	@Test
	public void testGetPhoneNumber() {
		boolean expect = true;
		Long id = (long) 233;
		User a = new User(id,"userName","emailAddress","phoneNumber");
		boolean result = a.getPhoneNumber().equals("phoneNumber");
		assertFalse(expect != result);
	}

	@Test
	public void testSetPhoneNumber() {
		boolean expect = true;
		User a = new User();
		a.setPhoneNumber("123-321-1234");
		boolean result = a.getPhoneNumber().equals("1233211234");
		assertFalse(expect != result);
	}

	@Test
	public void testClone() {
		boolean expect = true;
		User a = new User();
		Object b = null;
		try {
			b = a.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		boolean result = (b instanceof User);
		assertFalse(expect != result);
	}

	@Test
	public void testToString() {
		boolean expect = true;
		Long id = (long) 233;
		User a = new User(id,"userName","emailAddress","phoneNumber");
		boolean result = a.toString().equals("userName");
		assertFalse(expect != result);
	}

}
