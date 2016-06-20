package ch6;


public class NumberRepresentation {
	
	private final int BASE_2 = 2;
	private final int BASE_8 = 8;
	private final int BASE_16 = 16;
	private final int MAX_BASE_2 = 2;
	private final int MAX_BASE_8 = 8;
	private final int MAX_BASE_16 = 15;
	
	public NumberRepresentation(){
		
		System.out.println("A table representation of different number formats for 0 - 255\n\n");
		System.out.printf("  %5s %8s %8s %8s\n","Decimal","Binary","Octal","Hex");
		System.out.printf("=======================================\n");
		
		for(int i=0; i< 256; i++){
			
	
			System.out.printf("  %3s %8s %3s %2s\n", i,toBinary(i),toOctal(i),toHex(i));
		}
	}

	private String toBinary(int num){
		String reminder="";
		
		if(num < MAX_BASE_2){
			return String.valueOf(num);
		}else{
			reminder = toBinary(num/BASE_2)+(num%BASE_2);
			
		}
		return reminder;
	}
	
	private String toOctal(int num){
		String reminder="";
		if(num <= MAX_BASE_8){
			return String.valueOf(num);
		}else{
			reminder = toOctal(num/BASE_8)+(num%BASE_8);
		}
		return reminder;
	}
	
	private String toHex(int num){
		String reminder="";
		
		if(num <= MAX_BASE_16){
			switch(num){
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				return String.valueOf(num);
			case 10:
				return "A";
			case 11:
				return "B";
			case 12:
				return "C";
			case 13:
				return "D";
			case 14:
				return "E";
			case 15:
				return "F";
			}
		}else{
			int rem = num % BASE_16;
			switch(rem){
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				reminder = toHex(num/BASE_16)+rem;
				break;
			case 10:
				reminder = toHex(num/BASE_16)+"A";
				break;
			case 11:
				reminder = toHex(num/BASE_16)+"B";
				break;
			case 12:
				reminder = toHex(num/BASE_16)+"C";
				break;
			case 13:
				reminder = toHex(num/BASE_16)+"D";
				break;
			case 14:
				reminder = toHex(num/BASE_16)+"E";
				break;
			case 15:
				reminder = toHex(num/BASE_16)+"F";
				break;
			}
		
			
		}
		return reminder;
	}
	
	
	public static void main(String [] args){
		new NumberRepresentation();
	}
	
}
