package ch21;

public class Queue<T>{

	private List<T> queue;
	
	public Queue(){
		queue = new List<T>("Queue");
	}
	public Queue(String name){
		queue = new List<T>(name);
	}
	
	private void enqueu(T object){
		queue.insertAtBack(object);
	}
	
	private T dequeue() throws EmptyListException{
		return queue.removeFromFront();
	}
	
	private boolean isEmpty(){
		return queue.isEmpty();
	}
	
	private void print(){
		queue.print();
	}
}
