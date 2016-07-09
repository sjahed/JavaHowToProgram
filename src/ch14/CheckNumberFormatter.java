package ch14;

public class CheckNumberFormatter {

	private static final String [] textRepNum = {"ONE", "TWO", "THREE","FOUR","FIVE",
										  "SIX", "SEVEN", "EIGHT", "NINE"
										};
	
	private StringBuilder convert(double input){
		
		final int firstPartPos = 0, secondPartPos = 1;
		final int NON_ASSIGNED_VALUE = -1;
		final int MAX_PART = 2;
		StringBuilder textRep = new StringBuilder();
		int firstPart = -1, secondPart = -1, lengthFPart = -1, lengthSPart = NON_ASSIGNED_VALUE;
		
		
		String [] split = String.valueOf(input).split(".");
		
		firstPart = Integer.parseInt(split[firstPartPos]);
		lengthFPart = split[firstPartPos].length();
		
		if(split.length == MAX_PART){
			secondPart = Integer.parseInt(split[secondPartPos]);
			lengthSPart = split[secondPartPos].length();
		}
		
		
		return textRep;
	}//end of convert() 
}
