package P3.Game;

//import P3.Board.Board;


import P3.Board.ChessBoard;
import P3.Piece.Piece;
import P3.Position.Position;

public class ChessAction extends Action{

	private final ChessBoard onThisBoard;
	
	// Abstraction function:
    // 	AF(onThisBoard) = the game board that we play on  
    // Representation invariant:
    //	onThisBoard must be non null,and the reference could not be changed
    // Safety from rep exposure:
    //  the field is private and final
    //	all the mutators do not change the rep invariant
    
    // constructor
	public ChessAction(ChessBoard onThisBoard) {
		this.onThisBoard =onThisBoard;
		checkRep();
	}
    // checkRep
	private void checkRep()
	{
		assert onThisBoard != null;
	}
	

	@Override
	public boolean put(Player whichPlayer,Piece whichPiece,Position where)
	{
		if(whichPlayer == null || whichPiece == null || where == null)
		
		{
			System.out.println("null!!!");
			return false;
		}
		else if(onThisBoard.isEmpty(where))// the place is legal and empty
		{
			if(whichPlayer.containsPiece(whichPiece))
			{
				whichPiece.setPlace(where);
				onThisBoard.putPiece(whichPlayer, whichPiece, where);
				return true;
			}
			else 
			{
				System.out.println("wrong piece!");
				return false;
			}
		}
		else {
			return false;
		}
	}
	
	/**
	 * test if the given player could move his piece from the given place
	 * @param whichPlayer,the player that want to move his piece
	 * @param fromWhere,from where you want to move
	 * @return true if you there's a exact piece of yours at this place,else false
	 */
	public boolean playerCouldMoveFrom(Player whichPlayer,Position fromWhere)
	{
		
		if(onThisBoard.isEmpty(fromWhere))
		{
			System.out.println("初始位置没有可移动的棋子！");
			return false;
		}
		if(whichPlayer.findPieceByPosition(fromWhere)!=null)
		{
			return true;
		}
		else
		{
			System.out.println("该位置上的棋子不属于该玩家");
			return false;
		}
	}
	
	/**
	 * move a piece of this player which has existed on the board to another place on this board
	 * @param whichPlayer,the player who want to move his piece
	 * @param fromWhere,the place of the piece that you want to move now
	 * @param toWhere,the place you want to move it to
	 * @return true if you succeeded,else false 
	 */
	public  boolean move(Player whichPlayer,Position fromWhere, Position toWhere)
	{
		if(fromWhere.equals(toWhere))
		{
			System.out.println("same position!");
			return false;
		}
		Piece newPiece ;
		if(playerCouldMoveFrom(whichPlayer, fromWhere))
		{
			if(!onThisBoard.isEmpty(toWhere))
			{
				System.out.println("目的地已有其他棋子！");
				return false;
			}
			newPiece = whichPlayer.findPieceByPosition(fromWhere);
			put(whichPlayer, newPiece, toWhere) ;
			onThisBoard.makePositionEmpty(fromWhere);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * remove the piece from the game,just to help the method eatPiece()
	 * @param thisPlayer,whom the piece belongs to
	 * @param thisPiece,the exact piece you want to remove
	 */
	protected void remove(Player thisPlayer,Piece thisPiece)
	{
		onThisBoard.makePositionEmpty(thisPiece.getPosition());
		thisPlayer.removePieceFromHim(thisPiece);
	}
	
	/**
	 * help this player eat a piece of another player
	 * @param whichPlayer,the player who want to eat other's piece
	 * @param anotherPlayer,the player whose piece could be eaten
	 * @param fromWhere,the position of the piece which will eat other piece
	 * @param toWhere,the position of the piece that will be eaten
	 * @return true if succeeded,else false
	 */
	public boolean eatPiece(Player whichPlayer,Player anotherPlayer,Position fromWhere, Position toWhere)
	{
		if(fromWhere.equals(toWhere))
		{
			System.out.println("same position!");
			return false;
		}
		Piece pieceTobeEaten;
		if((pieceTobeEaten = anotherPlayer.findPieceByPosition(toWhere))!= null)//the target position actually
		{																		//have a piece of another player
			if(playerCouldMoveFrom(whichPlayer, fromWhere))							
			{
				remove(anotherPlayer,pieceTobeEaten);
				move(whichPlayer, fromWhere, toWhere);
				return true;
			}
			else{
				return false;
			}			
		}
		else{	
			System.out.println("那个位置没有能吃的棋子");
			return false;
		}
	}
	
}
