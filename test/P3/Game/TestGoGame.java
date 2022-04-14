package P3.Game;

import static org.junit.Assert.*;


import org.junit.Test;


public class TestGoGame {

	//test strategy
		//ChessGame(String,String)
		//		test it with putPieceOnBoard(Player,String,String,String)
		//		partition the inputs of putPieceOnBoard(Player,String,String,String) as :
		//		position: position is illegal,is empty,is occupied by himself,is occupied by other player
		//findrealPlayer(String)
		//		partition the inputs as :
		//		the input player has the same information with one of the game player,
		//		the input player has different information with any of the game player,
		//removeOthersPiece(String,String,String)
		//		partition the inputs as :
		//		position:position has his piece,position is empty,is illegal,has others piece
		//occupationOfThisPosition(String,String)
		//		partition the inputs as:
		//		place:position has piece,position is empty,illegal position
		//getNumberOfRemianingPieces(String)
		//		test it twice,before and after you put a piece of this player
		
	
	//covers: position is illegal,is empty,is occupied by himself,is occupied by other player
	@Test
	public void testPutPieces()
	{
		GoGame newGame = new GoGame("one","two");
		assertTrue(newGame.putPieceOnBoard("one", "white", "1", "1"));
		assertFalse(newGame.putPieceOnBoard("one", "white", "1", "1"));
		assertFalse(newGame.putPieceOnBoard("two", "black", "1", "1"));
		assertFalse(newGame.putPieceOnBoard("one", "white", "-1", "1"));
	}
	
	
	//covers:the input player has the same information with one of the game player,
	//		the input player has different information with any of the game player,

	@Test
	public void testFindRealPlayer()
	{
		GoGame newGame = new GoGame("one","two");
		Player firstPlayer = new Player("one");
		Player newPlayer = new Player("three");
		assertTrue(newGame.findRealPlayer(firstPlayer)!=null);
		assertTrue(newGame.findRealPlayer(newPlayer) == null);
	}
	
	//covers:position has his piece,position is empty,is illegal,has others piece
	@Test
	public void testRemovePieces()
	{
		GoGame newGame = new GoGame("one","two");
		newGame.putPieceOnBoard("one", "white", "1", "1");
		newGame.putPieceOnBoard("two", "black", "1", "2");
		assertFalse(newGame.removeOthersPieceFromBoard("one", "1", "1"));
		assertFalse(newGame.removeOthersPieceFromBoard("one", "1", "3"));
		assertFalse(newGame.removeOthersPieceFromBoard("one", "1", "-1"));
		assertTrue(newGame.removeOthersPieceFromBoard("one", "1", "2"));
		
	}

	//covers: the player has a piece,the player does not has a piece
	@Test
	public void testNumberOfRemainingPieces()
	{
		GoGame newGame = new GoGame("one","two");
		assertEquals(0,newGame.getnumberOfRemainingPieces("one"));
		newGame.putPieceOnBoard("one", "white", "1", "1");
		assertEquals(1,newGame.getnumberOfRemainingPieces("one"));		
	}
	
	//covers: the position is empty,is occupied,is illegal 
	@Test
	public void testOccupied() 
	{
		GoGame newGame = new GoGame("one","two");
		newGame.putPieceOnBoard("one", "white", "1", "1");
		assertTrue(newGame.occupationOfThisPosition("1", "1"));
		assertFalse(newGame.occupationOfThisPosition("-1", "1"));
		assertFalse(newGame.occupationOfThisPosition("2", "1"));
	}
	
}
