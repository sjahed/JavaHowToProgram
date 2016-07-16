package ch20;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class GenericStack<T> {
	
	private final ArrayList<T> list;
	private int capacity;
	
	public GenericStack(int capacity){
		this.capacity = capacity > 0 ? capacity : 10;
		list = new ArrayList<T>(10);
	}
	
	public void push(T element){
		list.add(element);
	}
	
	public T pop(){
		
		if(list.isEmpty())
			throw new EmptyStackException();
		
		return list.remove(list.size()-1);
	}

}
