package ch7;

import java.util.ArrayList;
import java.util.Arrays;

import javax.accessibility.Accessible;

public class KnightTour {

	public static void main(String [] args){
		new KnightTour();
	}
	private final int BOARD_DIM = 8;
	
	Knight knight;
	Board board;
	public KnightTour(){
		knight = new Knight(0,0);
		board = new Board();
	
		int moveNumber = 0;
		board.changeSquare(knight.getPos(), knight.getCount());//mark the start position
		//starting from x,y check if the next possible move position of knight
		//is inside the board and whether that positoin has already been visited
		//if both of those were true, then check the next move.
		//if not visited and the moves lands inside the board then move the knight 
		//to the new position, mark the board as visited, change the next move number to
		//zero and repeat it
		//continue this until you land in a position where you can't move
		do{
			if(board.isInside(knight.nextPosCoord(moveNumber)) &&
				!board.visited(knight.nextPosCoord(moveNumber))	){
				knight.move(moveNumber);//move the knight to new position, and increment counter
				board.changeSquare(knight.getPos(), knight.getCount());
				moveNumber = 0;//start from position zero again
			}//end of if(knightCanMove && isNotVisited)
			
			moveNumber++;
		}while(moveNumber < 8);
		
		System.out.format("Knight moved %d times\n\n", knight.getCount());
		board.printBoard();
		
	}//end of KnightTour()
	

	
	class Board{
		int [][] board;
		
		Board(){
			board = new int[BOARD_DIM][BOARD_DIM];
			for(int []row: board)
				Arrays.fill(row, -1);
		}//end of Board()
		
		//if a board has any number equal and bigger than
		//0, it means it was visited by the knight and changed
		boolean visited(int []coords){
			return (board[coords[0]][coords[1]] >= 0 )? true:false;
		}
		
		//end when board is full
		boolean isFull(){
			for(int i = 0; i < board.length; i++)
				for(int j = 0; j < board[i].length; j++)
					if(board[i][j] == 0)
						return false;
			return true;
		}//end of isFull()
		
		//change the value of square with a value
		//the coords are the position of knight and value 
		//is the move number of knight
		void changeSquare(int[] coords, int value){
			board[coords[0]][coords[1]] = value;
		}
		
		//position 0 is x value and position 1
		//represents y value of coordinates
		boolean isInside(int[]coords){
			return ((coords[0] >= 0 && coords[0] < board.length) &&
					(coords[1] >= 0 && coords[1] < board.length)) ? true : false;
		}//end of isInsideBoard
		
		//print the board,
		void printBoard(){
			for(int i = 0; i < board.length; i++){
				for(int j = 0; j < board[i].length; j++)
					System.out.format("%4d",board[i][j]);
				System.out.format("\n");
			}//end of outer for()
		}//end of printBoard
		
	}//end of Board
	
	class Knight{
		
		//the list of possible moves of a knight 
		//on a board is 8. it's row and col movement
		//for each move is in the table below for moves
		//0 to 7.
		int []horizontal = {2,1,-1,-2,-2,-1,1,2};
		int []vertical = {-1,-2,-2,-1,1,2,2,1};
		int x, y;//x,y position of the knight
		int counter;//to count number of moves
		
		Knight(){
			this(0,0);
		}
		Knight(int x, int y){
			
			this.x = x;
			this.y = y;
			counter = 0;
			
			
		}//end of Knight(x,y)
		
		
		int [] getPos(){
			return new int[]{x,y};
		}

		int getCount(){
			return counter;
		}
		
//		//check if knight can move from current position
//		boolean canMove(int moveN){
//			int []nextPos = getPos();
//			nextPos[0] += vertical[moveN];
//			nextPos[1] += horizontal[moveN];
//			
//			return isInsideBoard(nextPos);
//		}//end of canMove(x,y)
		
		//get knights next position based on move
		int[] nextPosCoord(int moveN){
			int []nextPos = getPos();
			nextPos[0] += vertical[moveN];
			nextPos[1] += horizontal[moveN];
			return nextPos;
		}//end of nextPosCoord(moveN)
		
		void move(int moveN){
			x += vertical[moveN];
			y += horizontal[moveN];
			counter++;
		}//end of move
		

		
	}//end of Knight
	
}//end of KnightTour
