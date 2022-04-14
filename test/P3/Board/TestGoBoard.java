package P3.Board;

import static org.junit.Assert.*;


import org.junit.Test;

import P3.Game.Player;
import P3.Piece.Piece;
import P3.Position.Position;

public class TestGoBoard extends TestBoardInstance{

	
	//Test strategy
	//putPiceOnBoard()
	// 		partition the position as follows:x =0,y=0  length>x>0,length>y>0 x<0,j<0  x>length,y>length
	//	 	the place has a piece,the place does not has a piece
	//makePositionEmpty()
	// 		partition the position as follows:x =0,y=0  length>x>0,length>y>0 x<0,j<0  x>length,y>length
	//		the place has a piece,the place does not has a piece
	@Override
    public Board emptyInstance()
    {
		return new ChessBoard();
    }
	
	@Test
	public void TestputPiece()
	{
		GoBoard newBoard = new GoBoard();
		Player newPlayer = new Player("tester");
		newBoard.initailBoard();
		Position firstPosition  = new Position(0,0);
		Position secondPosition  = new Position(-1,1);
		Position thirdPosition  = new Position(19,19);
		Piece firstPiece = new Piece("pawn", newPlayer, firstPosition);
		Piece secondPiece = new Piece("pawn", newPlayer, secondPosition);
		Piece thirdPiece = new Piece("pawn", newPlayer, thirdPosition);
		assertTrue(newBoard.putPiece(newPlayer, firstPiece, firstPosition));
		assertFalse(newBoard.putPiece(newPlayer, secondPiece, secondPosition));
		assertFalse(newBoard.putPiece(newPlayer, thirdPiece, thirdPosition));
		assertEquals(newPlayer+"'s "+"pawn",newBoard.getPosition(firstPosition));
		assertFalse(newBoard.putPiece(newPlayer, firstPiece, firstPosition));
	}
	@Test
	public void testMakePositionEmpty() {
		GoBoard newBoard = new GoBoard();
		Player newPlayer = new Player("tester");
		newBoard.initailBoard();
		Position firstPosition  = new Position(0,0);
		Position secondPosition  = new Position(-1,1);
		Position thirdPosition  = new Position(19,19);
		Piece firstPiece = new Piece("pawn", newPlayer, firstPosition);
		assertFalse(newBoard.makePositionEmpty(firstPosition));
		newBoard.putPiece(newPlayer, firstPiece, firstPosition);
		assertTrue(newBoard.makePositionEmpty(firstPosition));
		assertFalse(newBoard.makePositionEmpty(secondPosition));
		assertFalse(newBoard.makePositionEmpty(thirdPosition));		
	}
	
}
