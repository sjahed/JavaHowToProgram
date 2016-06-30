package ch7;

import java.util.Scanner;
/**
 * 
 * @author sjahed
 * For further information about this program
 * refer to Java: How to program, Chapter 7, Page 308
 *
 */
public class Simpletron {

	private final String END_LOADING_MESSAGE = "*** Program loading finished.   ***\n" +
			  "*** Starting program execution. ***\n";
	
	private static final int READ = 10;
	private static final int WRITE = 11;
	private static final int LOAD = 20;
	private static final int STORE = 21;
	private static final int ADD = 30;
	private static final int DIVIDE = 32;
	private static final int SUBTRACT = 31;
	private static final int MULTIPLY = 33;
	private static final int BRANCH = 40;
	private static final int BRANCHNEG = 41;
	private static final int BRANCHZERO = 42;
	private static final int HALT = 43;
	
	private static final int MAX_MEMORY_SIZE=100;
	
	private final int EXIT_CODE = -99999;
	private final int RANGE_MIN = 1000;
	private final int RANGE_MAX = 9999;

	
	private Scanner input;
	private int [] memory;
	private int accum;
	private int instructionCounter;
	private int instructionRegister;
	private int operationCode;
	private int operand;
	private boolean halt;
	public Simpletron(){
		//display welcome message
		System.out.println(welcomeMessage());
		
		input = new Scanner(System.in);
		accum = 0;
		instructionCounter = 0;
		instructionCounter = 0;
		instructionRegister = 0;
		operationCode = 0;
		operand = 0;
		halt = false;
		memory  = loadInput();
	
		run();
	}
	
	private void run(){
		int DENOMINATOR = 100;

		
		do{
			System.out.println(compDump());
			instructionRegister = memory[instructionCounter++];
			
			operationCode = instructionRegister / DENOMINATOR;
			operand = instructionRegister % DENOMINATOR;
			
			switch(operationCode){
			case READ:
				read(operand);
				break;
			case WRITE:
				write(operand);
				break;
			case LOAD:
				load(operand);
				break;
			case STORE:
				store(operand);
				break;
			case ADD:
				add(operand);
				break;
			case SUBTRACT:
				subtract(operand);
				break;
			case DIVIDE:
				divide(operand);
				break;
			case MULTIPLY:
				multiply(operand);
				break;
			case BRANCH:
				branch(operand);
				break;
			case BRANCHNEG:
				branchNeg(operand);
				break;
			case BRANCHZERO:
				branchZero(operand);
				break;
			case HALT:
				halt();
				break;
				
			}
			
		}while(instructionCounter < memory.length && !halt);//end of while loop - to read all memory data
	}//end of run() method
	
	private void read(int addressToWriteTo){
		int var;
		//try to get a number, until you get a number
		do{
			try{
				System.out.print("Enter a number: ");
				var = input.nextInt();
				memory[addressToWriteTo] = var;
				break;
			}catch(NumberFormatException ex){
				System.out.println("Enter a number please!");
				continue;
			}
		}while(true);	

	}//end of read method
	
	private void write(int addressToReadFrom){
		System.out.printf("%+4d \n",memory[addressToReadFrom]);
	}
	
	private void load(int addressToReadFrom){
		accum = memory[addressToReadFrom];
	}
	
	private void store(int addressToWriteTo){
		
		memory[addressToWriteTo] = accum;
	}
	
	private void add(int addressToReadFrom){
		accum += memory[addressToReadFrom];
	}
	
	private void subtract(int addressToReadFrom){
		accum -= memory[addressToReadFrom];
	}
	
	private void divide(int addressToReadFrom){
		try{
			accum /= memory[addressToReadFrom];
		}catch(ArithmeticException e){
			System.out.println("Can't divide by zero!");
			System.out.println("Operation terminated abnormally");
			//halt = true;
		}
		
	}
	
	private void multiply(int addressToReadFrom){
		accum *= memory[addressToReadFrom];
	}
	
	
	private void branch(int newInstructionCounterAddress){
		instructionCounter =  newInstructionCounterAddress;
	}
	
	private void branchNeg(int newInstructionCounterAddress){
		if(accum < 0)
			instructionCounter = newInstructionCounterAddress;
	}
	private void branchZero(int newInstructionCounterAddress){
		if(accum == 0)
			instructionCounter = newInstructionCounterAddress;
	}
	private void halt(){
		System.out.println("*** Program execution terminated! ***");
		halt = true;
	}
	private int [] loadInput(){
		
		int[] program = new int[MAX_MEMORY_SIZE];
		
		
		int counter = 0;
		do{
			try{
				//show input label
				System.out.printf("%02d ? ",counter);
				int in = input.nextInt();
				
				if(in == EXIT_CODE){
					System.out.printf(END_LOADING_MESSAGE);
					break;
				}//else if(Math.abs(in) > RANGE_MAX || Math.abs(in)< RANGE_MIN){
				//	System.out.println("Only a (negative/positive) four digit number");
				//	continue;
				//}
				else{
					program[counter] = in;
				}//end of if_elseif_else - check if exit requested, number within range
				
				
				counter++;
				
				
			}catch(Exception ex){
				System.out.println("Input exception");
				break;
			}//end of try-catch block
			
			
		}while(counter < program.length);//end of do_while loop - until memory available, read instructions
		
		return program;
	}//end of loadInput method
	
	private String welcomeMessage(){
		
		String message =   
				("*** Welcome to Simpletron!                       ***\n" +
				 "*** Please enter your program one instruction    ***\n" +
				 "*** (or data word) at a time. I will display     ***\n" +
				 "*** the location number and a question mark (?). ***\n" +
				 "*** You then type the word for that location.    ***\n" +
				 "*** Type -99999 to stopentering the program.     ***\n");
		return message;
	}//end of welcomeMessage method
	
	private String compDump(){
		String line = String.format("\n\nREGISTERS:\n");
		line += String.format("%-21s %+05d\n%-21s %02d\n%-21s %+05d\n%-21s %02d\n%-21s %02d\n\n","accumulator",accum,
				"instructionCounter",instructionCounter,"instructionRegister",instructionRegister,"operationCode",operationCode,"operand",operand);
		line += (String.format("MEMORY:\n"));
		line += (String.format("  %5d%5d%5d%5d%5d%5d%5d%5d%5d%5d\n",0,1,2,3,4,5,6,7,8,9));

		
	
		for(int i = 0; i < memory.length; i += 10){
			line += String.format("%2d ", i);
			for(int j = i; j < i+9; j++){
				line += String.format("%+05d ", memory[j]);
			}
			line += "\n";
		}
		
		return line;
	}
	
	public static void main(String [] args){
		new Simpletron();
	}
}
