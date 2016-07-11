package ch15;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.Scanner;

public class FileMatch {

	private static Scanner omReader;
	private static Scanner tReader;
	private static Formatter nmWriter;
	
	
	public static void openFile(){
		
		try{
			omReader = new Scanner(Paths.get("oldMaster.txt"));
			tReader = new Scanner(Paths.get("trans.txt"));
			nmWriter = new Formatter("newMaster.txt");
		}catch(FileNotFoundException fe){
			System.exit(1);
		}catch(IOException io){
			System.exit(1);
		}
	}
	
	
	public static void doTheThing(){
		
		while(tReader.hasNext()){
			
			int accA = tReader.nextInt();
			
			while(omReader.hasNext()){
				int accB = omReader.nextInt();
				if(accA == accB){
					
				}
						
			}
		}
	}
	
	public static void closeFIle(){
		if(omReader != null)
			omReader.close();
		if(tReader != null){
			tReader.close();
		}
		if(nmWriter != null)
			nmWriter.close();
	}
}
