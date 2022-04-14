package P3.Game;

import static org.junit.Assert.*;


import org.junit.Test;

import P3.Board.GoBoard;
import P3.Piece.Piece;
import P3.Position.Position;

public class TestGoAction {
	
	//test strategy
	//put(Player p,Piece a,Position b)
	//		partition the inputs as follows:
	//		position: illegal position,legal position, position that is not empty
	//removeGoPiece()
	//		partition the inputs as follows:
	//		position: illegal position,legal position but empty, position that have his piece,position that have other's piece

	//covers illegal position,legal position, position that is not empty
	@Test
	public void testPutPiece() {
		GoBoard testBoard = new GoBoard();
		GoAction testAction = new GoAction(testBoard);
		Player testPlayer = new Player("tester");
		assertTrue(testAction.put(testPlayer, new Piece("white",testPlayer, new Position(0,0)),new Position(0,0)));
		assertFalse(testAction.put(testPlayer, new Piece("white",testPlayer, new Position(-1,-1)),new Position(-1,-1)));
		assertFalse(testAction.put(testPlayer, new Piece("white",testPlayer, new Position(19,19)),new Position(19,19)));
		assertFalse(testAction.put(testPlayer, new Piece("white",testPlayer, new Position(0,0)),new Position(0,0)));
	}
	
	
	//covers illegal position,legal position but empty, position that have his piece,position that have other's piece
	@Test 
	public void testRemovePiece()
	{
		GoBoard testBoard = new GoBoard();
		GoAction testAction = new GoAction((GoBoard)testBoard);
		Player testPlayer = new Player("tester");
		Player anotherPlayer = new Player("anothertester");
		Piece pieceA = new Piece("white",testPlayer, new Position(0,0));
		Piece pieceB = new Piece("black",anotherPlayer, new Position(1,0));
		testPlayer.addPieceToHim(pieceA);
		anotherPlayer.addPieceToHim(pieceB);
		testAction.put(testPlayer, pieceA, new Position(0,0));
		testAction.put(anotherPlayer, pieceB, new Position(1,0));	
		assertFalse(testAction.removeGoPiece(testPlayer, anotherPlayer, new Position(0,0)));
		assertFalse(testAction.removeGoPiece(testPlayer, anotherPlayer, new Position(-1,-1)));
		assertFalse(testAction.removeGoPiece(testPlayer, anotherPlayer, new Position(8,8)));
		assertFalse(testAction.removeGoPiece(testPlayer, anotherPlayer, new Position(2,2)));
		assertTrue(testAction.removeGoPiece(testPlayer, anotherPlayer, new Position(1,0)));
	}
}
