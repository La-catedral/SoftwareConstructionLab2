package P3.Game;

import static org.junit.Assert.*;


import org.junit.Test;

import P3.Game.Player;
import P3.Piece.Piece;
import P3.Position.Position;

public class TestPlayer {

	
	//test strategy
	//Player(String s) toString()
	//		test them together,input a string to the constructor
	// 		observe it by invoke the moethod toString()
	//addPieceToHim(Piece p)
	//		partition the inputs as: piece has not been added,piece has been added
	//		observe the result
	//removePieceFromHim(Piece p)
	//		similar to last method 
	//findPiece(Piece p)
	//		partition the inputs as: this piece has not been added to him,piece does not belongs to him
	//		observe the result
	//findPieceByPositon(Position p)
	//		partition the inputs as: there is a piece of his in this place,there is not a piece on this place
	// 		or it is a emtpy place,observe the result
	//getRemainingPiece()
	//		partition the inputs as: 0 ,1 ,2 pieces in his set

	
	@Test
	public void testPlayerandToString() {
		Player testPlayer = new Player("computer");
		assertEquals("computer",testPlayer.toString());
		
	}
	
	
	//cover the input : piece that has been added ,piece that has not been added
	@Test
	public void testaddPieceToHim()
	{
		Player testPlayer = new Player("computer");
		Piece newPiece = new Piece("test", testPlayer, new Position(1,1));
		assertTrue(testPlayer.addPieceToHim(newPiece));
		assertFalse(testPlayer.addPieceToHim(newPiece));
		
	}
	//cover the input : piece that has been added ,piece does not exist in his set
	@Test
	public void testRemoveFromHim()
	{
		Player testPlayer = new Player("computer");
		Piece newPiece = new Piece("test", testPlayer, new Position(1,1));
		testPlayer.addPieceToHim(newPiece);		
		assertTrue(testPlayer.removePieceFromHim(newPiece));
		assertFalse(testPlayer.removePieceFromHim(newPiece));
	}
	
	// covers the piece that belongs to this player,the piece that does not belongs to this player
	@Test
	public void testfindPiece()
	{
		Player testPlayer = new Player("computer");
		Piece newPiece = new Piece("test", testPlayer, new Position(1,1));
		Piece fakePiece = new Piece("test", testPlayer, new Position(1,1));
		Piece anotherPiece = new Piece("newtest",testPlayer,new Position(1,2));
		testPlayer.addPieceToHim(newPiece);		
		assertTrue(newPiece == testPlayer.findPiece(fakePiece));
		assertTrue(testPlayer.findPiece(anotherPiece)==null);
	}
	
	
	@Test
	public void testFindPieceByPosition()
	{
		Player testPlayer = new Player("computer");
		Piece newPiece = new Piece("test", testPlayer, new Position(1,1));
		testPlayer.addPieceToHim(newPiece);		
		assertTrue(newPiece == testPlayer.findPieceByPosition(new Position(1,1)));
		assertTrue(testPlayer.findPieceByPosition(new Position(1,2))==null);
	}

	//covers:0,1,2 pieces in the set of this player
	@Test
	public void getRemainingPieces()
	{
		Player testPlayer = new Player("computer");
		Piece newPiece = new Piece("test", testPlayer, new Position(1,1));
		Piece anotherPiece = new Piece("newtest",testPlayer,new Position(1,2));
		assertEquals(0,testPlayer.getRemainingPieces());
		testPlayer.addPieceToHim(newPiece);
		assertEquals(1,testPlayer.getRemainingPieces());
		testPlayer.addPieceToHim(anotherPiece);
		assertEquals(2,testPlayer.getRemainingPieces());
	}
	
	@Test
	public void testName()
	{
		Player testPlayer = new Player("computer");
		assertEquals("computer",testPlayer.name());
	}
	
	
	
}



