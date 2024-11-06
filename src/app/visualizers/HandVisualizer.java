package app.visualizers;

import app.helpers.Color;
import app.objects.CardStack;
import app.objects.ChipStack;
import jGameLib.core.GameState;
import jGameLib.ui2d.rendering.UIEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HandVisualizer {
    private final Map<Color, ChipStack> chips;
    private final Map<Color, CardStack> cards;

    public HandVisualizer(Map<Color, ChipStack> chips, Map<Color, CardStack> cards) {
        this.chips = chips;
        this.cards = cards;
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
}
