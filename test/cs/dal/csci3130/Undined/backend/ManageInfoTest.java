package cs.dal.csci3130.Undined.backend;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class ManageInfoTest {
    //given the set/getRestName,set/getHour are from restaurant class, we are not going to test them here
    //We only have empty constructor, which cannot set the original data through it, so the test both set and get at the same time
@Test
public void testManageInfo(){
    boolean excpet = true;
    ManageInfo m = new ManageInfo();
    boolean result = ( a instanceof ManageInfo);
    assertFalse(except != result);
}
public void testTable(){
    boolean excpet = true;
    ManageInfo m = new ManageInfo();
    int a = 10;
    m.setTable(a);
    boolean result = m.getTable() == a;
    assertFalse(except != result);
}
//test clean is also the test for test available
public void testclean(){
    boolean excpet = true;
    ManageInfo m = new ManageInfo();
    int a = 10;
    m.setTable(a);
    m.clean();
    boolean result = m.getAvailable() == m.getTable();
    assertFalse(except != result);
}
public void testEmail(){
    boolean excpet = true;
    ManageInfo m = new ManageInfo();
    String a = "aaa";
    m.setEmail(a);
    boolean result = m.getEmail().equals("aaa");
    assertFalse(except != result);
}
public void testPhone(){
    boolean excpet = true;
    ManageInfo m = new ManageInfo();
    int a = 10;
    m.setPhone(a);
    boolean result = m.getTable() == a;
    assertFalse(except != result);
}
public void testDes(){
    boolean excpet = true;
    ManageInfo m = new ManageInfo();
    String a = "aaa";
    m.setDes(a);
    boolean result = m.getDes().equals("aaa");
    assertFalse(except != result);
}
}