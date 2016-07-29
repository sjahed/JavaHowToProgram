package ch21;

public class List<T> {
	
	private ListNode<T> firstNode;
	private ListNode<T> lastNode;
	private String name;
	
	public List(){
		this("list");
	}
	
	public List(String name){
		this.name = name;
		firstNode = lastNode = null;
	}
	
	public void insertAtFront(T insertItem){
		
		if(isEmpty()){
			firstNode = lastNode = new ListNode<T>(insertItem);
		}else{
			firstNode = new ListNode<T>(insertItem, firstNode);
		}
	}
	
	public void insertAtBack(T insertItem){
		if(isEmpty())
			lastNode = firstNode = new ListNode<T>(insertItem);
		else
			lastNode = lastNode.nextNode = new ListNode<T>(insertItem);
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
			ListNode<T> current = firstNode;
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

	public void printListBackward(){
		printListBackwardHelper(firstNode);
	}
	public void printListBackwardHelper(ListNode<T> node){
		
		if(node == lastNode){
			System.out.printf("%s ",node.getData());
			return;
		}else{
			printListBackwardHelper(node.nextNode);
			System.out.printf("%s ", node.getData());
		}
		
	}
	public void print() {
		
		if(isEmpty()){
			System.out.printf("%s is empty!\n", name);
			return;
		}
		System.out.printf("%s contains:%n", name);
		
		ListNode<T> currentNode = firstNode;
		if(firstNode != null){
			
			while(currentNode.nextNode  != null){
				System.out.printf("%s ", currentNode.getData());
				currentNode = currentNode.nextNode;
				
			}
		}
		
	}
}
