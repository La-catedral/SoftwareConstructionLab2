package P3.Game;

//import java.util.ArrayList;


import P3.Board.Board;

import P3.Board.GoBoard;
import P3.Piece.Piece;
import P3.Position.Position;

public class GoGame extends Game{

	private Board gameBoard = new GoBoard();
	private Action gameAction;
	private Player Player1 ,Player2;
//	private ArrayList<String[]> historyInstruction = new ArrayList<>();	

	// Abstraction function:
    // 	AF(gameBoard,gameAction,Player1,Player2) = the board that you will play the game on,the two player that will play this game
	// and gameAction is the tool to play the game
    // Representation invariant:
    //	all fields must be non null,and the reference could not be changed
    // Safety from rep exposure:
    //  all fields are private and final
    //	all the mutators do not change the rep invariant
	//	the observer that will return an object is protected from the client
    
    // constructor
	public GoGame(String player1Name, String player2Name) {
		this.gameAction = new GoAction((GoBoard)gameBoard);
		Player1 = new Player(player1Name);
		Player2 = new Player(player2Name);
		checkRep();
	}
	// checkRep
		private void checkRep()
		{
			assert gameBoard != null;
			assert gameAction != null; 
			assert Player1 != null;
			assert Player2 != null;
		}
	
	/**
	 * find the real id of the wanted player
	 * @param cloned player who has the same information but different id
	 * @return the real player of this game
	 */
	protected Player findRealPlayer(Player playerToFind) //the unit test of this 
	{													//should extends this class
		if(Player1.name().equals(playerToFind.name())) //非null try catch
			return Player1;
		else if(Player2.name().equals(playerToFind.name()))
			return Player2;
		else
		{
			System.out.println("无此玩家");
			return null;
		}
	}
	
	/** 
	 * put a piece that belongs to this player on board
	 * @param the player's name,the piece's name,the x and y coordinate of the place you want to put 
	 * @return true if succeeded ,else false
	 */
	public boolean putPieceOnBoard(String newplayer,String newpiece,String X,String Y)
	{//得判断要放的棋子是不是属于该玩家颜色的棋子
		Player newPlayer = new Player(newplayer);
		Position newPosition = new Position(Integer.parseInt(X),Integer.parseInt(Y));
		Piece newPiece = new Piece(newpiece, newPlayer, newPosition);
		if((newPlayer = findRealPlayer(newPlayer))!=null)
		{
			if((newPlayer == Player1 && newpiece.equals("white"))||(newPlayer == Player2 && newpiece.equals("black")))
			{
				newPlayer.addPieceToHim(newPiece);
				if(gameAction.put(newPlayer, newPiece, newPosition) ==true)
				return true;
				else
				{
					newPlayer.removePieceFromHim(newPiece);
					return false;
				}
			}
			else
			{
				System.out.println("该类棋子不属于该玩家");
				return false;
			}
		}
		else
		{
			System.out.println("There's no such player!");
			return false;
		}
	}
	/**
	 * remove a existed piece of another player
	 * @param newplayer's name, the x and y coordinate of from where and to where
	 * @return true if succeed,else false
	 */
	public boolean removeOthersPieceFromBoard(String newplayer,String X,String Y)
	{
		Player newPlayer = new Player(newplayer);
		Position newPosition = new Position(Integer.parseInt(X),Integer.parseInt(Y));
		if((newPlayer = findRealPlayer(newPlayer))!=null)
		{
			if(newPlayer == Player1)
			{
				return ((GoAction)gameAction).removeGoPiece(Player1, Player2, newPosition);
			}
			else
				return ((GoAction)gameAction).removeGoPiece(Player2, Player1, newPosition);
		}
		else
		{
			System.out.println("There's no such player!");
			return false;
		}
		
	}
	
//	@Override
//	public boolean writeDownHistory(String[] instructionOfThisTime)
//	{
//		if(instructionOfThisTime != null)
//		{	
//		String[] safeInstruction = new String[instructionOfThisTime.length];
//		safeInstruction = instructionOfThisTime.clone();	//defensive copy
//		this.historyInstruction.add(safeInstruction);
//		return historyInstruction.contains(instructionOfThisTime);
//		}
//		else {
//			System.out.println("the string array is null!");
//			return false;
//		}
//	}
	
	@Override
	public boolean occupationOfThisPosition(String X,String Y)
	{
		Position thisPosition = new Position(Integer.parseInt(X),Integer.parseInt(Y));
		if(gameBoard.isLegalPlace(thisPosition))
		{
			if(gameBoard.isEmpty(thisPosition))
			{	
			System.out.println("This place is empty.");
			return false;
			}
			else
			{
				if(Player1.findPieceByPosition(thisPosition) != null)
					System.out.println(Player1.toString()+"'s piece is on this palce.");
				else
					System.out.println(Player1.toString()+"'s piece is on this palce.");
				return true;
			}
		}
		else
		{
			System.out.println("Illegal place!");
			return false;
		}
	}
	
	
	@Override
	public int getnumberOfRemainingPieces(String playerName)
	{
		Player newPlayer = new Player(playerName);
		if((newPlayer = this.findRealPlayer(newPlayer))!=null)
		{
			return newPlayer.getRemainingPieces();
		}
		else
		{
			System.out.println("There's no such player!");
			return -1;
		}
	}
	
	@Override 
	public void printBoard()
	{
		gameBoard.printBoard();
	}
}
