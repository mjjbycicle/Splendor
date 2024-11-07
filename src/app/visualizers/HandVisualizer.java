package app.visualizers;

import app.helpers.Color;
import app.objects.CardStack;
import app.objects.ChipStack;
import app.objects.Noble;
import jGameLib.core.GameState;
import jGameLib.ui2d.rendering.UIEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HandVisualizer {
    private final Map<Color, ChipStack> chips;
    private final Map<Color, CardStack> cards;
    private final List<Noble> nobles;

    public HandVisualizer(Map<Color, ChipStack> chips, Map<Color, CardStack> cards, List<Noble> nobles) {
        this.chips = chips;
        this.cards = cards;
        this.nobles = nobles;
    }

    public List<List<UIEntity>> getInactiveChipEntities(int order, GameState state) {
        List<List<UIEntity>> res = new ArrayList<>();
        for (ChipStack stack : chips.values()) {
            res.add(ChipStackVisualizer.getInactiveChipStack(stack, order, state));
        }
        return res;
    }

    public List<List<UIEntity>> getActiveChipEntities(GameState state) {
        List<List<UIEntity>> res = new ArrayList<>();
        for (ChipStack stack : chips.values()) {
            res.add(ChipStackVisualizer.getActiveChipStack(stack, state));
        }
        return res;
    }

    public List<List<UIEntity>> getInactiveCardEntities(int order, GameState state) {
        List<List<UIEntity>> res = new ArrayList<>();
        for (CardStack stack : cards.values()) {
            res.add(CardStackVisualizer.getInactiveCardStack(stack, order, state));
        }
        return res;
    }

    public List<List<UIEntity>> getActiveCardEntities(GameState state) {
        List<List<UIEntity>> res = new ArrayList<>();
        for (CardStack stack : cards.values()) {
            res.add(CardStackVisualizer.getActiveCardStack(stack, state));
        }
        return res;
    }

    public List<UIEntity> getInactiveNobleEntities(int order, GameState state) {
        List<UIEntity> res = new ArrayList<>();
        for (Noble noble : nobles) {
            res.add(
                    new UIEntity(state)
                            .withBoundingBox(
                                    b -> {
                                        b.setAbsolutePosition(

                                        )
                                    }
                            )
            )
        }
    }
}
