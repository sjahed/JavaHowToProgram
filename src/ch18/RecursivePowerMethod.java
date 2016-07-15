package ch18;

public class RecursivePowerMethod {

	public static void main(String [] args){
		System.out.println(power(3,3));
	}
	public static int power(int base, int exponent){
		if(exponent == 1){
			return base;
		}else{
			return base * power(base, exponent-1);
		}
	}
}
