package P3.MyGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import P3.Game.ChessGame;
import P3.Game.Game;
import P3.Game.GoGame;

public class MyChessAndGoGame {

	public static void printGoHelp()
	{
		System.out.println("******HELP******");
		System.out.println("a :put a piece on the board");
//		System.out.println("b :remove another player's piece");
		System.out.println("b :check if the place was occupied");
		System.out.println("c :get the left number of on-board pieces of two players");
		System.out.println("e :end the game");
		System.out.println("p :pass this round");
	}
	
	public static void printChessHelp()
	{
		System.out.println("******HELP******");
		System.out.println("a :move your piece");
		System.out.println("b :eat another player's piece");
		System.out.println("c :check if the place was occupied");
		System.out.println("d :get the left number of on-board pieces of two players");
		System.out.println("e :end the game");
	}
	
	/**
	 * start a new game
	 */
	 public static void main(String[] args)  {
		 
		 Scanner nameScan = new Scanner(System.in);
			System.out.println("Please input the name of first Player:");
			String nameOfPlayer1 = nameScan.nextLine();
			System.out.println("Please input the name of second Player:");
			String nameOfPlayer2 = nameScan.nextLine();
//			nameScan.close();
		 
		 System.out.println("Please choose the type of the game:Chess or Go:");
//		 Scanner gameTypeScan = new Scanner(System.in);
		 String inputString = nameScan.nextLine();
		
		 //make the instance of the game
		 Game newGame;//it will be an instance after you choose the type
		 while(true)
		 {			if(inputString.equals("Chess"))
				 {
			 		
					newGame = new ChessGame(nameOfPlayer1,nameOfPlayer2) ;//start chess game
					 break;
				 }
				 else if(inputString.equals("Go"))
				 {
					 newGame = new GoGame(nameOfPlayer1,nameOfPlayer2);//start go game
					 break;
				 }
				 else {
					 System.out.println("Wrong type!Please input again:");
					 inputString = nameScan.nextLine();
				 }
		}
//		 gameTypeScan.close();
		 System.out.println("3~2~1~GAME BEGIN!");
		 List<String[]> historyInstructionsOfPlayer1 = new ArrayList<>();
		 List<String[]> historyInstructionsOfPlayer2 = new ArrayList<>();
		 
		 OUT:
		 if(inputString.equals("Chess"))//chess game 
		 {
			 printChessHelp();
			 while(true)
			 {
				 System.out.println();
				 System.out.println("********A New Round********");
				 System.out.println("Input \"h\" to get help. ");
				 System.out.println();
				 for(int i =0;i<2;i++)
				 {
					 if(i == 0)
					 {
						 System.out.println("Now it's "+nameOfPlayer1+"'s turn,please choose one of the instructions.");
					 }
					 else
					 {
						 System.out.println("Now it's "+nameOfPlayer2+"'s turn,please choose one of the instructions.");
					 }
					 ((ChessGame)newGame).printBoard();
//					 Scanner newScanner = new Scanner(System.in);
					 thisOUT:
					 while(true)
					 {
						 switch(nameScan.nextLine())
						 {
						 	case "a":
						 		System.out.println("please input:player's name,coordinate x of the position of your piece,coordinate y,coordinate x you want to get to,coordinate y you want to get to.");
						 		System.out.println("and please split them by space\" \", for example,you can input \"Tom 2 1 3 1\"");
						 		String[] instructionString = nameScan.nextLine().split(" ");
						 		while(!((ChessGame)newGame).movePiece(instructionString[0], instructionString[1], instructionString[2], instructionString[3], instructionString[4]))
						 		{
						 			System.out.println("Please input again");
							 		instructionString = nameScan.nextLine().split(" ");
						 		}
						 		if(i==0)
						 			historyInstructionsOfPlayer1.add(instructionString);
						 		else
						 			historyInstructionsOfPlayer2.add(instructionString);	
						 		break thisOUT;
						 	case "b":
						 		System.out.println("please input:your name,coordinate x of the position of your piece,coordinate y,coordinate x of the piece you want to eat,coordinate y of the piece you want to eat.");
						 		System.out.println("and please split them by space\" \", for example,you can input \"Tom 2 1 3 1\"");
						 		String[] instructionString2 = nameScan.nextLine().split(" ");
						 		while(!((ChessGame)newGame).eatPiece(instructionString2[0], instructionString2[1], instructionString2[2], instructionString2[3], instructionString2[4]))
						 		{
						 			System.out.println("Please input again");
							 		instructionString2 = nameScan.nextLine().split(" ");
						 		}
						 		if(i==0)
						 			historyInstructionsOfPlayer1.add(instructionString2);
						 		else
						 			historyInstructionsOfPlayer2.add(instructionString2);
						 		break thisOUT;
						 	case "c":
						 		System.out.println("Please input the x and y coordinate of the place that you want to check.");
						 		System.out.println("and please split them by space\" \", for example,you can input\"3 2\"");
						 		String[] instructionString3 = nameScan.nextLine().split(" ");
						 		String keepOrNot;
						 		do {
						 		while(!Character.isDigit(instructionString3[0].charAt(0)) ||!Character.isDigit(instructionString3[1].charAt(0)))
						 		{
					 				System.out.println("Illegal input,please input agian:");
					 				instructionString3 = nameScan.nextLine().split(" ");
						 		}
						 		newGame.occupationOfThisPosition(instructionString3[0],instructionString3[1]);
						 		
						 			if(i==0)
							 			historyInstructionsOfPlayer1.add(instructionString3);
							 		else
							 			historyInstructionsOfPlayer2.add(instructionString3);
						 			System.out.println("input 'yes' to input agian,else to to next round:");
						 			keepOrNot=nameScan.nextLine();
						 		}while(keepOrNot.equals("yes"));						 		
						 		break thisOUT;
						 		
						 	case "d":
						 		System.out.println(nameOfPlayer1+": "+newGame.getnumberOfRemainingPieces(nameOfPlayer1));
						 		System.out.println(nameOfPlayer2+": "+newGame.getnumberOfRemainingPieces(nameOfPlayer2));
						 		String[] checkString = {"check ","remaining pieces' number"};
						 		if(i==0)
						 			historyInstructionsOfPlayer1.add(checkString);
						 		else
						 			historyInstructionsOfPlayer2.add(checkString);
						 		break thisOUT;
						 	case "e":
						 		System.out.println("Game Over!");
						 		break OUT;
						 	case "h":
						 		printChessHelp();
						 		String[] helpString = {"help"};
						 		if(i==0)
						 			historyInstructionsOfPlayer1.add(helpString);
						 		else
						 			historyInstructionsOfPlayer2.add(helpString);
						 		break;
						 	case "p":
						 		String[] passString = {"pass"};
						 		if(i==0)
						 			historyInstructionsOfPlayer1.add(passString);
						 		else
						 			historyInstructionsOfPlayer2.add(passString);
						 		break;
						 	default :
						 		System.out.println("Your input seems not legal,please write again~ ");
						 }
					 }	 
				 }
			 }		 
		 }
		 else//Go game
		 {
			 printGoHelp();
			 while(true)
			 {
				 System.out.println();
				 System.out.println("********A New Round********");
				 System.out.println("Input \"h\" to get help. ");
				 System.out.println();
				 for(int i =0;i<2;i++)
				 {
					 if(i == 0)
					 {
						 System.out.println("Now it's "+nameOfPlayer1+"'s turn,please choose one of the instructions.");
					 }
					 else
					 {
						 System.out.println("Now it's "+nameOfPlayer2+"'s turn,please choose one of the instructions.");
					 }
					 ((GoGame)newGame).printBoard();
//					 Scanner newScanner = new Scanner(System.in);
					 thatOUT:
						 while(true)
						 {
							 switch(nameScan.nextLine())
							 {
							 	case "a":
							 		System.out.println("Please input :player's name,white or black piece,x coordinate of the position that you want to put,and y coordinate");
							 		System.out.println("and please split them by space \" \",for example:\"Mike white 2 3 \"");
							 		String[] instructionString = nameScan.nextLine().split(" ");
							 		while(!((GoGame)newGame).putPieceOnBoard(instructionString[0],instructionString[1] , instructionString[2],instructionString[3] ))
							 		{
							 			System.out.println("Please input again");
								 		instructionString = nameScan.nextLine().split(" ");
							 		}
							 		if(i==0)
							 			historyInstructionsOfPlayer1.add(instructionString);
							 		else
							 			historyInstructionsOfPlayer2.add(instructionString);
							 		break thatOUT;
//							 	case "b":
//							 		System.out.println("Please input :player's name,white or black piece,x coordinate of the position that you want to put,and y coordinate");
//							 		System.out.println("and please split them by space \" \",for example:\"Mike white 2 3 \"");
//							 		String[] instructionString1 = newScanner.nextLine().split(" ");
//							 		while(!((GoGame)newGame).removeOthersPieceFromBoard(newplayer, X, Y)(instructionString1[0],instructionString1[1] , instructionString1[2],instructionString1[3] ))
//							 		{
//							 			System.out.println("Please input again");
//								 		instructionString1 = newScanner.nextLine().split(" ");
//							 		}
//							 		if(i==0)
//							 			historyInstructionsOfPlayer1.add(instructionString1);
//							 		else
//							 			historyInstructionsOfPlayer2.add(instructionString1);
//							 		
//							 		break thatOUT;
							 	case "b":
							 		System.out.println("Please input the x and y coordinate of the place that you want to check.");
							 		System.out.println("and please split them by space\" \", for example,you can input\"3 2\"");
							 		String[] instructionString3 = nameScan.nextLine().split(" ");
							 		String keepOrNot;
							 		do {
							 		while(!Character.isDigit(instructionString3[0].charAt(0)) ||!Character.isDigit(instructionString3[1].charAt(0)))
							 		{
						 				System.out.println("Illegal input,please input agian:");
						 				instructionString3 = nameScan.nextLine().split(" ");
							 		}
							 		newGame.occupationOfThisPosition(instructionString3[0],instructionString3[1]);
							 		
							 			if(i==0)
								 			historyInstructionsOfPlayer1.add(instructionString3);
								 		else
								 			historyInstructionsOfPlayer2.add(instructionString3);
							 			System.out.println("input 'yes' to input agian,else to to next round:");
							 			keepOrNot=nameScan.nextLine();
							 		}while(keepOrNot.equals("yes"));
							 		break thatOUT;
							 	case "c":
							 		System.out.println(nameOfPlayer1+": "+newGame.getnumberOfRemainingPieces(nameOfPlayer1));
							 		System.out.println(nameOfPlayer2+": "+newGame.getnumberOfRemainingPieces(nameOfPlayer2));
							 		String[] checkString = {"check ","remaining pieces' number"};
							 		if(i==0)
							 			historyInstructionsOfPlayer1.add(checkString);
							 		else
							 			historyInstructionsOfPlayer2.add(checkString);
							 		break thatOUT;
							 	case "e":
							 		System.out.println("Game Over!");
							 		break OUT;
							 	case "h":
							 		printGoHelp();
							 		String[] helpString = {"help"};
							 		if(i==0)
							 			historyInstructionsOfPlayer1.add(helpString);
							 		else
							 			historyInstructionsOfPlayer2.add(helpString);
							 		break;
							 	case "p":
							 		String[] passString = {"pass"};
							 		if(i==0)
							 			historyInstructionsOfPlayer1.add(passString);
							 		else
							 			historyInstructionsOfPlayer2.add(passString);
							 		break;
							 	default :
							 		System.out.println("Your input seems not legal,please write again~ ");
							 }
						 } 
					 
					 anotherOUT:
					 while(true)
					 {
						 System.out.println("Do you need to remove one of his piece from the board: y or n");
						 String removeOrNot = nameScan.nextLine();
						 switch(removeOrNot)
						 {
						 	case "y":
						 		System.out.println("Please input :your name,white or black piece,x coordinate of the position that you want to put,and y coordinate");
						 		System.out.println("and please split them by space \" \",for example:\"Mike white 2 3 \"");
						 		String[] instructionString1 = nameScan.nextLine().split(" ");
						 		while(!((GoGame)newGame).removeOthersPieceFromBoard(instructionString1[0], instructionString1[1], instructionString1[2]))
						 		{
						 			System.out.println("Please input again");
							 		instructionString1 = nameScan.nextLine().split(" ");
						 		}
						 		if(i==0)
						 			historyInstructionsOfPlayer1.add(instructionString1);
						 		else
						 			historyInstructionsOfPlayer2.add(instructionString1);
						 		break;
						 	case "n":
						 		break anotherOUT;
						 	default:
						 		System.out.println("Illegal input,please input again~");
						 		removeOrNot = nameScan.nextLine();
						 }
					 }
				 }
			 }	 
		 }
		 nameScan.close();
		 
		 System.out.println();
		 System.out.println(nameOfPlayer1+"'s history instructions:");
		 for(String[] instructionStrings :historyInstructionsOfPlayer1)
		 {
		
			 for(String thisone:instructionStrings)
			{
				 System.out.print(thisone+" ");
			}
			 System.out.println();
		 }
		 System.out.println();
		 System.out.println(nameOfPlayer2+"'s history instructions:");
		 for(String[] instructionStrings :historyInstructionsOfPlayer2)
		 {
			 for(String thisone:instructionStrings)
				{
					 System.out.print(thisone+" ");
				}
				 System.out.println();
		 } 
		 
		 
	}
}
	 
