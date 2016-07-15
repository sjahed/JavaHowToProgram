package ch18;

public class MazeTraversal {

	private static int stepCounter = 0;
	private static char[][] MAZE = {
										 {'#','#','#','#','#','#','#','#','#','#','#','#'},
								         {'#','.','.','.','#','.','.','.','.','.','.','#'},
								         {'.','.','#','.','#','.','#','#','#','#','.','#'},
								         {'#','#','#','.','#','.','.','.','.','#','.','#'},
								         {'#','.','.','.','.','#','#','#','.','#','.','.'},
								         {'#','#','#','#','.','#','.','#','.','#','.','#'},
								         {'#','.','.','#','.','#','.','#','.','#','.','#'},
								         {'#','#','.','#','.','#','.','#','.','#','.','#'},
								         {'#','.','.','.','.','.','.','.','.','#','.','#'},
								         {'#','#','#','#','#','#','.','#','#','#','.','#'},
								         {'#','.','.','.','.','.','.','#','.','.','.','#'},
								         {'#','#','#','#','#','#','#','#','#','#','#','#'}
								    };
				
	public static void main(String [] args){
		new MazeTraversal();
	}
	
	public MazeTraversal(){
	
		startMazeTraverse(MAZE, 2, 0);
	}
	
	
	private void startMazeTraverse(char [][] maze, int entryRowInd, int entryColInd){
		traverseMaze(maze, entryRowInd, entryColInd);
	}
	
	private boolean traverseMaze(char [][] maze, int rowInd, int colInd){
		stepCounter++;

		
		if(maze[rowInd][colInd] == '.' 
				&&(colInd == maze.length -1 || rowInd == 0 || rowInd == maze.length-1 || colInd == 0)
				&& stepCounter != 1){
			return true;
		}else{
			maze[rowInd][colInd] = 'X';
			
			if(canMove(maze, rowInd, colInd)){
		
				int [] newPos = getNextMoveCell(maze, rowInd, colInd,'.');
				
				
				//maze[newPos[0]][newPos[1]] = 'X';
				printMaze(maze);
				if(traverseMaze(maze, newPos[0], newPos[1]))
					return true;
				
			}else{
			
				maze[rowInd][colInd] = 'D';
				printMaze(maze);
				int [] newPos = getNextMoveCell(maze, rowInd, colInd,'X');
				
				if(traverseMaze(maze, newPos[0], newPos[1]))
					return true;
			}
		}
		
		return false;
	}
	
	private int [] getNextMoveCell(char[][] maze, int currentPosRow, int currentPosCol, char sign){
		int [] result = new int[2];
		
		
		//if cell empty in up
		int row = currentPosRow;
		int col = currentPosCol;
		if(--row >= 0 && maze[row][col] == sign){
			result[0] = row;
			result[1] = col;
			return result;
		}
		
		//if down cell
		row = currentPosRow;
		col = currentPosCol;
		if(++row < maze.length && maze[row][col] == sign){
			result[0] = row;
			result[1] = col;
			return result;
		}
		
		//if left cell
		row = currentPosRow;
		col = currentPosCol;
		if(--col >= 0 && maze[row][col] == sign){
			result[0] = row;
			result[1] = col;
			return result;
		}
		
		//if right cell
		row = currentPosRow;
		col = currentPosCol;
		if(++col < maze.length && maze[row][col] == sign){
			result[0] = row;
			result[1] = col;
			return result;
		}
		return result;
	}//end of method getNextMoveCell
	
	private boolean canMove(char[][] maze, int rowInd, int colInd){
		
		if(canMoveUp(maze, rowInd, colInd)
				|| canMoveDown(maze, rowInd, colInd)
				|| canMoveLeft(maze, rowInd, colInd)
				|| canMoveRight(maze, rowInd, colInd))
			return  true;
		
		return false;
	}
	
	private boolean canMoveUp(char [][] maze, int rowInd, int colInd){
		if(--rowInd > 0 && maze[rowInd][colInd] == '.')
			return true;
		return false;
	}
	
	private boolean canMoveDown(char [][] maze, int rowInd, int colInd){
		if(++rowInd < maze.length && maze[rowInd][colInd] == '.')
			return true;
		return false;
	}
	
	private boolean canMoveLeft(char [][] maze, int rowInd, int colInd){
		if(--colInd > 0 && maze[rowInd][colInd] == '.')
			return true;
		return false;
	}
	
	private boolean canMoveRight(char [][] maze, int rowInd, int colInd){
		if(++colInd < maze.length && maze[rowInd][colInd] == '.')
			return true;
		return false;
	}
	
	private void printMaze(char [][] maze){
		
		System.out.printf("%n%nStep: %d%n",stepCounter);
		for(int i = 0; i < maze.length; i++){
			for(int j = 0; j <maze[i].length; j++){
				System.out.printf("%2C", maze[i][j]);
			}
			System.out.println();
		}
		System.out.printf("%n%n");
	}
	
}
