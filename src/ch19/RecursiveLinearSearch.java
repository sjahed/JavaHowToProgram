package ch19;

public class RecursiveLinearSearch {

	public static void main(String [] args){
		int [] array = {1,2,4,32,12,2,34,5,23,9};
		
		System.out.println(recursiveLinearSearch(array, 2, 0));
	}
	
	public static int recursiveLinearSearch(int [] array, int key, int currentIndex){
		
		if(currentIndex == array.length){
			return -1;
		}else{
			if(array[currentIndex] == key){
				return currentIndex;
			}else{
				return recursiveLinearSearch(array, key, ++currentIndex);
			}
			
			
		}
		
	}
}
