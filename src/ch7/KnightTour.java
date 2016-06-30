package ch7;

import java.util.ArrayList;

import javax.accessibility.Accessible;

public class KnightTour {

	private final int BMIN = -1;
	private final int BMAX = 8;
	
	private final int [][] allowedMoves = {
			{2,-1}, {1,-2}, {-1,-2}, {-2,-1},
			{-2,1}, {-1,2}, {1,2}, {2,1}
	};
	private final int [][] accessbility = {
			{2,3,4,4,4,4,3,2},
			{3,4,6,6,6,6,4,3},
			{4,6,8,8,8,8,6,4},
			{4,6,8,8,8,8,6,4},
			{4,6,8,8,8,8,6,4},
			{4,6,8,8,8,8,6,4},
			{3,4,6,6,6,6,4,3},
			{2,3,4,4,4,4,3,2}
	};

	private int currentXPos;
	private int currentYPos;
	
	private boolean [][]visited = new boolean[8][8];
	int totalVisited;
	
	public KnightTour(int xStartPos, int yStartPos){
		totalVisited = 0;
		currentXPos = xStartPos;
		currentYPos = yStartPos;
		
		//System.out.println(startTour());
	}
	
	private void updateKnightPosition(int newX, int newY){
		
		currentXPos = newX;
		currentYPos = newY;
	}
	
	public int startTour(){
		
		
		while(movePossible(currentXPos, currentYPos)){
			
			Integer [][] nxtPosofKnight;
			if( (nxtPosofKnight = getNextMove(currentXPos, currentYPos)) != null){
				
				updateKnightPosition(nxtPosofKnight[0][0], nxtPosofKnight[0][1]);
				visited[currentXPos][currentYPos] = true;
				totalVisited++;
				
			}
			
		}
		
		
		return totalVisited;
	}
	
	public void step(){
		
		if(movePossible(currentXPos,currentYPos)){
			Integer [][] nxtPosofKnight;
			if( (nxtPosofKnight = getNextMove(currentXPos, currentYPos)) != null){
				
				updateKnightPosition(nxtPosofKnight[0][0], nxtPosofKnight[0][1]);
				visited[currentXPos][currentYPos] = true;
				totalVisited++;
				
			}
		}
	}
	
	public int[] getCurrentPosition(){
		int [] result = {currentXPos,currentYPos};
		return result;
	}
	
	public int getTotalVisited(){
		return totalVisited;
	}
	private boolean movePossible(int x, int y){
		boolean result = false;
		int hMoveValuePos = 0;
		int vMoveValuePos = 1;
		
		for(int [] nm : allowedMoves){
			
			int nX = x + nm[hMoveValuePos];
			int nY = y + nm[vMoveValuePos];
			
			//if x,y are within boundary and has not been visited
			if((nX > BMIN && nX < BMAX) && ( nY > BMIN && nY < BMAX) && !visited[nX][nY]){
				result = true;
				break;
			}
		}
		return result;
		
	}
	
	private ArrayList<Integer[][]> possibleMoves(int x, int y){
		
		ArrayList<Integer[][]> pm = new ArrayList<Integer[][]>();
		int hMoveValuePos = 0;
		int vMoveValuePos = 1;
	
		for(int [] nm : allowedMoves){
			
			int nX = x + nm[hMoveValuePos];
			int nY = y + nm[vMoveValuePos];
			
			//if x,y are within boundary and has not been visited
			if((nX > BMIN && nX < BMAX) && ( nY > BMIN && nY < BMAX) && !visited[nX][nY]){
				Integer[][] m = new Integer[1][2];
				m[0][0] = nX;
				m[0][1] = nY;
				
				pm.add(m);
				
			}
		}
		return pm;
		
	}
	
	private Integer[][] getNextMove(int oldX, int oldY){
		Integer [][] result = null;
		
		ArrayList<Integer[][]> possibleMoves = possibleMoves(oldX, oldY);
		
		for(Integer [][] move : possibleMoves){
			
			if(result == null)
				result = move;
			else{
				
				if(accessbility[result[0][0]][result[0][1]] > accessbility[move[0][0]][move[0][1]])
					result = move;
				
			}
		}
		
		return result;
	}
	
	
	
//	public static void main(String [] args){
//		new KnightTour(0,0);
//	}
}
