package app.game;

import app.helpers.Color;
import app.helpers.Price;
import app.helpers.SingleValue;
import app.helpers.Value;
import app.objects.Card;
import app.objects.CardStack;
import app.objects.ChipStack;

import java.util.HashMap;
import java.util.Map;

public class Hand {
    private final Map<Color, ChipStack> chips;
    private final Map<Color, CardStack> cards;

    public Hand() {
        chips = new HashMap<>();
        cards = new HashMap<>();
        for (Color c : Color.values()) {
            chips.put(c, new ChipStack(new SingleValue(c, 0)));
            cards.put(c, new CardStack(c));
        }
    }

    private Price getHandTotal() {
        Price total = new Price();
        total.plus(getCardTotal());
        for (ChipStack stack : chips.values()) {
            total.plus(stack.getValue());
        }
        return total;
    }

    private Price getCardTotal() {
        Price total = new Price();
        for (CardStack stack : cards.values()) {
            total.plus(stack.getValue());
        }
        return total;
    }

    public void buyCard(Card card) {
        cards.get(card.getValue().getColor()).pushCard(card);
        takeChips(chipsToPay(card));
    }

    public void addChips(Value value) {
        for (ChipStack stack : chips.values()) {
            stack.add(value);
        }
    }

    public void takeChips(Value value) {
        for (ChipStack stack : chips.values()) {
            stack.take(value);
        }
    }

    public boolean canBuyCard(Card card) {
        Price total = getHandTotal();
        Price cost = card.getPrice();
        Price diff = total.minus(cost);
        int sum = 0;
        for (int n : diff.getGems().values()) {
            if (n < 0) {
                sum += n;
            }
        }
        return total.getGems().get(Color.ANY) >= sum;
    }

    private Price chipsToPay(Card card) {
        Price total = getHandTotal();
        Price cost = card.getPrice();
        Price diff = total.minus(cost);
        int sum = 0;
        for (Map.Entry<Color, Integer> entry : diff.getGems().entrySet()) {
            entry.setValue(entry.getValue() + cards.get(entry.getKey()).getValue().getNum());
            if (entry.getValue() < 0) {
                sum += entry.getValue();
                entry.setValue(0);
            }
        }
        diff.set(Color.ANY, sum);
        return diff;
    }
}
