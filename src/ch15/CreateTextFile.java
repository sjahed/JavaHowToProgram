package ch15;

import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CreateTextFile {

	private static Formatter output;
	
	public static void main(String [] args){
		openFile();
		addRecords();
		closeFile();
	}
	
	public static void openFile(){
		try{
			output = new Formatter("clients.txt");
		}catch(SecurityException se){
			System.err.println("write permission denied");
			System.exit(1);
		}catch(FileNotFoundException fnfe){
			System.err.println("error opening file");
			System.exit(1);
		}
	}
	
	public static void addRecords(){
		Scanner input = new Scanner(System.in);
		while(input.hasNext()){
			try{
				output.format("%d %s %s %.2f%n", input.nextInt(),input.next(),input.next(),input.nextDouble());
			}catch(FormatterClosedException fce){
				break;
			}catch(NoSuchElementException nsee){
				System.err.println("invalid input");
				input.nextLine();
			}
			System.out.print("?");
		}
	}
	
	public static void closeFile(){
		if(output != null)
			output.close();
	}
}
