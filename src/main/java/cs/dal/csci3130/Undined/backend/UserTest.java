package cs.dal.csci3130.Undined.backend;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

	@Test
	public void testUser() {
		boolean except = true;
		User a = new User();
		boolean result = (a instanceof User);
		assertFalse(except != result);
	}

	@Test
	public void testUserLongStringStringString() {
		boolean except = true;
		Long id = (long) 233;
		User a = new User(id,"userName","emailAddress","phoneNumber");
		boolean result = (a instanceof User);
		assertFalse(except != result);
	}

	@Test
	public void testGetId() {
		boolean except = true;
		Long id = (long) 233;
		User a = new User(id,"userName","emailAddress","phoneNumber");
		boolean result = a.getId() == id;
		assertFalse(except != result);
	}

	@Test
	public void testSetId() {
		boolean except = true;
		User a = new User();
		Long id = (long) 233;
		a.setId(id);
		boolean result = a.getId() == id;
		assertFalse(except != result);
	}

	@Test
	public void testGetUserName() {
		boolean except = true;
		Long id = (long) 233;
		User a = new User(id,"userName","emailAddress","phoneNumber");
		boolean result = a.getUserName().equals("userName");
		assertFalse(except != result);
	}

	@Test
	public void testSetUserName() {
		boolean except = true;
		User a = new User();
		a.setUserName("userName");
		boolean result = a.getUserName().equals("userName");
		assertFalse(except != result);
	}

	@Test
	public void testGetEmailAddress() {
		boolean except = true;
		Long id = (long) 233;
		User a = new User(id,"userName","emailAddress","phoneNumber");
		boolean result = a.getEmailAddress().equals("emailAddress");
		assertFalse(except != result);
	}

	@Test
	public void testSetEmailAddress() {
		boolean except = true;
		User a = new User();
		a.setEmailAddress("test@test.com");
		boolean result = a.getEmailAddress().equals("test@test.com");
		assertFalse(except != result);
	}

	@Test
	public void testGetPhoneNumber() {
		boolean except = true;
		Long id = (long) 233;
		User a = new User(id,"userName","emailAddress","phoneNumber");
		boolean result = a.getPhoneNumber().equals("phoneNumber");
		assertFalse(except != result);
	}

	@Test
	public void testSetPhoneNumber() {
		boolean except = true;
		User a = new User();
		a.setPhoneNumber("123-321-1234");
		boolean result = a.getPhoneNumber().equals("1233211234");
		assertFalse(except != result);
	}

	@Test
	public void testClone() {
		boolean except = true;
		User a = new User();
		Object b = null;
		try {
			b = a.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		boolean result = (b instanceof User);
		assertFalse(except != result);
	}

	@Test
	public void testToString() {
		boolean except = true;
		Long id = (long) 233;
		User a = new User(id,"userName","emailAddress","phoneNumber");
		boolean result = a.toString().equals("userName");
		assertFalse(except != result);
	}

}
