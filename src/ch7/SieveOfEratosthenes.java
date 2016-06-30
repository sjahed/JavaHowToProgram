package ch7;

public class SieveOfEratosthenes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SieveOfEratosthenes(1000);
	}
	
	private boolean [] primeNumbers;
	public SieveOfEratosthenes(int size){
		
		try{
			primeNumbers = new boolean[size];
			
		}catch(NumberFormatException ex){
			System.out.println("Only Integers accepted");
		}
		
		initializeToTrue();
		findPrimeNumbers();
		listPrimeNumbers();
	}

	private void initializeToTrue(){
		
		for(int i = 0 ; i < primeNumbers.length; i++){
			primeNumbers[i] = true;
		}
	}
	private void findPrimeNumbers(){

		for(int i = 2; i < primeNumbers.length; i++){

			multiplesOf(i);
		}
	}

	private void multiplesOf(int number){
		
		if(primeNumbers[number]){
			for(int i = number+number ; i < primeNumbers.length; i += number){
				
				if(i % number == 0)
					primeNumbers[i] = false;
			}
		}
		
	}
	
	private void listPrimeNumbers(){
		for(int i = 0; i<primeNumbers.length; i++){
			if(primeNumbers[i])
				System.out.printf("%10s\n", i);
		}
	}
	
}
