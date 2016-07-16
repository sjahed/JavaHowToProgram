package ch20;

public class Demo {

	public static <T> void printArray(T[] inputArray){
		for(T element : inputArray){
			System.out.printf("%s ", element);
		}
	}
}
