package P3.Board;

import static org.junit.Assert.*;


import org.junit.Test;

import P3.Position.Position;

public abstract class TestBoardInstance {

	/**
	 * Tests for instance methods of Board.
	 * test strategy
	 * initialBoard()
	 * 		test it together with getPosition()		
	 * 		invoke the initialBoard() 
	 * 		partition the position as follows:x =0,y=0  x>0,y>0
	 * isLegalPlace()
	 * 		partition the input as follows:x =0,y=0  length>x>0,length>y>0 x<0,j<0  x>length,y>length
	 * isEmpty()
	 * 		partition the input as follows:x =0,y=0  length>x>0,length>y>0 x<0,j<0  x>length,y>length
	 * and the position is occupied or empty
	 */		
	
	 /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty board of the particular implementation being tested
     */
    public abstract Board emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    
	@Test
	public void testInitialAndGetPosition() {
	Board newBoard = emptyInstance();
	Position newPosition =new Position(0,0);	
	Position anotherPosition = new Position(1,1);
	Position thirdPosition = new Position(20,20);
	newBoard.initailBoard();
	assertEquals("empty",newBoard.getPosition(newPosition));
	assertEquals("empty",newBoard.getPosition(anotherPosition));	
	assertEquals(null,newBoard.getPosition(thirdPosition));
	}
	
	@Test
	public void testIsLegalPlace()
	{
		Board newBoard = emptyInstance();
		Position newPosition =new Position(0,0);	
		Position anotherPosition = new Position(1,1);
		Position thirdPosition = new Position(-1,-1);
		Position fourthPosition = new Position(19,19);
		assertTrue(newBoard.isLegalPlace(newPosition));
		assertTrue(newBoard.isLegalPlace(anotherPosition));
		assertFalse(newBoard.isLegalPlace(thirdPosition));
		assertFalse(newBoard.isLegalPlace(fourthPosition));
	}
	
	@Test
	public void testIsEmpty()
	{
		Board newBoard = emptyInstance();
		Position newPosition =new Position(0,0);	
		Position anotherPosition = new Position(1,1);
		Position thirdPosition = new Position(-1,-1);
		Position fourthPosition = new Position(19,19);
		newBoard.initailBoard();
		assertTrue(newBoard.isEmpty(newPosition));
		assertTrue(newBoard.isEmpty(anotherPosition));
		assertFalse(newBoard.isEmpty(thirdPosition));
		assertFalse(newBoard.isEmpty(fourthPosition));
	}
	
	

}
