package app.objects;

import app.constants.Color;
import app.initializers.Cards;
import app.helpers.*;
import app.visualizers.CardVisualizer;
import jGameLib.ui2d.utils.ImageRendererComponent;

public class Card {
    private boolean faceDown;
    private final int id;
    private final Price price;
    private final SingleValue value;
    private final int points;
    private final CardVisualizer visualizer;

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
        visualizer = new CardVisualizer(
                id,
                Cards.tiers.get(id),
                this::isFaceDown
        );
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

    public void setFaceDown(boolean faceDown) {
        this.faceDown = faceDown;
    }

    public boolean isFaceDown() {
        return faceDown;
    }

    public ImageRendererComponent getImage() {
        return visualizer.getImage();
    }

}