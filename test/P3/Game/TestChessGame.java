package P3.Game;

import static org.junit.Assert.*;


import org.junit.Test;

import P3.Position.Position;

public class TestChessGame {

	//test strategy
	//ChessGame(String,String)
	//		test it with putPieceOnBoard(Player,String,Position)
	//		partition the inputs of putPieceOnBoard(Player,String,Position) as :
	//		place already has piece,place is empty,place is illegal
	//findrealPlayer(String)
	//		partition the inputs as :
	//		the input player has the same information with one of the game player,
	//		the input player has different information with any of the game player,
	//movePieceOnBoard(String,String,String,String,String)
	//		partition the inputs as :
	//		position: from position is empty, to position has his piece,from position have other's piece 
	//		from position is illegal,to position is illegal
	//eatPieceOnBoard(String,String,String,String,String)
	//		partition the inputs as :
	//		position: from position is empty, to position has his piece,from position have other's piece 
	// 		to position is illegal ,to position have others piece
	//occupationOfThisPosition(String,String)
	//		partition the inputs as:
	//		place:position has piece,position is empty,illegal position
	//getNumberOfRemianingPieces(String)
	//		test it twice,before and after you eat a piece of this player
	@Test
	public void testChessGameAndPutPiece() {
		ChessGame newGame = new ChessGame("one","two");
		Player newPlayer = new Player("three");
		assertTrue(newGame.putPieceOnBoard(newPlayer,"pawn", new Position(2,2)));
		assertFalse(newGame.putPieceOnBoard(newPlayer,"pawn", new Position(1,2)));
		assertFalse(newGame.putPieceOnBoard(newPlayer,"pawn", new Position(-1,-1)));
	}

	//covers:the input player has the same information with one of the game player,
	//		the input player has different information with any of the game player,

	@Test
	public void testFindPlayer()
	{
		ChessGame newGame = new ChessGame("one","two");
		Player firstPlayer = new Player("one");
		Player newPlayer = new Player("three");
		assertTrue(newGame.findRealPlayer(firstPlayer)!=null);
		assertTrue(newGame.findRealPlayer(newPlayer) == null);
	}
	
	//covers : from position is empty, to position has his piece,from position have other's piece 
	//	from position is illegal,to position is illegal,
	@Test
	public void testMovePiece()
	{
		ChessGame newGame = new ChessGame("one","two");
		assertTrue(newGame.movePiece("one", "1", "1", "2","1"));
		assertFalse(newGame.movePiece("one", "3", "3", "4","3"));
		assertFalse(newGame.movePiece("one", "1", "-1", "-1","-1"));
		assertFalse(newGame.movePiece("one", "6", "1", "5","1"));
	}
	
	//covers : from position is empty, to position has his piece,from position have other's piece 
	// to position is illegal ,to position have others piece
	@Test
	public void testEatPiece()
	{
		ChessGame newGame = new ChessGame("one","two");
		assertFalse(newGame.eatPiece("one", "1", "1", "2", "1"));
		assertFalse(newGame.eatPiece("one", "2", "1", "3", "1"));
		assertFalse(newGame.eatPiece("one", "6", "1", "1", "1"));
		assertFalse(newGame.eatPiece("one", "1", "1", "-1", "1"));
		assertTrue(newGame.eatPiece("one", "1", "1", "6", "1"));		
	}
	
	
	//covers:position has piece,position is empty,illegal position
	@Test
	public void testOccupation()
	{
		ChessGame newGame = new ChessGame("one","two");
		newGame.printBoard();
		assertTrue(newGame.occupationOfThisPosition("7", "1"));
		assertFalse(newGame.occupationOfThisPosition("2", "2"));
		assertFalse(newGame.occupationOfThisPosition("-1", "-1"));
		assertFalse(newGame.occupationOfThisPosition("8", "8"));
		newGame.eatPiece("one", "1", "1", "6", "1");		
		assertFalse(newGame.occupationOfThisPosition("1", "1"));
	}

	
		@Test
		public void testGetNumberOfRemainingPieces()
		{
			ChessGame newGame = new ChessGame("one","two");
			assertEquals(16,newGame.getnumberOfRemainingPieces("two"));
			newGame.eatPiece("one", "1", "1", "6", "1");		
			assertEquals(15,newGame.getnumberOfRemainingPieces("two"));
		}
	
}
