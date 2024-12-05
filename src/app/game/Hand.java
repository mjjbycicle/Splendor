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
        for (Map.Entry<Color, CardStack> entry : cards.entrySet()) {
            if (entry.getKey() != Color.ANY) total.plus(entry.getValue().getValue());
        }
        return total;
    }

    public Price buyCard(Card card) {
        Price res = chipsToPay(card);
        removeChips(res);
        cards.get(card.getValue().getColor()).pushCard(card);
        return res;
    }

    public void addChips(Value value) {
        for (ChipStack stack : chips.values()) {
            if (value.getGems().get(stack.getValue().getColor()) > 0) {
                stack.add(value);
            }
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
        int anyRequired = 0;
        for (Color color : Color.values()) {
            if (total.getGems().get(color) < cost.getGems().get(color)) {
                anyRequired += cost.getGems().get(color) - total.getGems().get(color);
                cost.getGems().put(color, getStackTotal().getGems().get(color));
            } else {
                cost.getGems().put(color, Math.max(0, cost.getGems().get(color) - getCardTotal().getGems().get(color)));
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

    public int getPoints() {
        int res = 0;
        for (Noble noble : nobles) {
            res += 3;
        }
        for (CardStack stack : cards.values()) {
            if (stack.getColor() != Color.ANY) {
                res += stack.getPoints();
            }
        }
        return res;
    }

    public Hand createClone() {
        Hand res = new Hand();
        cloneNobles(res.nobles);
        cloneChips(res.chips);
        cloneCards(res.cards);
        return res;
    }

    private void cloneNobles(List<Noble> newNobles) {
        newNobles.clear();
        newNobles.addAll(nobles);
    }

    private void cloneChips(Map<Color, ChipStack> newChips) {
        for (Map.Entry<Color, ChipStack> entry : chips.entrySet()) {
            newChips.put(entry.getKey(), chips.get(entry.getKey()).createClone());
        }
    }

    private void cloneCards(Map<Color, CardStack> newChips) {
        for (Map.Entry<Color, CardStack> entry : cards.entrySet()) {
            newChips.put(entry.getKey(), cards.get(entry.getKey()).createClone());
        }
    }

    public int getNumCards() {
        int res = 0;
        for (CardStack stack : cards.values()) {
            res += stack.getCards().size();
        }
        return res;
    }
}
