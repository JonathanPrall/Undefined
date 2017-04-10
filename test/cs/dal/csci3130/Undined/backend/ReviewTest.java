package cs.dal.csci3130.undined.backend;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class ReviewTest {
   //to test review class
@Test
public void testReview(){
   boolean excpet = true;
   Review r = new Review();
   boolean result = ( a instanceof Review);
   assertFalse(except != result);
}
public void testReviewWords(){
   boolean excpet = true;
   Review r = new Review();
   String a = "aaa";
   r.setEmail(a);
   boolean result = r.getReview().equals("aaa");
   assertFalse(except != result);
}
public void testName(){
   boolean excpet = true;
   Review r = new Review();
   String a = "aaa";
   r.setName(a);
   boolean result = r.getName().equals("aaa");
   assertFalse(except != result);
}
}