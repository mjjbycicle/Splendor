package app.visualizers;

import app.game.Hand;
import app.helpers.Color;
import app.objects.Card;
import app.objects.CardStack;
import app.objects.ChipStack;

import java.util.Map;

public class HandVisualizer {
    private final Map<Color, ChipStack> chips;
    private final Map<Color, CardStack> cards;

    public HandVisualizer(Map<Color, ChipStack> chips, Map<Color, CardStack> cards) {
        this.chips = chips;
        this.cards = cards;
    }


}
