package P3.Game;

//import java.util.ArrayList;

//import java.util.List;

import P3.Board.Board;
import P3.Board.ChessBoard;
import P3.Piece.Piece;
import P3.Position.Position;

public class ChessGame extends Game{

	private final Board gameBoard = new ChessBoard();
	private final Action gameAction;
	private final Player Player1 ,Player2;
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
	public ChessGame(String player1Name,String player2Name) {
		this.gameAction = new ChessAction((ChessBoard)gameBoard);
		this.Player1 = new Player(player1Name);
		this.Player2 = new Player(player2Name);
		checkRep();
		initGame(this.Player1,this.Player2);
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
	 * initial the game
	 * @param Player1,this game's player1
	 * @param Player2,this game's player2
	 */
	private void initGame(Player Player1,Player Player2)
	{
		for(int i = 0;i < 8;i++) //put pawns
		{			
			putPieceOnBoard(Player1, "pawn", new Position(1,i));
			putPieceOnBoard(Player2, "pawn", new Position(6,i));
		}
		putPieceOnBoard(Player1,"rook",new Position(0,0));//rook
		putPieceOnBoard(Player1,"rook",new Position(0,7));
		putPieceOnBoard(Player2,"rook",new Position(7,0));
		putPieceOnBoard(Player2,"rook",new Position(7,7));
		
		putPieceOnBoard(Player1,"knight",new Position(0,1));
		putPieceOnBoard(Player1,"knight",new Position(0,6));
		putPieceOnBoard(Player2,"knight",new Position(7,1));
		putPieceOnBoard(Player2,"knight",new Position(7,6));
		
		putPieceOnBoard(Player1,"bishop",new Position(0,2));
		putPieceOnBoard(Player1,"bishop",new Position(0,5));
		putPieceOnBoard(Player2,"bishop",new Position(7,2));
		putPieceOnBoard(Player2,"bishop",new Position(7,5));
		
		putPieceOnBoard(Player1,"queen",new Position(0,3));
		putPieceOnBoard(Player1,"king",new Position(0,4));
		putPieceOnBoard(Player2,"queen",new Position(7,3));
		putPieceOnBoard(Player2,"king",new Position(7,4));
	
	}
	
	/**
	 * used to provide tool to other method of this class 
	 * @param playerToFind,the player that has same information
	 * @return
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
	
	//报错请重新放置  以后改为String 输入
	/** 
	 * put a piece that belongs to this player on board
	 * @param the player's id,the piece's name,the place you want to put 
	 * @return true if succeeded ,else false
	 */
	protected boolean putPieceOnBoard(Player newPlayer,String newpiece,Position newPosition)
	{
//		Player newPlayer = new Player(newplayer);
//		Position newPosition = new Position(Integer.parseInt(X),Integer.parseInt(Y));
		Piece newPiece = new Piece(newpiece, newPlayer, newPosition);
//		if((newPlayer = findRealPlayer(newPlayer))!=null) {
			newPlayer.addPieceToHim(newPiece);
			return gameAction.put(newPlayer, newPiece, newPosition);
//		}
//		else
//		{
//			System.out.println("There's no such player!");
//			return false;
//		}
	}
	
	/**
	 * move a piece of this player from one position to another
	 * @param newplayer ,the name of the player
	 * @param fromX,the x coordinate of where the piece from
	 * @param fromY,the y coordinate of where the piece from
	 * @param toX,the x coordinate of where the piece to
	 * @param toY,the y coordinate of where the piece to
	 * @return true if succeed, else false 
	 */
	public boolean movePiece(String newplayer,String fromX,String fromY,String toX,String toY)
	{
		Player newPlayer = new Player(newplayer);
		Position fromWhere = new Position(Integer.parseInt(fromX),Integer.parseInt(fromY));
		Position toWhere = new Position(Integer.parseInt(toX),Integer.parseInt(toY));
		if((newPlayer = findRealPlayer(newPlayer))!=null)
			return ((ChessAction)gameAction).move(newPlayer, fromWhere, toWhere);		
		else
			{
				System.out.println("There's no such player!");
				return false;
			}
	}
	
	/**
	 * move a piece of one of the player to a place where has already 
	 * existed a piece that belongs to another player
	 * @param newplayer's name, the x and y coordinate of from where and to where
	 * @return true if succeed,else false
	 */
	public boolean eatPiece(String newplayer,String fromX,String fromY,String toX,String toY)
	{
		Player newPlayer = new Player(newplayer);
		Position fromWhere = new Position(Integer.parseInt(fromX),Integer.parseInt(fromY));
		Position toWhere = new Position(Integer.parseInt(toX),Integer.parseInt(toY));
		if((newPlayer = findRealPlayer(newPlayer))!=null)
		{
			Player anotherPlayer ;
			if(newPlayer == Player1)
				anotherPlayer = Player2;
			else
				anotherPlayer = Player1;
			return ((ChessAction)gameAction).eatPiece(newPlayer, anotherPlayer,fromWhere, toWhere);		

		}
		else
			{
				System.out.println("There's no such player!");
				return false;
			}
	}
	
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
	
	
//	@Override
//	public boolean writeDownHistory(String playerName,String[] instructionOfThisTime)
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
//	@Override
//	public List<String[]> getHistoryInstructions(String playerName)
//	{
//		Player newPlayer = findRealPlayer(new Player(playerName));
//		newPlayer.
//	}
}
