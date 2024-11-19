package app.game;

import app.constants.Color;
import app.helpers.Price;
import app.helpers.SingleValue;
import app.helpers.Value;
import app.objects.Card;
import app.objects.CardStack;
import app.objects.ChipStack;
import app.objects.Noble;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hand {
    final Map<Color, ChipStack> chips;
    final Map<Color, CardStack> cards;
    final List<Noble> nobles;

    public Hand() {
        chips = new HashMap<>();
        cards = new HashMap<>();
        nobles = new ArrayList<>();
        for (Color c : Color.values()) {
            chips.put(c, new ChipStack(new SingleValue(c, 0)));
            cards.put(c, new CardStack(c));
        }
    }

    private Price getHandTotal() {
        return getCardTotal().plus(getStackTotal());
    }

    private Price getStackTotal() {
        Price total = new Price();
        for (ChipStack stack : chips.values()) {
            total.plus(stack.getValue());
        }
        return total;
    }

    private Price getCardTotal() {
        Price total = new Price();
        for (CardStack stack : cards.values()) {
            if (stack.getColor() != Color.ANY) total.plus(stack.getValue());
        }
        return total;
    }

    public Price buyCard(Card card) {
        cards.get(card.getValue().getColor()).pushCard(card);
        Price res = chipsToPay(card);
        removeChips(res);
        return res;
    }

    public void addChips(Value value) {
        for (ChipStack stack : chips.values()) {
            stack.add(value);
        }
    }

    public void removeChips(Value value) {
        for (ChipStack stack : chips.values()) {
            stack.take(value);
        }
    }

    public boolean canBuyCard(Card card) {
        return getHandTotal().contains(chipsToPay(card));
    }

    private Price chipsToPay(Card card) {
        Price total = getHandTotal();
        Price cost = card.getPrice();
        Price diff = total.minus(cost);
        int anyRequired = 0;
        for (Map.Entry<Color, Integer> entry : diff.getGems().entrySet()) {
            entry.setValue(entry.getValue() + cards.get(entry.getKey()).getValue().getNum());
            if (entry.getValue() < 0) {
                cost.set(entry.getKey(), cost.getGems().get(entry.getKey()) + entry.getValue());
                anyRequired -= entry.getValue();
                entry.setValue(0);
            }
        }
        cost.set(Color.ANY, anyRequired);
        return cost;
    }

    private int getTotalChips() {
        int res = 0;
        for (ChipStack stack : chips.values()) {
            res += stack.getValue().getNum();
        }
        return res;
    }

    public boolean overTenChips() {
        return getTotalChips() > 10;
    }

    public boolean canTakeNoble(Noble noble) {
        return getCardTotal().contains(noble.getPrice());
    }

    public void takeNoble(Noble noble) {
        nobles.add(noble);
    }

    public void addReservedCard(Card card) {
        cards.get(Color.ANY).pushCard(card);
    }

    public boolean canReserve() {
        return cards.get(Color.ANY).getCards().size() < 3;
    }
}
