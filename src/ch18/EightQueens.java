package ch18;

public class EightQueens {

	
	private static int NUM_QUEENS;
	private int boardSize;
	private int [][] board;
	
	public EightQueens(int size){
		if(size > 24)
			System.exit(1);
		
		NUM_QUEENS = size;
		boardSize = size;
		board = new int[boardSize][boardSize];

		System.out.println(solveProblem(board, 0, NUM_QUEENS));
		printBoard();
	}
	
	/*
	 * starting from left most column, check if it is safe
	 * to put queen there, if so, then recursively check other
	 * options, if it wasn't, then move to the next col
	 */
	
	private boolean solveProblem(int[][] board, int colIndex ,int NUM_QUEENS){
		
		if(NUM_QUEENS == 0){
			return true;
		}else{
			
			//put in each row of that column (column number is passed as parameter of method)
			for(int rowIndex = 0; rowIndex < boardSize; rowIndex++){
				//check if given row,col is safe
				if(isSafe(board, rowIndex, colIndex)){
					//if safe, then place queen there
					board[rowIndex][colIndex] = 1;
					//recursively solve for all other options
					if(solveProblem(board, ++colIndex, --NUM_QUEENS)){
						return true;
					}
					
									
					//if not safe, then remove queen from there and put it somewhere else
					board[rowIndex][--colIndex] = 0;
					++NUM_QUEENS;
				}
				
			}//end of for loop - check all rows of column
		}
		return false;
	}
	
	
	private boolean isSafe(int [][] board, int row, int col){
		if(checkHor(board,row,col)
				&& checkVer(board,row, col)
				&& checkRec(board, row, col)){
			return true;
		}else{
			return false;
		}
	}
	
	
	private boolean checkRec(int[][] board, int row, int col) {
		
		int rowIn = row;
		int colIn = col;
		//check rectangle
		while(++rowIn < boardSize && ++colIn < boardSize){
			if(board[rowIn][colIn] == 1)
				return false;
		}
		rowIn = row;
		colIn = col;
		while(--rowIn >= 0 && --colIn >= 0)
			if(board[rowIn][colIn] == 1)
				return false;
		
		rowIn = row;
		colIn = col;
		
		while(++rowIn < boardSize && --colIn >= 0)
			if(board[rowIn][colIn] == 1)
				return false;
		
		rowIn = row;
		colIn = col;
		while(--rowIn >= 0 && ++colIn < boardSize)
			if(board[rowIn][colIn] == 1)
				return false;
		
		return true;
	}

	private boolean checkHor(int[][] board, int row, int col) {
		int rowIn = row;
		int colIn = col;
		while(++colIn < boardSize)
			if(board[rowIn][colIn] == 1)
				return false;
		
		colIn = col;
		while(--colIn >= 0)
			if(board[rowIn][colIn] == 1)
				return false;
		
		return true;
	}

	private boolean checkVer(int[][] board, int row, int col) {
		
		int rowIn = row;
		int colIn = col;
		
		while(++rowIn < boardSize)
			if(board[rowIn][colIn] == 1)
				return false;
		rowIn = row;
		while(--rowIn >= 0)
			if(board[rowIn][colIn] == 1)
				return false;
		
		return true;
	}

	private void printBoard(){
		System.out.printf("%n%n");
		for(int i = 0; i < boardSize; i++){
			for(int j = 0; j < boardSize; j++){
				if(board[i][j] == 1)
					System.out.printf("%4s", "Q");
				else
					System.out.printf("%4s", "*");
			}
			System.out.println();
		}
		System.out.printf("%n%n");
	}
	
	public static void main(String [] args){
		new EightQueens(24);
	}
}
