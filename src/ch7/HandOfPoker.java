package ch7;

public class HandOfPoker {

	private static final int NUM_CARDS_FOR_DEALER = 5;
	private static final int NUM_CARDS_FOR_PLAYER = 2;
	
	
	private DeckOfCards deck;
	private boolean withPlayers;
	private Player[] playersList;
	private Card[] dealer;

	private int [] rankFreq;
	private int [] suitFreq;
	
	public HandOfPoker(int numPlayers){
		
		if(numPlayers > 0){
			
			playersList = new Player[numPlayers];
			for(int i = 0; i<playersList.length;i++)
				playersList[i] = new Player();
			withPlayers = true;
		}
		deck = new DeckOfCards();
		dealer = new Card[NUM_CARDS_FOR_DEALER];
		
		//used during evaluation of how many each of card is dealt in a hand
		rankFreq = new int[13];
		suitFreq = new int [4];
	}
	
	public HandOfPoker(){
		deck = new DeckOfCards();
		dealer = new Card[NUM_CARDS_FOR_DEALER];
		
		//used during evaluation of how many each of card is dealt in a hand
		rankFreq = new int[13];
		suitFreq = new int [4];
	}
	
	public void dealHand(){
		deck.shuffle();
		
		if(withPlayers){
			for(int i = 0; i < NUM_CARDS_FOR_PLAYER; i++){
				for(int j = 0; j < playersList.length;j++){
					playersList[j].getsCard(deck.dealCard());
				}
			}//end of for - deal cards for all palyers
			
		}//end of if(withPlayers) - check if game was initiated with players
		
		for(int i = 0; i < NUM_CARDS_FOR_DEALER; i++){
			dealer[i] = deck.dealCard();
		}
		//what cards does the dealer get
		getDealerCardFreq();
		
	}
	
	private void getDealerCardFreq(){
		for(int i = 0; i < NUM_CARDS_FOR_DEALER; i++){
			rankFreq[dealer[i].getRank()]++;
			suitFreq[dealer[i].getSuitRank()]++;
		}

	}
	
	public boolean hasPair(){
		boolean result = false;
		
		for(int item: rankFreq){
		
			if(item == 2){
				result = true;
				break;
			}
			
		}//end of searching freq array
		
		return result;
	}//end of hasPair method
	
	public boolean hasTwoPairs(){

		boolean result =false;
		boolean flag = false;
		
		for(int i = 0; i < rankFreq.length; i++){
			if(rankFreq[i] == 2){
				for(int j=i+1; j< rankFreq.length; j++){
					
					if(rankFreq[j] == 2){
						result = true;
						flag = true;
						break;
					}//end of rankFreq[j] == 2
						
				}//end of second search of rankFreq array, for second pair
				//if flag set, then we are done searching
				if(flag)
					break;
			}//end of first if rankFreq[i] == 2
		}//end of for - searching rankFreq array 
		
		return result;
	}//ehd of hasTwoPairs()
	
	public boolean hasFourOfKind(){
		boolean result = false;
		
		for(int item: rankFreq){
			
			if(item == 4)
				result = true;
		}//end of searching freq array
		return result;
	}//end of hasFourOfKind method
	
	public boolean hasFlush(){
		boolean result = false;
		
		for(int item: suitFreq){
			
			if(item == 5){
				result = true;
				break;
			}
		}//end of for - searching array
		return result;
	}//end of hasFlush
	
	public boolean hasFullHouse(){
		boolean result = false;
		for(int item: rankFreq){
			if (item == 3 && hasPair()){
				result = true;
				break;
			}
		}//end of for - searching rankFreq array
		
		return result;
	}//end of hasFullHouse
	
	
	public boolean hasStraight(){
		boolean result = false;
		
		for(int i = 0; i < rankFreq.length; i++){
			
			if(i+5 < rankFreq.length){
				if(rankFreq[i] == 1 && rankFreq[i+1] == 1 && rankFreq[i+2] == 1 &&
						rankFreq[i+3] == 1 && rankFreq[i+4] == 1){
					result = true;
					break;
				}//end of if - check if five consecutive fields have 1 value set
			}//end of if - check we are within bounds of rankFreq
		}//end of for - search rankFreq
		
		return result;
		
	}
	
	public String toString(){
		
		String m = "Dealer has:\n";
		
		for(Card item: dealer){
			m += item.toString()+"\n";
		}
		
		if(withPlayers)
			for(int i=0; i<playersList.length;i++)
				m += playersList[i].toString();
		
		return m;
	}
	
	
	class Player{
		
		
		private Card[] playerCards;
		private int numCards;
		
		public Player(){
			
			playerCards = new Card[NUM_CARDS_FOR_PLAYER];
			numCards = -1;
		}
		
		void getsCard(Card card){
			if(numCards == -1 || numCards < NUM_CARDS_FOR_PLAYER){
				playerCards[++numCards] = card;
			}
				
		}
		
		public Card[] getPlayerCards(){
			return playerCards;
		}
		
		public boolean hasPair(){
			
			return (playerCards[0].getFace() == playerCards[1].getFace()) ?  true : false;
		}
		
		public String toString(){
			String m="Player has: \n";
			for(int i=0; i<getPlayerCards().length;i++){
				m += getPlayerCards()[i].toString()+"\n";
			}

			return m;
		}
	}//end of class Player
}
