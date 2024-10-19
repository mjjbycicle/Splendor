package app.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck(int lvl) {
        cards = new ArrayList<Card>();
        switch (lvl) {
            case 1:
                for (int i = 0; i <= 39; i++) cards.add(new Card(i));
                break;
            case 2:
                for (int i = 40; i <= 69; i++) cards.add(new Card(i));
                break;
            case 3:
                for (int i = 70; i <= 89; i++) cards.add(new Card(i));
        }
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (!cards.isEmpty())
            return cards.remove(0);
        return new Card(-1);
    }
}
