package ch16;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class AlphabetCounter {

	public static Map<Character, Integer> list;
	
	public static void main(String [] args){
		list = new HashMap<>();
		createMap(list);
		showMap(list);
	}
	
	public static void createMap(Map<Character, Integer> list){
		Scanner input = new Scanner(System.in);
		String inputLine="";
		do{
			System.out.println("Enter a sentence");
			try{
				inputLine = input.nextLine();
				break;
			}catch(InputMismatchException e){
				continue;
			}catch(Exception e){
				continue;
			}
		}while(true);
		
		String[] splittedString = inputLine.split(" ");
		for(String word: splittedString){
			word = word.toLowerCase();
			for(int i=0; i<word.length(); i++){
				
				if(list.containsKey(word.charAt(i))){
					int count = list.get(word.charAt(i));
					list.put(word.charAt(i), count+1);
				}else{
					list.put(word.charAt(i), 1);
				}
			}
		}
	
	}//end of createMap()
	
	public static void showMap(Map<Character, Integer> list){
		
	
		Set<Character> sortedKeys = new TreeSet<>(list.keySet());
		for(Character c: sortedKeys){
			System.out.printf("%-4s%4s%n",c,list.get(c));
		}
	}
}
