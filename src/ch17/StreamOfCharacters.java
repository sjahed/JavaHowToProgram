package ch17;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StreamOfCharacters {

	public static void main(String [] args) throws IOException{
		Pattern pattern = Pattern.compile("\\s+");
		Pattern cPattern = Pattern.compile("");
		Predicate<String> isNotSpace = value -> !value.equals(" ");
		
		Map<String, Long> wordCount =
				Files.lines(Paths.get("Chapter2Paragraph.txt"))
				     .map(line -> line.replaceAll("(?!')\\p{P}", ""))
				     .flatMap(line->pattern.splitAsStream(line))
				     .collect((Collectors.groupingBy(String::toLowerCase,TreeMap::new, Collectors.counting())));
		
		Map<String, Long> characterCount = 
				Files.lines(Paths.get("Chapter2Paragraph.txt"))
				     .map(line -> line.replaceAll("(?!')\\p{P}", ""))
				     .flatMap(line->cPattern.splitAsStream(line))
				     .filter(isNotSpace)
				     .collect((Collectors.groupingBy(String::toLowerCase,TreeMap::new, Collectors.counting())));
			     

		characterCount.entrySet()
	     .stream()
	     .collect(Collectors.groupingBy(entry -> entry.getKey(),
	    		 TreeMap::new, Collectors.toList()))
	     .forEach((letter, wordList) ->
	         {
	        	 //System.out.printf("%n%s%n", letter);
	        	 wordList.stream().forEach(word -> System.out.printf(
	        			   "%s: %d%n", word.getKey(), word.getValue()));

	         });
		

		wordCount.entrySet()
			     .stream()
			     .collect(Collectors.groupingBy(entry -> entry.getKey().charAt(0),
			    		 TreeMap::new, Collectors.toList()))
			     .forEach((letter, wordList) ->
			         {
			        	 System.out.printf("%n%C%n", letter);
			        	 wordList.stream().forEach(word -> System.out.printf(
			        			   "%13s: %d%n", word.getKey(), word.getValue()));

			         });
		
	}
}
