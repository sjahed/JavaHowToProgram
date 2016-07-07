package ch11;

public class ExceptionsTest {

	
	public static void main(String [] args){
		try{
			throwException();
		}catch( Exception e){
			System.out.println("Caught exception");
		}
	}
	
	public static void throwException() throws Exception{
		
		try{
			System.out.println("Exception 1 thrown!");
			throw new Exception();
		}catch(Exception e){
			System.out.println("Re-throwing a caught exception!");
			throw e;
		}
		
	}
}
