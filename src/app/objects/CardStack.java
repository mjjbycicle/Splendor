package app.objects;

import app.constants.Color;
import app.helpers.SingleValue;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CardStack implements Comparable<CardStack> {
    private final Color color;
    private final List<Card> cards;

    public CardStack(Color color) {
        this.color = color;
        this.cards = new ArrayList<>();
    }

    public Color getColor() {
        return color;
    }

    public void pushCard(Card card) {
        cards.add(card);
    }

    public void removeCard(Card card) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).getID() == card.getID()) {
                cards.remove(i);
                break;
            }
        }
    }

    public List<Card> getCards() {
        return cards;
    }

    public SingleValue getValue() {
        return new SingleValue(color, cards.size());
    }

    public int getPoints() {
        return cards.stream().map(Card::getPoints).reduce(0, Integer::sum);
    }

    @Override
    public int compareTo(@NotNull CardStack o) {
        return color.getNumVal() - o.getColor().getNumVal();
    }

    public CardStack createClone() {
        CardStack res = new CardStack(color);
        res.cards.addAll(this.cards);
        return res;
    }
}