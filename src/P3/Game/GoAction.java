package P3.Game;

//import P3.Board.ChessBoard;

import P3.Board.GoBoard;
import P3.Piece.Piece;
import P3.Position.Position;

public class GoAction extends Action{
	
	private GoBoard onThisBoard;
	
	// Abstraction function:
    // 	AF(onThisBoard) = the game board that we play on  
    // Representation invariant:
    //	onThisBoard must be non null,and the reference could not be changed
    // Safety from rep exposure:
    //  the field is private and final
    //	all the mutators do not change the rep invariant
    
    // constructor
	public GoAction(GoBoard onThisBoard) {
		this.onThisBoard = onThisBoard;
		checkRep();
	}
	// checkRep
		private void checkRep()
		{
			assert onThisBoard != null;
		}
		
	@Override
	public boolean put(Player whichPlayer, Piece whichPiece, Position where)
	{
	
			if(whichPlayer == null || whichPiece == null || where == null)
			
			{
				System.out.println("null!!!");
				return false;
			}
			else if(onThisBoard.isEmpty(where))// the place is legal and empty
			{
				whichPiece.setPlace(where);
				onThisBoard.putPiece(whichPlayer, whichPiece, where);
				return true;
			}
			else {
				return false;
			}	
	}

	/**
	 * this player remove a piece of another player on this position
	 * @param the player who wants to remove other's piece,the position of the piece to be moved 
	 * @return true if succeed ,else false
	 */
	public boolean removeGoPiece(Player thisPlayer,Player anotherPlayer,Position where)
	{
		Piece pieceToBeEaten;
		if(!onThisBoard.isLegalPlace(where))//if the position are out of board
		{
			return false;
		}
		if((pieceToBeEaten = anotherPlayer.findPieceByPosition(where))!= null)
		{
			onThisBoard.makePositionEmpty(where);
			thisPlayer.removePieceFromHim(pieceToBeEaten);
			return true;
		}
		else
		{
			System.out.println("该位置没有该玩家可以提的棋子");
			return false;
		}
	}

}
