

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import lib.Interactable;
import lib.ImageLoader;

public class Card {

	private String suit;
	private String rank;
	private int pointValue;

	public static Image RED_BACK, BLUE_BACK;
	public static Image RED_BACK_SCALE, BLUE_BACK_SCALE;
	public static int cardW = 50, cardH = 100;
	public Image frontScale, backScale;

	public boolean hovering = false, holding = false;
	public boolean revealed = true;
	
	public int x, y, w, h;

	public Card(String cardRank, String cardSuit, int cardPointValue) {
		this.rank = cardRank;
		this.suit = cardSuit;
		this.pointValue = cardPointValue;
		loadImages();
		
	}

	public void loadImages() {
		this.frontScale = ImageLoader.loadImage("/cards/fronts/png_96_dpi/" + suit + "_" + rank + ".png").getScaledInstance(cardW, cardH, Image.SCALE_SMOOTH);
		this.backScale = isRed() ? RED_BACK_SCALE : BLUE_BACK_SCALE;
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
 