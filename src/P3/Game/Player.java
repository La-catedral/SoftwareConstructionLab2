package P3.Game;

//import java.util.ArrayList;
import java.util.HashSet;

import java.util.Set;

import P3.Piece.Piece;
import P3.Position.Position;

public class Player {
	private final String playerName;
	private Set<Piece> owningPieces = new HashSet<>();
	// Abstraction function:
    // 	AF(playerName,owningPieces) = the name of this player and his pieces  
    // Representation invariant:
    //	the Piece in owningPiecews must be non-null 
    // Safety from rep exposure:
    //  all fields are private 
    //	mutators are all protected and do not be exposed to the client 
     
	//constructor
	public Player(String playerOne)
	{
			this.playerName = playerOne;
		}
		
    // checkRep
	private void checkRep()
	{
		for(Piece thisPiece:owningPieces)
		{
			assert thisPiece!= null;
		}
	}
	
	/**
	 * get the name of this player
	 * @return the name of this player
	 */
	public String name()
	{
		return playerName; 
	}
	
	/**
	 * check out if this player has a piece which has similar inforamtion with this one
	 */
	public boolean containsPiece(Piece thisPiece)
	{
		for(Piece newPiece:owningPieces)
		{
			if(newPiece.toString().equals(thisPiece.toString()))
			return true;
		}
		return false;
	}
	/**
	 * find a non null piece which belong to this player
	 * @param the piece that has the same information with the piece you want to get
	 * @return the real piece of this player
	 */
	protected Piece findPiece(Piece thisOne)
	{
		if(containsPiece(thisOne))
		{
			Piece realPiece = null;//the set contains the piece thus realPiece woulnd't be null
			for(Piece pieceInSet : owningPieces)
			{
				OUT:
				if(pieceInSet.toString().equals(thisOne.toString()))
				{
					realPiece = pieceInSet;
					break OUT;
				}
			}
			checkRep();
			return realPiece;
		}
		else {
			System.out.println("the Piece wrong");
			checkRep();
			return null;
		}
		 
	}
	
	
	/**
	 * get one of the piece of this player by input its position
	 * @param where , the position of the piece you want to get
	 * @return the piece you want to get
	 */
	protected Piece findPieceByPosition(Position where)
	{
		boolean hasFound = false;
		Piece result = null;
		OUT:
		for(Piece pieceInSet: owningPieces)
		{
			if(pieceInSet.getPosition().equals(where))
			{
				hasFound = true;
				result = pieceInSet;
				break OUT;
			}
		}
		if(hasFound == false)
		System.out.println("该玩家没有这个棋子");
		checkRep();
			return result;
		
	}
	/**
	 * get the number of this player's remianing pieces
	 * @return the number of this player's remianing pieces
	 */
	public int getRemainingPieces()
	{
		return this.owningPieces.size();
	}
	
	/**
	 * add a new piece to this player
	 * @param newPiece,the piece you want to add to him
	 */
	protected boolean addPieceToHim(Piece newPiece)
	{
		if(newPiece != null)
		{
			boolean result = owningPieces.add(newPiece);
			checkRep();
			return result;
		}
		
		else
			System.out.println("null piece!");
			return false;
	}
	
	//need to find the piece itself
	//input this piece and remove it from the set
	
	/**
	 * remove a piece from this player
	 * @param piece, the exact piece you want to move from him
	 */
	protected boolean removePieceFromHim(Piece piece)
	{
		if(piece != null)
			{
			  	boolean result = owningPieces.remove(piece);
			  	checkRep();
			  	return result;
			}
		else
			{
				System.out.println("null piece!");
				return false;
			}
	}


	@Override
	public String toString() {
		return  playerName ;
	}
	
	
}
