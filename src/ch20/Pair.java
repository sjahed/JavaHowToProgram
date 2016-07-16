package ch20;

public class Pair<F,S> {

	F firstElement;
	S secondElement;
	
	public Pair(F firstElement, S secondElement){
		this.firstElement = firstElement;
		this.secondElement = secondElement;
	}
	
	public void setFElement(F element){
		firstElement = element;
	}
	
	public F getFElement(){
		return firstElement;
	}
	
}
