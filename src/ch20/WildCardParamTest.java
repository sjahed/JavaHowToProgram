package ch20;

import java.util.ArrayList;

public class WildCardParamTest {

	
	public static void main(String [] args){
		int [] intList = {1,3,4,2,5,3,4};
		double[] doubleList = {1.3,23.2,234.2};
		Number[] numberList = {1,3,4,2,5,3,4,1.3,23.2,234.2};
		
		ArrayList<Integer> intAList = new ArrayList<Integer>();
		for(int n: intList){
			intAList.add(n);
		}
		
		ArrayList<Double> doubAList = new ArrayList<Double>();
		for(double n: doubleList)
			doubAList.add(n);
		ArrayList<Number> numAList = new ArrayList<Number>();
		for(Number n: numberList)
			numAList.add(n);
		
		System.out.println(sum(intAList));
		System.out.println(sum(doubAList));
		System.out.println(sum(numAList));
	}

	private static double sum(ArrayList<? extends Number> list) {
		
		double total = 0;
		for(Number e: list)
			total += e.doubleValue();
		
		return total;
	}
	
	
}
