package ch21;

import java.security.SecureRandom;

public class OrderedList<T extends Comparable<T>> {

	public static void main(String[] args){
		String alphabents = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		SecureRandom random = new SecureRandom();
		OrderedList<Character> list = new OrderedList<Character>();
		List<Character> backwardList = new List<Character>("Backwardlist");
		
		System.out.println("Following numbers will be added: ");
		for(int i = 0; i < 20; i++){
			Character c = alphabents.charAt(random.nextInt(alphabents.length()));
			System.out.printf("%s ", c);
			list.insert(c);
			backwardList.insertAtFront(c);
		}
		System.out.println();
		list.print();
		System.out.println();
		backwardList.print();
		System.out.println();
		backwardList.printListBackward();
	}
	private OrderedListNode<T> firstNode;
	private OrderedListNode<T> lastNode;
	private String name;
	
	public OrderedList(){
		this("Ordered List");
	}
	
	public OrderedList(String name){
		this.name = name;
		firstNode = lastNode = null;
	}
	public void insert(T insertItem){
		
		if(isEmpty()){
			firstNode = lastNode = new OrderedListNode<T>(insertItem);
			
		}else if(insertItem.compareTo(firstNode.getData()) < 0){
			OrderedListNode<T> newNode = new OrderedListNode<T>(insertItem);
			newNode.nextNode = firstNode;
			firstNode = newNode;
			
		}else{
			OrderedListNode<T> before = firstNode;
			OrderedListNode<T> current = before.nextNode;
			
			while(current != null){
				if(insertItem.compareTo(current.getData()) < 0)
					break;
				before = current;
				current = current.nextNode;
			}
			OrderedListNode<T> newNode = new OrderedListNode<T>(insertItem);
			before.nextNode = newNode;
			newNode.nextNode = current;
			if(current == null)
				lastNode = newNode;
			
		}
	
		
	}


	public T removeFromFront() throws EmptyListException{
		
		if(isEmpty()){
			throw new EmptyListException(name);
		}
		T data = firstNode.getData();
		
		if(firstNode == lastNode)
			firstNode = lastNode = null;
		else
			firstNode = firstNode.nextNode;
		
		return data;
	}
	
	public T removeFromBack() throws EmptyListException{
		if(isEmpty())
			throw new EmptyListException(name);
		
		T data = lastNode.getData();
		if(firstNode == lastNode)
			firstNode = lastNode = null;
		else{
			OrderedListNode<T> current = firstNode;
			while(current.nextNode != lastNode)
				current = current.nextNode;
			
			lastNode = current;
			current.nextNode = null;
		}
		
		return data;
	}
	
	
	
	public boolean isEmpty() {
		return firstNode == null;
	}

	public void print() {
		
		if(isEmpty()){
			System.out.printf("%s is empty!\n", name);
			return;
		}
		System.out.printf("%s contains:%n", name);
		
		OrderedListNode<T> currentNode = firstNode;
		if(firstNode != null){
			
			while(currentNode.nextNode  != null){
				System.out.printf("%s ", currentNode.getData());
				currentNode = currentNode.nextNode;
				
			}
		}
		
	}
}

class OrderedListNode<T extends Comparable<T>>{
	
	OrderedListNode<T> nextNode;
	T data;
	
	public OrderedListNode(T data){
		this(data, null);
	}
	public OrderedListNode(T data, OrderedListNode<T> next){
		this.data = data;
		nextNode = next;
	}
	public T getData(){
		return data;
	}
	public OrderedListNode<T> getNextNode(){
		return nextNode;
	}
}