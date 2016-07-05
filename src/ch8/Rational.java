package ch8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Rational {

	public static void main(String [] args){
		Rational r = new Rational("1/33");
		Rational r2 = new Rational();
		r2.addFractions("1/3", "1/3");
	}
	
	private int nominator;
	private int denominator;
	private String inputFraction;
	String[] partsOfFraction;
	
	public Rational(){
		
	}
	public Rational(String fraction){
		inputFraction = fraction;
	
		partsOfFraction = fraction.split("/");
		reduceFraction(Integer.parseInt(partsOfFraction[0]),
				Integer.parseInt(partsOfFraction[1]));
		
	}
	
	private void setNominator(int num){
		nominator = num;
	}
	private void setDenominator(int num){
		denominator = num;
	}
	private int getNominator(){
		return nominator;
	}
	private int getDenominator(){
		return denominator;
	}
	private String getInputFraction(){
		return inputFraction;
	}
	
	private void addFractions(String a , String b){
		
		List<Integer> result = new ArrayList<Integer>();
		int newFraNom, newFraDen;
		
		int nomFraA = Integer.parseInt(a.split("/")[0]);
		int denFraA = Integer.parseInt(a.split("/")[1]);
		int nomFraB = Integer.parseInt(b.split("/")[0]);
		int denFraB = Integer.parseInt(b.split("/")[1]);
		
		if(denFraA != denFraB){
			newFraNom = nomFraA * denFraB + nomFraB * denFraA;
			newFraDen = denFraA * denFraB;
			result = reduceFraction(newFraNom, newFraDen);
		}else{
			newFraNom = nomFraA + nomFraB;
			newFraDen = denFraB;
			result = reduceFraction(newFraNom, newFraDen);
		}
		
		System.out.println(result.get(0)+"/"+result.get(1));
	}
	
	private List<Integer> reduceFraction(int a, int b){
		
		List<Integer> result = new ArrayList<Integer>();
		int commonDivider = 0;
		commonDivider = GCF(a,b);
		result.add(a/commonDivider);
		result.add(b/commonDivider);
		return result;
	}
	
	/*
	 * given two integers, this method finds GCF
	 * it will first find primeFactors of each
	 * then it will find intersection of both numbers,
	 * at last it will find the greatest prime number that can
	 * divide both numbers and then returns it
	 */
	private int GCF(int a, int b){
		
		List<Integer> commonElements= intersection(primeFactors(a), primeFactors(b));
		Collections.sort(commonElements);
		
		for(int index = commonElements.size() - 1; index > 0; index--){
			if(a % commonElements.get(index) == 0 && b % commonElements.get(index) == 0)
				return commonElements.get(index);
		}
		return 1;
	}//end of GCF method
	
	/*
	 * returns intersection of two given lists
	 */
	private <T> List<T> intersection(List<T> listA, List<T> listB){
		List<T> intersectionOfTwoLists = new ArrayList<T>();
		
		for(T item: listA){
			if(listB.contains(item))
				intersectionOfTwoLists.add(item);
		}
		return intersectionOfTwoLists;
		
	}
	/*
	 * initiates an array as all true, picks the first prime number
	 * that has its value set as true, and searches for its multiple
	 * all its multiples will be set as false. 
	 * it will return an arraylist that contains prime factors of 
	 * the number
	 */
	private List<Integer> primeFactors(int number){
		
		boolean[] primeNumbers = new boolean[number];
		List<Integer> list = new ArrayList<Integer>();
		
		for(int i = 0; i < primeNumbers.length; i++){
			primeNumbers[i] = true;
		}//end of for loop - sets the array as true
		
		//integer 1 is prime factor for all numbers, including 1
		list.add(1);
		
		for(int i = 2; i < primeNumbers.length; i++){
			if(!primeNumbers[i])
				continue;
			
			list.add(i);
			for(int j = i+i; j < primeNumbers.length; j = j + i){
				
				if(!primeNumbers[j])
					continue;
				
				primeNumbers[j] = false;
			}//end of for loop - searches for multiples of int given to it
		}//end of for loop - selects prime number from the list
		return list;
	}//end of primeFactors method
}
