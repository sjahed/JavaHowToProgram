package ch17;

import java.util.stream.IntStream;

public class IntStreamOperations {

	public static void main(String [] args){
		
		int [] values = {3,10,6,1,4,8,2,5,9,7};
		
		System.out.println(IntStream.of(values).summaryStatistics());
		
		System.out.print("Original Values: ");
		IntStream.of(values).forEach(value -> System.out.printf("%d ",value));
		System.out.println();
		System.out.printf("%nCount: %d%n",IntStream.of(values).count());
		System.out.printf("Min: %d%n", IntStream.of(values).min().getAsInt());
		System.out.printf("Max: %d%n", IntStream.of(values).max().getAsInt());
		System.out.printf("Sum: %d%n", IntStream.of(values).sum());
		System.out.printf("Average: %.2f%n", IntStream.of(values).average().orElse(0));
		
		System.out.printf("Sum via reduced method: %d%n", IntStream.of(values).reduce(0,(x,y) -> x+y));
		System.out.printf("Sum of squares via reduce method: %d%n",
						   IntStream.of(values).reduce(0, (x,y)-> x + y * y));
		
		System.out.printf("%nEven values displayed in sorted order: ");
		IntStream.of(values)
				 .filter(value -> value %2 == 0)
				 .sorted()
				 .forEach(value -> System.out.printf("%d ", value));
		System.out.println();
		
		System.out.printf("Odd values multiplied by 10 and displayed in sorted order:");
		IntStream.of(values)
				 .filter(value -> value % 2 == 1)
				 .map(value -> value * 10)
				 .sorted()
				 .forEach(value -> System.out.printf("%d ", value));
		System.out.println();
		
		System.out.printf("Sum of values from 1 to 10: %d",
				  IntStream.range(1, 10).sum());
		System.out.printf("%nOriginal array:");
		IntStream.of(values).forEach(value -> System.out.printf("%d ",value));
				 
	}
}
