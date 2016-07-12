package ch16;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.text.html.HTMLDocument.Iterator;

public class AddingElementsInLinkedList {

	private static SecureRandom random;
	private static List<Integer> list;
	
	public static void main(String [] args){
		random = new SecureRandom();
		list = new LinkedList<Integer>();
		
		addIntegers(list);
		showLinkedList(list);
	}

	private static void addIntegers(List<Integer> list2) {
		// TODO Auto-generated method stub
		int counter=0;
		for(int i = 0; i < 25; i++){
			
			int newNumber = random.nextInt(101);
			list2.add(newNumber);
		}//end of for loop - number generation
	
		Collections.sort(list2);
	}//end of addIntegers()
	
	private static void showLinkedList(List<Integer> list2){
		for(Integer x: list2){
			System.out.printf("%d%n", x);
		}
	}
}
