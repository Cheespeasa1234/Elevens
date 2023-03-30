package cards.card;

import java.awt.Image;
import java.io.File;
import java.net.URL;

import javax.swing.ImageIcon;

public class Card {

	private String suit;
	private String rank;
	private int pointValue;

	public static Image RED_BACK;
	public static Image BLUE_BACK;
	public Image front, back;

	public Card(String cardRank, String cardSuit, int cardPointValue) {
		this.rank = cardRank;
		this.suit = cardSuit;
		this.pointValue = cardPointValue;
		loadImage();
	}

	public void loadImage() {
		File f = new File("img_cards/fronts/png_96_dpi/"+this.suit+"_"+this.rank+".png");
		System.out.println(f.getAbsolutePath());
		this.front = new ImageIcon(f.getPath()).getImage();
	}

	public boolean matches(Card otherCard) {
		return this.pointValue + otherCard.pointValue == 11 || 
		((this.isFace() && otherCard.isFace()) && this.rank.equals(otherCard.rank));
	}

	public boolean isRed() {
		return this.rank.equals("diamonds") || this.rank.equals("hearts");
	}

	public boolean equals(Object object) {
		if(!(object instanceof Card)) {
			return false;
		}
		Card c = (Card) object;
		return c.rank.equals(this.rank);
	}

	public boolean isFace() {
		return this.rank.equals("jack") || this.rank.equals("queen") || this.rank.equals("king");
	}
	/**
	 * Converts the rank, suit, and point value into a string in the format
	 *     "[Rank] of [Suit] (point value = [PointValue])".
	 * This provides a useful way of printing the contents
	 * of a <code>Deck</code> in an easily readable format or performing
	 * other similar functions.
	 *
	 * @return a <code>String</code> containing the rank, suit,
	 *         and point value of the card.
	 */
	@Override
	public String toString() {
		return "Card[rank:" + rank + " suit:" + suit + " points:" + pointValue + "]";
	}
}
 