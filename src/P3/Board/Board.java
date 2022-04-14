package P3.Board;

import P3.Position.Position;

public abstract class Board {
	/**
	 * return the information that this place hold
	 * for example,return "empty" or "playerA's white piece"
	 * @param thisPlace,the position you want to check
	 * @return the information that this place hold
	 */
	public abstract String getPosition(Position thisPlace);
	
	
	/**
	 * initial the game board
	 */
	public abstract void initailBoard();
	
	/**
	 * print out the game board
	 */
	public abstract void printBoard();
	
	/**
	 * check if the position was legal to this board 
	 * @param where,the position you gonna check
	 * @return true if legal,else false
	 */
	public abstract boolean isLegalPlace(Position where);

	/**
	 * check if the position is empty on this board
	 * @param where,the position you gonna check
	 * @return true if empty,else false
	 */
	public abstract boolean isEmpty(Position where);
}
