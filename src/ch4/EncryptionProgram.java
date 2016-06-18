package ch4;

import java.util.Scanner;

/*
 * This program reads a four digit number from user, 
 * encrypts it by: 
 * 			Replace each digit with the result of adding 7 
 * 			to the digit and getting the remainder after dividing the 
 * 			new value by 10. Then swap the first digit with the third, 
 * 			and swap the second digit with the fourth
 * and can decrypt it
 */
public class EncryptionProgram {

	private int userNumber;
	private String encryptedNumber;
	private int unencryptedNumber;
	
	public static void main(String []args){
		
		new EncryptionProgram();
	}
	
	public EncryptionProgram(){
		
		userNumber = getInput();
		System.out.println(encryptedNumber = encrypt(userNumber));
		unencryptedNumber= decrypt(encrypt(userNumber));
		System.out.println(unencryptedNumber);
		
	}
	public String encrypt(int input){
		
		int fstD = ((input / 1000)+7)%10;
		int sndD = (((input / 100)%10)+7)%10;
		int trdD = (((input / 10 )% 10)+7)%10;
		int fthD = ((input % 10)+7)%10;
		
		
		return trdD+""+fthD+fstD+sndD;
		
	}
	public int decrypt(String inputString){
		
		int fstD = Integer.parseInt(Character.toString(inputString.charAt(0)));
		int sndD = Integer.parseInt(Character.toString(inputString.charAt(1)));
		int trdD = Integer.parseInt(Character.toString(inputString.charAt(2)));
		int fthD = Integer.parseInt(Character.toString(inputString.charAt(3)));
		
		fstD = ((fstD+10) - 7)%10;
		sndD = ((sndD+10) - 7)%10;
		trdD = ((trdD+10) - 7)%10;
		fthD = ((fthD+10) - 7)%10;
		
		return (trdD*1000)+(fthD*100)+(fstD*10)+sndD;
	}
	
	public int getInput(){
		int userNumber;
		while(true){
			try{
				
				boolean inputCorrect = false;
				Scanner input = new Scanner(System.in);
				System.out.println("Please Enter a four digit number: ");
				do{
					userNumber = input.nextInt();
				
					if(desiredInput(userNumber)){
						inputCorrect = true;
					}else{
						System.out.println("Please Enter a four digit number only!");
					}
				}while(!inputCorrect);
				return userNumber;
			}catch(NumberFormatException e){
				System.out.println("Please enter number only!");
			}catch(Exception e){
				System.out.println("Input Exception");
				
			}
		}
		
		
		//return userNumber;
	}
	
	public boolean desiredInput(int input){
		boolean result = true;
		
		int reminder = 0;
		int divider = 1000;
		
		reminder = input / divider;
		
		if(reminder < 1 || reminder > 9){
			result = false;
		}
		 
		return result;
	}
	
	public String toString(){
		String message = "User Entered: "+userNumber+"\nEncrypted Integer: "+encryptedNumber+" \n"
				+"Unecrypted Number: "+unencryptedNumber;
		return message;
	}
	
	
}
