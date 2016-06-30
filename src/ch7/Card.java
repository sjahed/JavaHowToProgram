package ch7;

public class Card {


	private final String face;
	private final String suit;
	private final int rank;
	private final int suitRank;
	
	public Card(String face, String suit, int rank, int suitRank){
		this.face = face;
		this.suit = suit;
		this.rank = rank;
		this.suitRank = suitRank;
	}

	public String toString(){
		return face+" of "+suit;
	}
	
	public String getFace(){
		return face;
	}
	public String getSuit(){
		return suit;
	}
	public int getRank(){
		return rank;
	}
	
	public int getSuitRank(){
		return suitRank;
	}
}
