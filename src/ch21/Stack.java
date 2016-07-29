package ch21;

public class Stack<T> extends List<T>{
	
	public Stack(){
		super("stack");
	}
	
	public void push(T object){
		insertAtFront(object);
	}
	public T pop() throws EmptyListException{
		return removeFromFront();
	}
	
	public T peek() throws EmptyListException{
		T object = removeFromFront();
		insertAtFront(object);
		return object;
	}
}
