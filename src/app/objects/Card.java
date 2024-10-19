package app.objects;

import app.Cards;
import app.helpers.*;

public class Card {
    private boolean faceDown;
    private final int id;
    private final Price price;
    private final SingleValue value;
    private final int points;

    public Card(int id) {
        this.id = id;
        if (id != -1) {
            this.faceDown = false;
            price = Cards.cardPrices.get(id);
            value = Cards.cardColors.get(id);
            points = Cards.cardPoints.get(id);
        } else {
            this.faceDown = false;
            price = new Price();
            value = new SingleValue(Color.ANY, 0);
            points = 0;
        }
    }

    public Price getPrice() {
        return price;
    }

    public SingleValue getValue() {
        return value;
    }

    public int getID() {
        return id;
    }

    public int getPoints() {
        return points;
    }
}
