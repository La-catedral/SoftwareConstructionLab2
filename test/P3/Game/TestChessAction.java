package P3.Game;

import static org.junit.Assert.*;

import org.junit.Test;

import P3.Board.Board;
import P3.Board.ChessBoard;
import P3.Piece.Piece;
import P3.Position.Position;

public class TestChessAction {

	//test strategy
		//put(Player p,Piece a,Position b)
		//		partition the inputs as follows:
		//		position: illegal position,legal position, position that is not empty
		//testPlayerCouldMove()
		//		partition: there is a piece on this place,isn't a piece on this place,illegal place
		//		and the piece belongs to another player
		//movePiece()
		//		partition the inputs as :
		//		position: from position is empty, to position has his piece,from position have other's piece 
		//		from position is illegal,to position is illegal
		//remove():
		//		this is a method being protected from client and only be used in eatPiece()
		//		so just invoke it one time ,and check the result
		//eatPiece():
		//		partition the inputs as :
		//		position: from position is empty, to eat position has his piece,from position have other's piece 
		//		from position is illegal,to position is illegal,from and to are same position
	
	//该棋子并非属于该 棋手、指定的位置超出棋盘的范围、
	 // 指定位置已有棋子、所指定的棋子已 经在棋盘上等
	@Test
	public void testPutPiece() {
		Board testBoard = new ChessBoard();
		Action testAction = new ChessAction((ChessBoard)testBoard);
		Player testPlayer = new Player("tester");
		Piece pieceA = new Piece("pawn",testPlayer, new Position(0,0));
		Piece pieceB = new Piece("pawn",testPlayer, new Position(-1,-1));
		Piece pieceC = new Piece("pawn",testPlayer, new Position(19,19));
		Piece pieceD = new Piece("pawn",testPlayer, new Position(0,0));
		testPlayer.addPieceToHim(pieceA);
		testPlayer.addPieceToHim(pieceB);
		testPlayer.addPieceToHim(pieceC);
		testPlayer.addPieceToHim(pieceD);
		assertTrue(testAction.put(testPlayer, pieceA,new Position(0,0)));
		assertFalse(testAction.put(testPlayer, pieceB,new Position(-1,-1)));
		assertFalse(testAction.put(testPlayer, pieceC,new Position(19,19)));
		assertFalse(testAction.put(testPlayer, pieceD,new Position(0,0)));
	}
	
	@Test
	//covers there is a piece on this place,isn't a piece on this place,illegal place
	//and the piece belongs to another player
	public void testPlayerCouldMove()
	{
		Board testBoard = new ChessBoard();
		Action testAction = new ChessAction((ChessBoard)testBoard);
		Player testPlayer = new Player("tester");
		Player anotherPlayer = new Player("anotherTester");
		Piece pieceA = new Piece("pawn",testPlayer, new Position(0,0));
		Piece pieceB = new Piece("pawn",anotherPlayer, new Position(1,1));
		testPlayer.addPieceToHim(pieceA);
		anotherPlayer.addPieceToHim(pieceB);
		testAction.put(testPlayer, pieceA,new Position(0,0));
		testAction.put(anotherPlayer,pieceB,new Position(1,1));
		assertTrue(((ChessAction)testAction).playerCouldMoveFrom(testPlayer, new Position(0,0)));
		assertFalse(((ChessAction)testAction).playerCouldMoveFrom(testPlayer, new Position(1,1)));
		assertFalse(((ChessAction)testAction).playerCouldMoveFrom(testPlayer, new Position(1,2)));
		assertFalse(((ChessAction)testAction).playerCouldMoveFrom(testPlayer, new Position(-1,-1)));
		assertFalse(((ChessAction)testAction).playerCouldMoveFrom(testPlayer, new Position(19,19)));	
	}
	
	//covers position is empty, to position has his piece,from position have other's piece 
	//		from position is illegal,to position is illegal
	@Test 
	public void testMovePiece()
	{
		Board testBoard = new ChessBoard();
		Action testAction = new ChessAction((ChessBoard)testBoard);
		Player testPlayer = new Player("tester");
		Player anotherPlayer = new Player("anotherTester");
		Piece pieceA = new Piece("pawn",testPlayer, new Position(0,0));
		Piece pieceB = new Piece("pawn",anotherPlayer, new Position(1,1));
		testPlayer.addPieceToHim(pieceA);
		anotherPlayer.addPieceToHim(pieceB);
		testAction.put(testPlayer, new Piece("pawn",testPlayer, new Position(0,0)),new Position(0,0));
		testAction.put(anotherPlayer, new Piece("pawn",testPlayer, new Position(1,1)), new Position(1,1));
		assertTrue(((ChessAction)testAction).move(testPlayer, new Position(0,0), new Position(1,2)));
		assertFalse(((ChessAction)testAction).move(testPlayer, new Position(1,2), new Position(1,1)));
		assertFalse(((ChessAction)testAction).move(testPlayer, new Position(1,1), new Position(2,2)));
		assertFalse(((ChessAction)testAction).move(testPlayer, new Position(1,2), new Position(-1,-1)));
		assertFalse(((ChessAction)testAction).move(testPlayer, new Position(1,2), new Position(19,19)));		
	}
	
	
	@Test
	public void testRemove()
	{
		Board testBoard = new ChessBoard();
		Action testAction = new ChessAction((ChessBoard)testBoard);
		Player testPlayer = new Player("tester");
		Piece pieceA = new Piece("pawn",testPlayer, new Position(0,0));
		testPlayer.addPieceToHim(pieceA);
		testAction.put(testPlayer, pieceA, new Position(0,0));
		((ChessAction)testAction).remove(testPlayer, pieceA);
		assertEquals(0,testPlayer.getRemainingPieces());
		assertTrue(testBoard.isEmpty(new Position(0,0)));
	}
	
	@Test
	public void testEatPiece()
	{
		Board testBoard = new ChessBoard();
		Action testAction = new ChessAction((ChessBoard)testBoard);
		Player testPlayer = new Player("tester");
		Player anotherPlayer = new Player("anotherTester");
		Piece pieceA = new Piece("pawn",testPlayer, new Position(0,0));
		Piece pieceB = new Piece("pawn",anotherPlayer, new Position(1,1));
		testPlayer.addPieceToHim(pieceA);
		anotherPlayer.addPieceToHim(pieceB);
		testAction.put(testPlayer, new Piece("pawn",testPlayer, new Position(0,0)),new Position(0,0));
		testAction.put(anotherPlayer, new Piece("pawn",testPlayer, new Position(1,1)), new Position(1,1));
		assertFalse(((ChessAction)testAction).eatPiece(testPlayer, anotherPlayer, new Position(0,0), new Position(19,19)));
		assertFalse(((ChessAction)testAction).eatPiece(testPlayer, anotherPlayer, new Position(0,0), new Position(-1,-1)));
		assertFalse(((ChessAction)testAction).eatPiece(testPlayer, anotherPlayer, new Position(0,0), new Position(0,0)));
		assertFalse(((ChessAction)testAction).eatPiece(testPlayer, anotherPlayer, new Position(0,0), new Position(2,2)));
		assertTrue(((ChessAction)testAction).eatPiece(testPlayer, anotherPlayer, new Position(0,0), new Position(1,1)));
	}
	

}
