package ch19;

import java.util.Arrays;

public class BubbleSortDemo {

	
	static int [] numbers = {23,12,56,123,45,41,2,7,41,875,34,2};
	
	public static void main(String [] args){
		System.out.println("Before sort:"+Arrays.toString(numbers));
		//enhancedBubbleSort(numbers);
		//bubbleSort(numbers);
		enhancedBubbleSort2(numbers);
		System.out.println("After sort: "+Arrays.toString(numbers));
	}
	
	public static void bubbleSort(int [] array){
		
		for(int i = 0; i < array.length; i++){
			for( int j = 1; j < array.length; j++){
				if(array[j-1] > array[j]){
					int temp = array[j-1];
					array[j-1] = array[j];
					array[j] = temp;
				}
			}
		}
	}
	
	public static void enhancedBubbleSort(int [] array){
		int counter = array.length - 1;
		while(counter >= 0){
			
			for( int j = 1; j < array.length; j++){
				if(array[j-1] > array[j]){
					int temp = array[j-1];
					array[j-1] = array[j];
					array[j] = temp;
				}
			}
			counter--;
		}
	}
	
	public static void enhancedBubbleSort2(int [] array){
		
		boolean flag = true;
		while(true){
			flag = false;
			for( int j = 1; j < array.length; j++){
				if(array[j-1] > array[j]){
					int temp = array[j-1];
					array[j-1] = array[j];
					array[j] = temp;
					flag = true;
				}
			}
		}//end of while
	}//end of enhancedBubbleSort2
}
