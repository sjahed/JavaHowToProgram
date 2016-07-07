package ch11;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CustomExceptionHeirarchyTest {

	public static void main(String [] args){
		try{
			
			getExceptionA();
			getExceptionB();
			getExceptionC();
			
			
			
		}catch(ExceptionA ea){
			System.err.println("Caught Exception A only!");
			ea.printStackTrace();
		}
		
		try{
			
			getExceptionB();
		}catch(ExceptionB eb){
			System.err.println("Caught Exception B Only!");
			eb.printStackTrace();
		}
		
		try{
			someMethod2();
		}catch(Exception e){
			System.err.println("Caught exception from somemethod2 called");
		}
		
		
		Scanner input = new Scanner(System.in);
		int x, y;
		try{
			try{
				System.out.println("Enter two numbers");
				x = input.nextInt();
				y = input.nextInt();
				System.out.println(x/y);
			}catch(InputMismatchException e){
				System.err.println("Only digits - inner try - catch block");
			}
		}catch(ArithmeticException e){
			System.err.println("Can't divide by zero - outer try-catch block");
		}
	}
	
	static void getExceptionA() throws ExceptionA{
		throw new ExceptionA("Exception A");
	}
	
	static void getExceptionB() throws ExceptionB{
		throw new ExceptionB("Exception B");
	}
	
	static void getExceptionC() throws ExceptionC{
		throw new ExceptionC("Exception C");
	}
	
	static void someMethod() throws Exception{
		throw new Exception();
	}
	static void someMethod2()throws Exception{
		try{
			someMethod();
		}catch(Exception e){
			System.err.println("Caught Exception, rethrowing it!");
			throw e;
		}
	}

}
