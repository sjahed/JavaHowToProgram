package ch23;

import java.security.SecureRandom;
import java.util.Arrays;

public class SimpleArray {

	public static final SecureRandom generator = new SecureRandom();
	public final int[] array;
	public int writeIndex;
	
	public SimpleArray(int size){
		array = new int[size];
	}
	
	public synchronized void add(int value){
		int position = writeIndex;
		try{
			Thread.currentThread().sleep(generator.nextInt(5000));
		}catch(InterruptedException e){
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
		
		array[position] = value;
		System.out.printf("%s wrote %2d to element %d.%n",Thread.currentThread().getName(),
															value,position);
		++writeIndex;
		System.out.printf("Next Write index: %d%n", writeIndex);
	}
	
	public synchronized String toString(){
		return Arrays.toString(array);
	}
}
