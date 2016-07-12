package ch16;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class DuplicateElimination {

	private static Set<String> nameList;
	private static Scanner inputReader;
	public static void main(String [] args){
		getInput();
		search();
	}
	public static  void getInput(){
		
		inputReader = new Scanner(System.in);
		nameList = new HashSet<String>();
		String in= "";
		do{
			try{
				System.out.println("Please Enter a Name:");
				in = inputReader.next();
				nameList.add(in);
			}catch(Exception e){
				continue;
			}
			
		}while(!in.equals("-9"));
		
	}
	public static void search(){
		if(nameList.contains(inputReader.next()))
			System.out.println("Name is in the list");
	}
	
	
}
