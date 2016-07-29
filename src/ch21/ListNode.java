package ch21;

public class ListNode<T> {

	ListNode<T> nextNode;
	private T data;
	
	public ListNode(T data){
		this(data,null);
	}

	public ListNode(T data, ListNode<T> nextNode){
		this.data = data;
		this.nextNode = nextNode;
	}
	
	T getData(){
		return data;
	}
	
	ListNode<T> getNext(){
		return nextNode;
	}
}
