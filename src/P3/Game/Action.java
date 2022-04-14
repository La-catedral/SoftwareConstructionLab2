package P3.Game;

import P3.Piece.Piece;
import P3.Position.Position;

public abstract class Action {

	/**
	 * put a piece on the board 
	 * @param whichPlayer,the player who would like to put the piece on board 
	 * @param whichPiece,the piece that will be put(must belong to this player) 
	 * @param where,the place to put
	 * @return true if succeed,else false
	 */
	public abstract boolean put(Player whichPlayer, Piece whichPiece, Position where);
}
