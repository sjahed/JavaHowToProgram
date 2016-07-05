package ch8;

import java.util.Scanner;
import java.util.regex.Pattern;

public class TicTacToe {

	private static final int BOARD_SIZE = 3;
	private static final int MAX_NUM_MOVES = (int) Math.pow(BOARD_SIZE, 2);
	private enum BoardMarks { X , O, EMPTY };
	private BoardMarks [][] board;
	
	private final Pattern inputPattern = Pattern.compile("^[0-9][0-9]");
	
	private final String emptyCellMarker = "___";
	private final String p1CellMarker = "_X_";
	private final String p2CellMaker = "_O_";
	private int numMoves;
	private boolean p1;
	
	public TicTacToe(){
		
		numMoves = 0;
		board = new BoardMarks[BOARD_SIZE][BOARD_SIZE];
		for(int i = 0; i < board.length; i++){
			for( int j = 0; j < board[i].length; j++){
				board[i][j] = BoardMarks.EMPTY;
			}//end of inner for loop initialize board
		}//end of outer for loop to initialize board
		run();
		
	}
	
	private void run(){
		
		p1 = true;
		int [] lastMove;
		String playerTurn;
		drawBoard();
		do{
			if(p1){
				playerTurn = "One";
				p1 = false;
			}
			else{
				playerTurn = "Two";
				p1 = true;
			}
			
			lastMove = getInput(playerTurn);
			modifyBoard(lastMove);
			
			numMoves++;
			drawBoard();
		}while(!winner(lastMove) && numMoves < MAX_NUM_MOVES);
		
		if(playerTurn.equals("One") && winner(lastMove))
			System.out.println("Player One Won the Game!");
		else if(playerTurn.equals("Two") && winner(lastMove))
			System.out.println("Player Two Won the Game!");
		else
			System.out.println("Game ended in a draw!");
	}
	
	private void modifyBoard(int [] move){
		if(p1){
			board[move[0]][move[1]] = BoardMarks.X;
		}else{
			board[move[0]][move[1]] = BoardMarks.O;
		}
	}
	/*
	 * this method gets two integers
	 * from user, where each respresents
	 * a [row][col] index
	 */
	private int [] getInput(String player){
		
		final int DIVIDER = 10;
		Scanner input = new Scanner(System.in);
		int row;
		int col;
		
		System.out.printf("Player %s, ",player);
		do{

			try{
				System.out.printf("Enter your move address: ");
				String line = input.nextLine();
				
				if(!inputPattern.matcher(line).matches()){
					System.out.println("Please enter only a two numbers" +
							"as 01 - where 0 represents row index" +
							"and 1 represents column index");
					continue;
				}
				row = Integer.parseInt(line)/DIVIDER;
				col = Integer.parseInt(line)%DIVIDER;
				
				if(board[row][col] != BoardMarks.EMPTY){
					System.out.println("Cell not empty!");
					continue;
				}
				break;
				
			}catch(Exception ex){
				System.out.println("Enter only numbers please!");
				
			}
		}while(true);
		int [] result = {row, col};
		return result;
	}
	
	/*
	 * this method checks if there is a winner, by
	 * looking thorough diagonal, horizontal,vertical
	 * cells that are on a board
	 */
	private boolean winner(int [] lastMove){
		
		if(checkDiagonal(lastMove))
			return true;
		else if(checkVertical(lastMove))
			return true;
		else if(checkVertical(lastMove))
			return true;
		
		return false;
	}
	
	/*
	 * this method checks is a diagonal have all same 
	 * marks, meaning one player wins
	 */
	private boolean checkDiagonal(int [] lastMove){
		
		final int START_POS = 0;
		boolean result = false;
		

		//get index and marker of the last move
		int rowInd = lastMove[0];
		int colInd = lastMove[1];
		BoardMarks m = board[rowInd][colInd];
		
		//check cells before lastMove cell
		while(--rowInd >= START_POS && --colInd >= START_POS){
			if(board[rowInd][colInd] != m){
				return result;
			}
		}
		
		rowInd = lastMove[0];
		colInd = lastMove[1];
		//check cells after lastMove cell
		while(++rowInd < BOARD_SIZE && ++colInd < BOARD_SIZE){
			if(board[rowInd][colInd] != m){
				return result;
			}
		}
		result = true;
		return result;
	}//end of checkDiagonal()
	
	/*
	 * this method checks if there is a winner
	 * by checking cells that are below/top of
	 * last move cell
	 */
	private boolean checkVertical(int [] lastMove){
		boolean result = false;
		final int START_POS = 0;
		
		int rowInd = lastMove[0];
		int colInd = lastMove[1];
		BoardMarks m = board[rowInd][colInd];
		
		while(--rowInd >= START_POS){
			if(board[rowInd][colInd] != m){
				return result;
			}
		}
		
		rowInd = lastMove[0];
		colInd = lastMove[1];
		//check cells after lastMove cell
		while(++rowInd < BOARD_SIZE){
			if(board[rowInd][colInd] != m){
				return result;
			}
		}
		
		return result;
	}//end of checkVertical method
	
	/*
	 * this method checks if there is a winner
	 * by checking left/right of the lastMove cell
	 * if they have all the same mark
	 */
	private boolean checkHorizontal(int [] lastMove){
		boolean result = false;
		final int START_POS = 0;
		
		int rowInd = lastMove[0];
		int colInd = lastMove[1];
		BoardMarks m = board[rowInd][colInd];
		
		while(--colInd >= START_POS){
			if(board[rowInd][colInd] != m){
				return result;
			}
		}
		
		rowInd = lastMove[0];
		colInd = lastMove[1];
		//check cells after lastMove cell
		while(++colInd < BOARD_SIZE){
			if(board[rowInd][colInd] != m){
				return result;
			}
		}
		
		return result;
	}
	
	private void drawBoard(){

		
		//first row label
//		System.out.printf("%6s %5s %5s\n",String.format("%d%-1s",0," "),
//				String.format("%d%-1s",1," "),
//				String.format("%d%-1s",2," "));
		
		for(int i=0 ; i < BOARD_SIZE;i++){
			
			System.out.printf("%d",i);
			for(int j=0; j < BOARD_SIZE;j++){
				
				if(board[i][j] == BoardMarks.EMPTY)
					System.out.printf("%5s ",emptyCellMarker);
				else if(board[i][j] == BoardMarks.X)
					System.out.printf("%5s ", p1CellMarker);
				else if(board[i][j] == BoardMarks.O)
					System.out.printf("%5s ", p2CellMaker);
			}//end of inner for loop
			System.out.println();
		}//end of outer for loop
	}
	
	public static void main(String [] args){
		new TicTacToe();
	}
}
