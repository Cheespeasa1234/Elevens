import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.Rectangle;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import cards.card.Card;
import cards.deck.Deck;

public class Game extends JPanel implements MouseMotionListener, MouseListener {

    public static final int PREF_W = 800;
    public static final int PREF_H = 600;

    public static final int cardW = Card.cardW, cardH = Card.cardH, cardGapX = 5, cardGapY = 5;
    private static final Color 
    cardHoverColor = new Color(100, 100, 100, 50), 
    cardLMBColor = new Color(180, 100, 100, 50),
    cardRMBColor = new Color(100, 180, 100, 50),
    cardMMBColor = new Color(100, 100, 180, 50);

    private Deck d = Deck.ofDefault();
    private Point mouseat = new Point(0,0);
    private boolean mouseLMB = false;
    private boolean mouseRMB = false;
    private boolean mouseMMB = false;

    private long frame = 0;
    private long fpsTrack = 0;

    private Timer t1 = new Timer(1000 / 60, e -> {
        repaint();
    });

    private Timer t2 = new Timer(1000, e -> {
        fpsTrack = frame;
        frame = 0;
    });

    private void wait(int millis) {
        long start = System.currentTimeMillis();
        while(start + millis < System.currentTimeMillis()) {System.out.print(".");}
    }

    public Game() {
        this.setFocusable(true);
        this.setBackground(Color.WHITE);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        t1.start();
        t2.start();
        // d.shuffle();
        for (Card c : d.cards) {
            try {
                c.loadImage(cardW, cardH);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        JButton shuffleButton = new JButton("Reshuffle");
        shuffleButton.addActionListener(e -> {
            int i = 5;
            while(i>0) {
                i--;
                d.shuffle();
                wait(100);
            }
            updated = true;
        });
        
        JButton dealButton = new JButton("Deal from top");
        dealButton.addActionListener(e -> {
            d.deal();
            updated = true;
        });

        JButton popButton = new JButton("Deal from bottom");
        popButton.addActionListener(e -> {
            d.pop();
            updated = true;
        });

        JButton resetButton = new JButton("Reset deck");
        resetButton.addActionListener(e -> {
            d = Deck.ofDefault();
            updated = true;
        });
        
        JPanel buttons = new JPanel();
        buttons.add(shuffleButton);
        buttons.add(dealButton);
        buttons.add(popButton);
        buttons.add(resetButton);
        
        this.setLayout(new BorderLayout());
        this.add(buttons, BorderLayout.SOUTH);
    }

    private void renderCard(Graphics2D g2, Card c, int x, int y, int w, int h) {
        
        g2.drawRoundRect(x, y, w, h, cardGapX, cardGapY);
        Image i;
        if(w != cardW || h != cardH && c.revealed) {
            i = c.front.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        } else if(w != cardW || h != cardH && !c.revealed) {
            i = c.isRed() ? Card.RED_BACK : Card.BLUE_BACK;
        } else {
            i = c.frontScale;
        }

        g2.drawImage(i, x, y, this);
        boolean hoveringme = new Rectangle(x, y, w, h).contains(mouseat);
        if (hoveringme) {
            if(mouseLMB)
                g2.setColor(cardLMBColor);
            else if(mouseMMB)
                g2.setColor(cardMMBColor);
            else
                g2.setColor(cardHoverColor);
            g2.fillRoundRect(x, y, w, h, cardGapX, cardGapY);
            g2.setColor(Color.BLACK);
        }
    }

    private void renderDeck(Graphics2D g2) {
        List<Card> draw = d.undealtCards();
        int x = cardGapX, y = cardGapY;
        for(int i = 0; i < draw.size(); i++) {
            Card c = draw.get(i);
            if(updated) {
                if(x + cardW + 6> PREF_W) {
                    x = 5;
                    y += cardH+5;
                }
                c.x = x;
                c.y = y;
                x+=cardW+5;
            } 
            renderCard(g2, c, c.x, c.y, cardW, cardH);
        }
        if( updated )
            updated = false;
        if(d.size != d.cards.size()) {
            renderCard(g2, d.lastDealt, PREF_W - 100, PREF_H - 200, 100, 175);
        }
    }
    
    private boolean updated = true;
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        renderDeck(g2);

        g2.drawString(fpsTrack + "", 10, 10);
        
        frame++;

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

    
    @Override public void mouseMoved(MouseEvent e) {
        mouseat = e.getPoint();
    }
    @Override public void mousePressed(MouseEvent e){
        int button = e.getButton();
        if(button == MouseEvent.BUTTON1)
            mouseLMB = true;
        else if(button == MouseEvent.BUTTON2)
            mouseRMB = true;
        else if(button == MouseEvent.BUTTON3)
            mouseMMB = true;
    }
    @Override public void mouseReleased(MouseEvent e){
        mouseMoved(e);
        int button = e.getButton();
        if (button == MouseEvent.BUTTON1)
            mouseLMB = false;
        else if (button == MouseEvent.BUTTON2)
            mouseRMB = false;
        else if (button == MouseEvent.BUTTON3)
            mouseMMB = false;
    }

    @Override public void mouseClicked(MouseEvent e){}
    @Override public void mouseEntered(MouseEvent e){}
    @Override public void mouseExited(MouseEvent e){}
    @Override public void mouseDragged(MouseEvent e){}

}
