package ch14;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TextAnalyzer {

	private int [] alphaFreq;
	private static final int NUM_ENG_ALPHABETS = 26;
	private static final char [] ENG_ALPHABETS = {'a','b','c','d','e','f','g','h','i','j',
											   'k','l','m','n','o','p','q','r','s','t',
											   'u','v','w','x','y','z'
												};
	private String inputString;
	
	public static void main(String [] args){
		new TextAnalyzer();
	}
	public TextAnalyzer(){
		
		inputString = getInput("Please enter a sentence below:\n");
		alphaFreq = alphabetFreqCounter(inputString);
		
		for(int i=0 ; i < NUM_ENG_ALPHABETS; i++){
			if(alphaFreq[i] > 0){
				System.out.printf("%c appeared %d times\n", ENG_ALPHABETS[i], alphaFreq[i]);
			}
		}
	}
	
	
	private int[] alphabetFreqCounter(String message){
		int [] result = new int[NUM_ENG_ALPHABETS];
		
		int counter = 0;
		char c;
		while(counter < message.length()){
			c = message.charAt(counter);
			for(int i = 0 ; i < NUM_ENG_ALPHABETS; i++){
				if( c == ENG_ALPHABETS[i]){
					result[i]++;
					break;
				}
			}
			
			counter++;
		}
		
		return result;
	}
	private String getInput(String message){
		Scanner input = new Scanner(System.in);
		String inputString ="";
		do{
			try{
				System.out.println(message);
				inputString = input.nextLine();

				if(inputString.matches("[A-Za-z]+[a-z\\s]*[.|?]?"))
					break;
				else
					System.out.println("Please enter a valid input!");
			}catch(InputMismatchException e){
				System.out.println("Fromat Exception! Please enter a valid input!");
			}
		}while(true);
		
		return inputString.toLowerCase();
	}
}
