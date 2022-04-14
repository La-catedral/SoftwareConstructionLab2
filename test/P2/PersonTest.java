package P2;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PersonTest {

	//test strategy 
	// testPerson()
	//		define a new Person and observe its information 
	// testToStringPerson()
	//		observe a person's information by invoke toString
	// testEqualsPerson()
	//		input a person has same name this person
	
	@Test
	public void testPerson() {
		Person newperson =new Person("newperson");
		assertTrue(newperson.toString().equals("newperson"));
	}
	
	@Test
	public void testToStringPerson() {
		Person newPerson =new Person("Richard");
		String s ="Richard";
		assertTrue(s.equals(newPerson.toString()));

	}

	@Test
	public void testEqualsPerson() {
		Person newPerson =new Person("Tank");
		Person anotherPerson = new Person("Linda");
		Person thirdPerson =new Person("Tank");
		assertFalse(newPerson.equals(anotherPerson));
		assertTrue(newPerson.equals(thirdPerson));

	}
	
}
