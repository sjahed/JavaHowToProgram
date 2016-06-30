package ch7;

import java.security.SecureRandom;
import java.util.Random;

public class DeckOfCards {

	private Card[] deck;
	private int currentCard;
	private static final SecureRandom randomNumbers = new SecureRandom();
	private static final int NUM_OF_CARDS  = 52;
	public static final int NUM_SUITS = 4;


	
	public DeckOfCards(){
		String[] faces = {"Ace", "Deuce", "Three", "Four", "Five",
				"Six", "Seven", "Eight", "Nine", "Ten", "Jack",
				"Queen", "King"
		};
		String[] suits = {"Diamond", "Hearts", "Clubs", "Spades"};
		
		deck = new Card[52];
		
		for(int i=0; i<deck.length;i++){
			deck[i] = new Card(faces[i % 13], suits[i/13], i % 13, i/13);
		}
		
	}
	
	public void shuffle(){
		currentCard = 0;
		
		for(int first = 0; first < deck.length; first++){
			
			int second = randomNumbers.nextInt(NUM_OF_CARDS);
			Card temp = deck[first];
			deck[first] = deck[second];
			deck[second] = temp;
		}
	}

	public Card dealCard(){
		
		if(currentCard < deck.length){
			return deck[currentCard++];
		}else
			return null;
	}
	
	
}
