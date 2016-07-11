package ch15;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileAndDirectoryInfo {

	
	public static void main(String [] args) throws IOException{
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter file or directory name: ");
		Path path = Paths.get(scanner.nextLine());
		
		if(Files.exists(path)){
			System.out.printf("%n%s exists%n",path.getFileName());
			System.out.printf("%s is a directory%n", Files.isDirectory(path) ? "Is" : "Is not");
			System.out.printf("%s absolute path%n", path.isAbsolute() ? "Is" : "Is not");
			System.out.printf("Last modified at %s%n",Files.getLastModifiedTime(path));
			System.out.printf("Size: %s%n", Files.size(path));
			System.out.printf("Path: %s%n", path);
			System.out.printf("Absolute path %s%n",path.toAbsolutePath());
			
			if(Files.isDirectory(path)){
				System.out.printf("Directory contnets:%n");
				
				DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
				for(Path p: directoryStream){
					System.out.println(p);
				}
			}
			
		}else{
			System.out.printf("Doesnt exist!");
		}
	}
}

