package ch14;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SearchString {

	private String inputString;
	private char targetChar;
	private int [] alphaFreq;
	private static final int NUM_ENG_ALPHABETS = 26;
	private static final char [] ENG_ALPHABETS = {'a','b','c','d','e','f','g','h','i','j',
											   'k','l','m','n','o','p','q','r','s','t',
											   'u','v','w','x','y','z'
												};
	public static void main(String [] args){
		
		new SearchString();
	}
	public SearchString(){
		alphaFreq = new int[NUM_ENG_ALPHABETS];
		inputString = getInput("Enter a string: ");
		targetChar = getInput("Enter a character to search for: ").charAt(0);
		System.out.println(howManyTimes(inputString, targetChar));
		howManyEachChar(inputString);
		for(int i = 0; i < NUM_ENG_ALPHABETS; i++){
			System.out.printf("%c appeared %d times \n", ENG_ALPHABETS[i], alphaFreq[i]);
		}
	
	}
	
	
	
	private String getInput(String message){
		Scanner input = new Scanner(System.in);
		String result = "";
		try{
			System.out.print(message);
			result = input.nextLine();
		}catch(InputMismatchException e){
			System.out.println("Enter only a string!");
		}
		
		return result.toLowerCase();
	}
	
	private int howManyTimes(String s, char c){
		int result = 0;
		while(s.length() != 0){
			if(s.indexOf(c,s.length()-1) != -1)
				result++;
			s = s.substring(0, s.length()-1);
		}
		return result;
	}
	private void howManyEachChar(String s){
		
		while(s.length() != 0){
			for(int i = 0; i < NUM_ENG_ALPHABETS; i++){
				if(s.indexOf(ENG_ALPHABETS[i], s.length()-1) != -1){
					alphaFreq[i]++;
				}
			}//end of for
			s = s.substring(0, s.length()-1);
		}//end of while
	}//end of howManyEachChar method
}
