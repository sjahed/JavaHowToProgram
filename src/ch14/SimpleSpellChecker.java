package ch14;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class SimpleSpellChecker {

	private List<String> wordList;
	
	public SimpleSpellChecker(){
		wordList = new ArrayList<String>();
		addWordsToList();
		
		System.out.print("Now enter a word: ");
		do{
			if(wordList.isEmpty()){
				System.out.println("word list empty!");
				break;
			}
			String spelledWord = getInput();
			if(!existsInList(spelledWord)){
				String suggestedWord = transpose(spelledWord);
			}else{
				System.out.println("no spelling problem");
			}
			
		}while(true);
	}

	private String transpose(String spelledWord) {
		//too lazy to research about spell checking,
		//its tedious and time consuming,
		//mabye i will do it once i go in other chapters,
		//where i can connect it to a dictionary
		// TODO Auto-generated method stub
		return null;
	}

	private boolean existsInList(String word){
		
		if(!wordList.isEmpty()){
			for(String w: wordList){
				if(w.equals(word)){
					return true;
				}
			}
		}
		return false;
	}
	private String getInput(){
		String result = "";
		Scanner input = new Scanner(System.in);
		try{
			result = input.nextLine();
		}catch(InputMismatchException e){
			System.out.println("something wrong with input");
		}
		
		return result;
	}
	private void addWordsToList() {
		String word;
		Scanner input = new Scanner(System.in);
		do{
			System.out.println("Enter a world or -999 to stop:");
			word = getInput();
			if(!word.matches("[A-Za-z]+"))
				continue;
		
			wordList.add(word);
		}while(!input.equals("-999"));
		
	}
}
