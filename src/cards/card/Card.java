package cards.card;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import lib.Interactable;

public class Card {

	private String suit;
	private String rank;
	private int pointValue;

	public static Image RED_BACK, BLUE_BACK;
	public static int cardW = 50, cardH = 100;
	public Image front, back, frontScale, backScale;

	public boolean hovering = false, holding = false;
	public boolean revealed = true;
	
	public int x, y, w, h;

	public Card(String cardRank, String cardSuit, int cardPointValue) {
		this.rank = cardRank;
		this.suit = cardSuit;
		this.pointValue = cardPointValue;
		try {
			loadImage(cardW, cardH);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadImage(int w, int h) throws IOException {
		File f = new File("img_cards/fronts/png_96_dpi/" + this.suit + "_" + this.rank + ".png");
		URL url = f.toURI().toURL();
		System.out.println(f.getAbsolutePath());
		System.out.println(url.toString());
		
		this.front = ImageIO.read(f);
		this.frontScale = front.getScaledInstance(w, h, Image.SCALE_SMOOTH);
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
 