package app.objects;

import app.initializers.Cards;
import app.visualizers.CardDeckVisualizer;
import jGameLib.core.GameState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CardDeck {
    private final List<Card> cards;
    private final int tier;

    public CardDeck(int tier) {
        this.cards = new ArrayList<>();
        this.tier = tier;
        for (int i = 0; i < 90; i++) {
            if (Cards.tiers.get(i) == tier)
                cards.add(new Card(i));
        }
        Collections.shuffle(cards);
    }

    public Card drawTopCard() {
        if (cards.isEmpty()) return null;
        Card res = cards.getFirst();
        cards.removeFirst();
        return res;
    }

    public void addAllEntities(GameState state) {
        CardDeckVisualizer.addAllEntities(tier, state);
    }

    public CardDeck createClone() {
        CardDeck res = new CardDeck(tier);
        res.setCards(this.cards);
        return res;
    }

    private void setCards(List<Card> newCards) {
        cards.clear();
        cards.addAll(newCards);
    }
}
