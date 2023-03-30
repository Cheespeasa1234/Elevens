import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import cards.card.Card;
import cards.deck.Deck;

public class Game extends JPanel {

    public static final int PREF_W = 800;
    public static final int PREF_H = 600;

    private Deck d = Deck.ofDefault();

    public Game() {
        this.setFocusable(true);
        this.setBackground(Color.WHITE);
        d.shuffle();
        for (Card c : d.cards) {
            c.loadImage();
        }
    }

    private void renderCard(Graphics2D g2, Card c, int x, int y, int w, int h) {
        g2.drawRoundRect(x, y, w, h, 5, 5);
        Image i = c.front.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        g2.drawImage(i, x, y, this);
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        renderCard(g2, new Card("jack", "spades", 10), 100, 100, 50, 100);

    }

    /* METHODS FOR CREATING JFRAME AND JPANEL */

    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Elevens");
        JPanel gamePanel = new Game();

        frame.getContentPane().add(gamePanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
