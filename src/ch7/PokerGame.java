package ch7;

//poker game
public class PokerGame {


	private int numPlayers;
	public PokerGame(){
		
		HandOfPoker hand = new HandOfPoker();
		hand.dealHand();
		System.out.println(hand.toString());
		System.out.println("Dealer has Flush: "+hand.hasFlush());
		System.out.println("Dealer has Straight: "+hand.hasStraight());
		System.out.println("Dealer has Four of a kind: "+hand.hasFourOfKind());
		System.out.println("Delaer has Full House: "+hand.hasFullHouse());
		System.out.println("Dealer has Two Pairs: "+hand.hasTwoPairs());
		System.out.println("Dealer has One Pair: "+hand.hasPair());
		
		
	}
	
	public PokerGame(int numPlayers){
		HandOfPoker hand = new HandOfPoker(numPlayers);
		hand.dealHand();
		System.out.println(hand.toString());
		
		System.out.println("Dealer has Flush: "+hand.hasFlush());
		System.out.println("Dealer has Straight: "+hand.hasStraight());
		System.out.println("Dealer has Four of a kind: "+hand.hasFourOfKind());
		System.out.println("Delaer has Full House: "+hand.hasFullHouse());
		System.out.println("Dealer has Two Pairs: "+hand.hasTwoPairs());
		System.out.println("Dealer has One Pair: "+hand.hasPair());
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		new PokerGame(2);
		
	}

}
