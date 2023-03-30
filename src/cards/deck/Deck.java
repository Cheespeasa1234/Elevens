package cards.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import cards.card.Card;

/**
 * The Deck class represents a shuffled deck of cards.
 * It provides several operations including
 * initialize, shuffle, deal, and check if empty.
 */
public class Deck {

	public static final String[] SUITS = { "clubs", "hearts", "spades", "diamonds" };
	public static final String[] RANKS = { "1", "2", "3", "4", "5", "6", "7", "8", "9", 
											"10", "jack", "queen", "king", "ace" 
	};
	
	public static final int[] POINTVALUES = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 1};
	public static final int[] POINTVALUES_ELEVENS = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 1};

	/**
	 * cards contains all the cards in the deck.
	 */
	public List<Card> cards;

	/**
	 * size is the number of not-yet-dealt cards.
	 * Cards are dealt from the top (highest index) down.
	 * The next card to be dealt is at size - 1.
	 */
	public int size;

	/**
	 * Creates a new <code>Deck</code> instance.<BR>
	 * It pairs each element of ranks with each element of suits,
	 * and produces one of the corresponding card.
	 * 
	 * @param ranks  is an array containing all of the card ranks.
	 * @param suits  is an array containing all of the card suits.
	 * @param values is an array containing all of the card point values.
	 */

	private Deck() {
		this.cards = new ArrayList<Card>();
		this.size = cards.size();
	}

	public static Deck of(String[] ranks, String[] suits, int[] values) {
		Deck d = new Deck();
		for (String s : suits) {
			for (int i = 0; i < Math.min(ranks.length, values.length); i++) {
				Card c = new Card(ranks[i], s, values[i]);
				d.cards.add(c);
				d.size++;
			}
		}
		return d;
	}

	public static Deck ofDefault() {
		return of(RANKS, SUITS, POINTVALUES);
	}

	/**
	 * Determines if this deck is empty (no undealt cards).
	 * 
	 * @return true if this deck is empty, false otherwise.
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	/**
	 * Randomly permute the given collection of cards
	 * and reset the size to represent the entire deck.
	 */
	public void shuffle() {
		Collections.shuffle(cards);
	}

	/**
	 * Deals a card from this deck.
	 * 
	 * @return the card just dealt, or null if all the cards have been
	 *         previously dealt.
	 */
	public Card deal() {
		if(size < 1)
			return null;
		Card c = cards.get(size-1);
		size --;
		return c;
	}

	/**
	 * Generates and returns a string representation of this deck.
	 * 
	 * @return a string representation of this deck.
	 */
	@Override
	public String toString() {
		String rtn = "size = " + size + "\nUndealt cards: \n";

		for (int k = size - 1; k >= 0; k--) {
			rtn = rtn + cards.get(k);
			if (k != 0) {
				rtn = rtn + ", ";
			}
			if ((size - k) % 2 == 0) {
				// Insert carriage returns so entire deck is visible on console.
				rtn = rtn + "\n";
			}
		}

		rtn = rtn + "\nDealt cards: \n";
		for (int k = cards.size() - 1; k >= size; k--) {
			rtn = rtn + cards.get(k);
			if (k != size) {
				rtn = rtn + ", ";
			}
			if ((k - cards.size()) % 2 == 0) {
				// Insert carriage returns so entire deck is visible on console.
				rtn = rtn + "\n";
			}
		}

		rtn = rtn + "\n";
		return rtn;
	}
}
