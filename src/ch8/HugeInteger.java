package ch8;

import java.math.BigInteger;

public class HugeInteger {

	public static void main(String [] args){
		HugeInteger one = new HugeInteger("400000000000000000000000000000000000000");
		System.out.println(one.isEqual(new HugeInteger("400000000000000000000000000000000000000")));
	
		
	}
	
	private static final int MAX_STORAGE_SIZE = 40;
	private final int [] STORAGE;
	private int largestDigitPos;
	
	public HugeInteger(int number){
	
		this(Integer.toString(number));
		
	}
	public HugeInteger(String number){
		STORAGE = new int[MAX_STORAGE_SIZE];
		largestDigitPos = MAX_STORAGE_SIZE;
		parse(number);
	}
	
	public String toString(){
		
		String numInString = "";
		for(int i = largestDigitPos; i < STORAGE.length; i++){
			numInString += STORAGE[i];
		}
		return numInString;
	}
	
	private BigInteger toBigInt(String number){
		
		return new BigInteger(number);
	}
	
	public BigInteger add(HugeInteger number){
		BigInteger inputVar = toBigInt(number.toString());
		BigInteger thisVar  = toBigInt(this.toString());
		
		return thisVar.add(inputVar);
	}
	public BigInteger subtract(HugeInteger number){
		
		return toBigInt(this.toString()).subtract(toBigInt(number.toString()));
	}
	
	public void parse(String acceptedString){
		
		if(acceptedString.isEmpty() || acceptedString == null){
			System.out.println("Please provide a number");
			return;
		}
		
		for(int i = acceptedString.length()-1; i >= 0; i--){
			
			STORAGE[--largestDigitPos] = Character.getNumericValue(acceptedString.charAt(i));
		}
	}
	
	/*
	 * check if its zero
	 */
	public boolean isZero(){
		boolean result = true;
		
		for(int a : STORAGE){
			if(a != 0){
				result = false;
				break;
			}
		}
		
		return result;
	}//end of isZero
	
	public boolean isEqual(HugeInteger number){
		
		return (toBigInt(this.toString()).equals(toBigInt(number.toString())));
		
		
	}
	public void isNotEqual(){
		
	}
	public void isGreaterThan(){
		
	}
	public void isLessThan(){
		
	}
	public void isGreaterThanOrEqualTo(){
		
	}
	public void isLessThanOrEqualTo(){
		
	}
}
