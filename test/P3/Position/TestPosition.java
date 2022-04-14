package P3.Position;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestPosition {

	// Test strategy
	// test these three methods together:Position(int x,int y),getX() , getY()
	// define a new Position and observe its x and y coordinate
	
	// equals()
	//		define two Position instance with same coordinate
	//		invoke equals by one of them,and input another,observe the result
	// hashCode()
	// 		similar to equals()
	@Test
	public void testPostionAndGet() {
		Position newPosition = new Position(1,2);
		assertEquals(1,newPosition.getX());
		assertEquals(2,newPosition.getY());
	}
	
	
	@Test 
	public void TestEquals()
	{
		Position firstPosition = new Position(1,2);
		Position secondPosition = new Position(1,2);
		Position thirdPosition = new Position(1,3);

		assertTrue(firstPosition.equals(secondPosition));
		assertFalse(firstPosition.equals(thirdPosition));
		
	}
	
	@Test 
	public void TestHashCode()
	{
		Position firstPosition = new Position(1,2);
		Position secondPosition = new Position(1,2);
		assertEquals(firstPosition.hashCode(),secondPosition.hashCode());
	}
	
	

}
