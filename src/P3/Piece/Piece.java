package P3.Piece;

import P3.Game.Player;
import P3.Position.Position;

public class Piece {
	private final String nameOfPiece;//棋子的名字
	private Player belongTo; //属于那个棋手
	private Position itsPosition;//
	
	// Abstraction function:
    // 	AF(nameOfPiece,belongTo,itsPosition) = the name of the piece,who does it belongs to,and where is it
    // Representation invariant:
    //	all fields must be non null
    // Safety from rep exposure:
    //  all fields are private 
    //	all the mutators do not change the rep invariant
	//	the observer will make defensive copy
    
    // constructor
	public Piece(String nameOfPiece,Player belong, Position where) {

		this.nameOfPiece = nameOfPiece;
		this.belongTo = belong;
		this.itsPosition = where;
//		checkRep();
	}
	//checkRep()
	private void checkRep()
	{
//		assert nameOfPiece != null;
//		assert belongTo != null;
//		assert itsPosition != null;
	}
	
	/**
	 * get the position of this piece
	 * @return
	 */
	public Position getPosition()
	{
		Position getPosition = new Position(this.itsPosition.getX(), this.itsPosition.getY());
		return getPosition;
	}

	/**
	 * reset the position of this piece
	 * @param newPosition
	 */
	public void setPlace(Position newPosition)
	{
		itsPosition = new Position(newPosition.getX(), newPosition.getY());
	}

	
	@Override
	public String toString() {
		return nameOfPiece ;
	}
	
}
