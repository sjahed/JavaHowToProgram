package ch15;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TestFileCreator {

	private static Formatter output1, output2;
	
	public static void main(String [] args){
		openFile();
		addRecords();
		closeFile();
	}

	private static void openFile() {
		// TODO Auto-generated method stub
		try{
			output1 = new Formatter("oldmaster.txt");
			output2 = new Formatter("trans.txt");
		}catch(FileNotFoundException e){
			System.exit(1);
		}
		
	}

	private static void addRecords() {
		Scanner input = new Scanner(System.in);
//		
//		while(input.hasNext()){
//			System.out.print("?");
//			try{
//				output1.format("%d %s %s %.2f%n", input.nextInt(),input.next(),input.next(),input.nextDouble());
//			}catch(FormatterClosedException fce){
//				break;
//			}catch(NoSuchElementException nsee){
//				System.err.println("invalid input");
//				input.nextLine();
//			}
//			
//		}
		
		while(input.hasNext()){
			System.out.print("?");
			try{
				output2.format("%d %.2f%n", input.nextInt(),input.nextDouble());
			}catch(FormatterClosedException fce){
				break;
			}catch(NoSuchElementException nsee){
				System.err.println("invalid input");
				input.nextLine();
			}
			
		}
	}

	private static void closeFile() {
		// TODO Auto-generated method stub
		if(output1 != null)
			output1.close();
		
		if(output2 != null)
			output2.close();
	}
	
	
}
