import card.*;
import cards.deck.*;

public class tester {
    public static void main(String[] args) {
        System.out.println();
        Deck d = Deck.ofDefault();
        while(!d.isEmpty()) {
            System.out.println(d.size + " -- " + d.deal());
        }
    }
}
