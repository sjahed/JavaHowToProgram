package ch7;

public class VariableLength {

	public static void main(String [] args){
		
		System.out.println(product(1,2,3));
		System.out.println(product(4,5,6,7));
		System.out.println(product(10));
	}
	public static int product(int ... numbers){
		
		int p = 1;
		for(int x : numbers){
			p *= x;
		}
		
		return p;
	}
}
