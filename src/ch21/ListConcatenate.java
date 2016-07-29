package ch21;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class ListConcatenate {

	
	public static void concatenate(LinkedList<Character> fList, LinkedList<Character>sList){
		ListIterator<Character> iterator =  sList.listIterator();
		
		while(iterator.hasNext()){
			fList.addLast(iterator.next());
		}
	}
	
	public static void main(String [] args){
		
		SecureRandom randomChar = new SecureRandom();
		LinkedList<Character> l1 = new LinkedList<Character>();
		LinkedList<Character> l2 = new LinkedList<Character>();
		
		for(int i = 1; i < 5; i++){
			Integer number = randomChar.nextInt(130);
			l1.add(number.toString().charAt(0));
			number = randomChar.nextInt(130);
			l2.add(number.toString().charAt(0));
		}
		System.out.println("List 1 has:");
		printList(l1);
		System.out.println("List 2 has: ");
		printList(l2);
		concatenate(l1, l2);
		System.out.println("List 1 after concatenation: ");
		printList(l1);
		
		
	}
	
	public static void printList(List<Character> list){
		ListIterator iterator = list.listIterator();
		while(iterator.hasNext()){
			System.out.printf("%s ", iterator.next());
		}
		System.out.println();
	}
	
	
}
