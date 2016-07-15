package ch18;

public class Palindrome {

	public static void main(String [] args){
		char [] array = {'r','a','d','b','r'};
		//System.out.println(testPalindrome(array,0,array.length-1));
		System.out.println(enhancedTestPalindrome("Badar"));
		
	}
	
	public static boolean enhancedTestPalindrome(String s){
		
		if(s.length() == 0 || s.length() == 1){
			return true;
		}else{
			if(s.charAt(0) == s.charAt(s.length()-1)){
				return enhancedTestPalindrome(s.substring(1, s.length()-1));
			}
			
			return false;
		}
	}
	public static boolean testPalindrome(char[] array, int p1, int p2){
		
		if((array.length % 2 != 0) 
				&& p1 == array.length / 2 
				|| p2 == array.length){
			return true;
		}else if( (array.length %2 == 0) && p1 == array.length / 2 || p2 == array.length /2){
			if(array[p1] == array[p2]){
				return true;
			}else{
				return false;
			}
		}else{
			if(array[p1] == array[p2])
				return testPalindrome(array, p1+1, p2-1);
			else
				return false;
		}
			
		
	}
}
