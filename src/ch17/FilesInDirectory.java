package ch17;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;


public class FilesInDirectory {

	public static void main(String [] args) throws FileNotFoundException, IOException{
		
		Properties prop = new Properties();
		prop.load(new FileInputStream(new File("/home/sjahed/Downloads/DataTracesSet.txt")));
		
		Map<String, String> fileList = (Map<String, String>) prop.entrySet()
										   .stream()
										   .collect(Collectors.toMap(
												   e -> (String) e.getKey(),
												   e -> (String) e.getValue()
												   ));
		Set<String> keyset = fileList.keySet();
		for(String s: keyset){
			System.out.printf("File: %s Attributes:%s", s,fileList.get(s));
		}
	
	}
	
}
