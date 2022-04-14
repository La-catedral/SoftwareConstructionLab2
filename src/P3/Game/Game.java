package P3.Game;

//import java.util.List;



public abstract class Game {

	
	/**
	 * print the board
	 */
	public abstract void printBoard();
	
	/**
	 * find out if there's a piece on this place
	 * @param the x and y coordinate of the position
	 * @return true if there have one, else return false
	 */
	public abstract boolean occupationOfThisPosition(String X,String Y);
	
//	/**
//	 * put the instructions of the player in the history list,
//	 * for example, "player1","put","3","2" is a series of instructions of one round
//	 *@param the array of the instructions of one time 
//	 *@return if the instructions are add to the list successfully or not
//	 */
//	public abstract boolean writeDownHistory(String playerName,String[] instructionOfThisTime);
	
	/**
	 * get the number of the left pieces
	 * @param the name of the Player you want to check
	 * @return the number of his remaining pieces
	 */
	public abstract int getnumberOfRemainingPieces(String playerName);
	
	
//	/**
//	 * get the history instructions of this player
//	 * @param the name of the player who wants to check his history instructions
//	 * @return his history instructions
//	 */
//	public abstract List<String[]> getHistoryInstructions(String playerName);
}
