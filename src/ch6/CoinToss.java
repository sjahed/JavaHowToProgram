package ch6;

import java.security.SecureRandom;
import java.util.Scanner;

public class CoinToss {

	private static int hFreq, tFreq;
	
	private enum Coin { HEAD, TAIL};
	
	public static void main(String[]args){
		new CoinToss();
	}
	public CoinToss(){
		hFreq = 0;
		tFreq = 0;
		
		boolean exit = true;
		System.out.println("Welcome to Coin Toss Program!");
		
		do{
			int in = getInput();
			switch(in){
			case 1:
				System.out.println("\nYou flipped "+flip()+"\n");
				exit = false;
				break;
			case 2:
				if((gethFreq()+gettFreq()) != 0 )
					System.out.println("\n\n"+toString());
				System.out.println("Bye Bye!");
				exit = true;
				break;
			}
		}while(!exit);
	}
	
	public Coin flip(){
		
		Coin flipResult = null;
		SecureRandom r = new SecureRandom();
		
		switch(r.nextInt(2)){
		case 0:
			flipResult = Coin.HEAD;
			sethFreq(gethFreq()+1);
			break;
		case 1:
			flipResult = Coin.TAIL;
			settFreq(gettFreq()+1);
			break;
			
		}
		
		return flipResult;
	}

	public static int gethFreq() {
		return hFreq;
	}

	public static void sethFreq(int hFreq) {
		CoinToss.hFreq = hFreq;
	}

	public static int gettFreq() {
		return tFreq;
	}

	public static void settFreq(int tFreq) {
		CoinToss.tFreq = tFreq;
	}
	
	public void showMenu(){
		System.out.println("Your Options Are:");
		System.out.println("1: Flip Coin");
		System.out.println("2: Exit Program");
		System.out.print("Please Enter Your Choice: ");
		
	}
	public int getInput(){
		int userInput = -1;
		Scanner input = new Scanner(System.in);
		
		do{
			try{
				
				showMenu();
				userInput = input.nextInt();
				
			}catch(NumberFormatException e){
				System.out.println("Only numbers 1 or 2!");
			}catch(Exception e){
				System.out.println("Only numbers 1 or 2!");
			}
			if(userInput == 1 || userInput == 2){
				break;
			}
		}while(true);
	
		return userInput;
	}
	
	public String toString(){
		return "You Flipped coin "+(gettFreq()+gethFreq())+" times\n"
				+"Heads: "+gethFreq()
				+" Tails: "+gettFreq();
	}
}
