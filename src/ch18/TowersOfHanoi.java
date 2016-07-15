package ch18;

public class TowersOfHanoi {

	public static void solveTowers(int disks, int srcPeg, int destPeg, int tempPeg){
		if(disks == 1){
			System.out.printf("%n%d ---> %d", srcPeg, destPeg);
			return;
		}
		
		solveTowers(disks-1, srcPeg, tempPeg, destPeg);
		
		
		System.out.printf("%n%d ---> %d", srcPeg, destPeg);
		solveTowers(disks-1, tempPeg, destPeg, srcPeg);
		
	}
	
	public static void main(String [] args){
		int startPeg = 1;
		int endPeg = 3;
		int tempPeg = 2;
		int totalDisks = 3;
		
		solveTowers(totalDisks, startPeg, endPeg, tempPeg);
	}
}
