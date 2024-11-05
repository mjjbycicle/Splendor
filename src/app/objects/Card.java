package app.objects;

import app.Cards;
import app.helpers.*;
import java.awt.image.BufferedImage;

import static java.lang.System.out;

public class Card {
    private boolean faceDown;
    private final int id;
    private final Price price;
    private final SingleValue value;
    private final int points;
    private final BufferedImage faceUpPic;
    private final BufferedImage backPic;

    public Card(int id) {
        this.id = id;
        if (id != -1) {
            this.faceDown = false;
            price = Cards.cardPrices.get(id); //holds: map of each color + # needed (price)
            value = Cards.cardColors.get(id); //holds: card color + 1
            points = Cards.cardPoints.get(id); //holds: # of victory pts
            //front pic use imageNamer()
            try {
                faceUpPic = new ImageIO.read(Card.class.getResource("/res/cards/" + imageNamer(price, value, points) + ".png"));
            } catch (Exception e) {
                out.println("Card face image creating error");
            }
            /*back pic find off of id and use the right one of 3
            0-39 = back 1
            40-69 = back 2
            70-89 = back 3
            */
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

    public void setFaceDown(boolean faceDown) {
        this.faceDown = faceDown;
    }

    public boolean isFaceDown() {
        return faceDown;
    }

    public String imageNamer (Price p, SingleValue value, int pts) {
        String ret = "CARD_";
        ret += colorToImageString(value.getColor());
        ret += "_VAL" + pts + "_";

        int numW = p.getGems().get(Color.WHITE);
        int numR = p.getGems().get(Color.RED);
        int numG = p.getGems().get(Color.GREEN);
        int numBlu = p.getGems().get(Color.BLUE);
        int numBla = p.getGems().get(Color.BLACK);

        ret += numW + "_" + numR + "_" + numG + "_" + numBlu + "_" + numBla;

        return ret;
    }

    public String colorToImageString (Color c) {
        switch (c) {
            case Color.RED -> { return "R"; }
            case Color.GREEN -> { return "G"; }
            case Color.BLACK -> { return "BLA"; }
            case Color.BLUE -> { return "BLU"; }
            case Color.WHITE -> { return "W"; }
            case Color.ANY -> { return ""; }
        }
        return "";
    }
}