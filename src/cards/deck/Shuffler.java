package cards.deck;

import cards.card.Card;

/**
 * This class provides a convenient way to test shuffling methods.
 */
public class Shuffler {

	/**
	 * The number of consecutive shuffle steps to be performed in each call
	 * to each sorting procedure.
	 */
	private static final int SHUFFLE_COUNT = 1;

	/**
	 * The number of values to shuffle.
	 */
	private static final int VALUE_COUNT = 4;

	/**
	 * Apply a "perfect shuffle" to the argument.
	 * The perfect shuffle algorithm splits the deck in half, then interleaves
	 * the cards in one half with the cards in the other.
	 * @param deck is an array of integers simulating cards to be shuffled.
	 */
	public static void perfectShuffle(Deck deck) {

		Card[] cards = new Card[deck.cards.size()];
		for(int i=0;i<deck.cards.size();i++)cards[i]=deck.cards.get(i);

		int mid = cards.length / 2;
		Card[] firstHalf = new Card[mid], secondHalf = new Card[cards.length - mid];
		System.arraycopy(cards, 0, firstHalf, 0, mid);
		System.arraycopy(cards, mid, secondHalf, 0, mid);

		Card[] shuffled = new Card[cards.length];
		for(int i = 0; i < shuffled.length; i++) {
			if(i%2==0) shuffled[i] = firstHalf[i/2];
			if(i%2==1) shuffled[i] = secondHalf[i/2];
		}

		deck.cards.clear();
		for(Card s:shuffled)deck.cards.add(s);
	}

	/**
	 * Apply an "efficient selection shuffle" to the argument.
	 * The selection shuffle algorithm conceptually maintains two sequences
	 * of cards: the selected cards (initially empty) and the not-yet-selected
	 * cards (initially the entire deck). It repeatedly does the following until
	 * all cards have been selected: randomly remove a card from those not yet
	 * selected and add it to the selected cards.
	 * An efficient version of this algorithm makes use of arrays to avoid
	 * searching for an as-yet-unselected card.
	 * @param values is an array of integers simulating cards to be shuffled.
	 */
	public static void selectionShuffle(int[] values) {
		/* *** TO BE IMPLEMENTED IN ACTIVITY 3 *** */
	}
}
