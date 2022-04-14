package P3.Piece;

import static org.junit.Assert.*;


import org.junit.Test;

import P3.Game.Player;
import P3.Position.Position;

public class TestPiece {

	//test strategy
	//Piece()
	//		test it with toString()
	//		define a new piece and observe its name by toString()
	//getPosition()
	//		no inputs
	//		define a new piece,and invoke this method,observe the result
	//setPlace(Position p)
	//		partition the inputs as follows:
	//		the x and y of the position =0   x>0,y>0
	//hashCode()
	//		no inputs
	//		compare two piece with same information
	//		compare two piece with different information
	//equals()
	//		same strategy with hashCode()
	
	
	
	@Test
	public void testPiece() {
		Piece newPiece = new Piece("test", new Player("tester"), new Position(1,1));
		assertEquals("test",newPiece.toString());
	}
	
	@Test
	public void testGetPosition()
	{
		Piece newPiece = new Piece("test", new Player("tester"), new Position(1,1));
		assertEquals(new Position(1,1),newPiece.getPosition());
	}
	
	@Test
	public void testSetPlace()
	{
		Piece newPiece = new Piece("test", new Player("tester"), new Position(1,1));
		newPiece.setPlace(new Position(7,8));
		assertEquals(new Position(7,8),newPiece.getPosition());
		newPiece.setPlace(new Position(0,0));
		assertEquals(new Position(0,0),newPiece.getPosition());
	}
	
	
	

}
