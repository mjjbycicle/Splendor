package app.visualizers;

import app.constants.Color;
import app.constants.ObjectLocations;
import app.constants.Sizes;
import app.objects.CardStack;
import app.objects.ChipStack;
import app.objects.Noble;
import app.visualizers.entities.ChipStackEntity;
import jGameLib.core.GameState;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.rendering.UIRendererRootComponent;

import java.util.List;
import java.util.Map;

public class HandVisualizer {
    private final Map<Color, ChipStack> chips;
    private final Map<Color, CardStack> cards;
    private final List<Noble> nobles;

    HandVisualizer(Map<Color, ChipStack> chips, Map<Color, CardStack> cards, List<Noble> nobles) {
        this.chips = chips;
        this.cards = cards;
        this.nobles = nobles;
    }

    private void addInactiveChipEntities(int order, GameState state) {
        for (ChipStack stack : chips.values()) {
            try {
                new ChipStackEntity(state, stack,
                        ObjectLocations.INACTIVE_PLAYER_CHIPS.getInactiveLocation(order, stack.getValue().getColor(), 0));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void addActiveChipEntities(GameState state) {
        for (ChipStack stack : chips.values()) {
            try {
                new ChipStackEntity(state, stack,
                        ObjectLocations.ACTIVE_PLAYER_CHIPS.getActiveLocation(stack.getValue().getColor(), 0));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void addInactiveCardEntities(int order, GameState state) {
        for (CardStack stack : cards.values()) {
            CardStackVisualizer.addInactiveCardStack(stack, order, state);
        }
    }

    private void addActiveCardEntities(GameState state) {
        for (CardStack stack : cards.values()) {
            CardStackVisualizer.addActiveCardStack(stack, state);
        }
    }

    private void addInactiveNobleEntities(int order, GameState state) {
        for (int i = 0; i < nobles.size(); i++) {
            int finalI = i;
            new UIEntity(state)
                    .withBoundingBox(
                            b -> {
                                b.setSize(Sizes.NOBLE.size);
                                try {
                                    b.setAbsolutePosition(
                                            ObjectLocations.INACTIVE_NOBLE_LOCATIONS.getNobleLocation(order, finalI)
                                    );
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                    )
                    .addComponents(
                            new UIRendererRootComponent(),
                            nobles.get(i).getImage()
                    ).cast();
        }
    }

    private void addActiveNobleEntities(GameState state) {
        for (int i = 0; i < nobles.size(); i++) {
            int finalI = i;
            new UIEntity(state)
                    .withBoundingBox(
                            b -> {
                                b.setSize(100, 100);
                                try {
                                    b.setAbsolutePosition(
                                            ObjectLocations.ACTIVE_NOBLE_LOCATIONS.getNobleLocation(0, finalI)
                                    );
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                    )
                    .addComponents(
                            new UIRendererRootComponent(),
                            nobles.get(i).getImage()
                    ).cast();
        }
    }

    public void addAllInactiveEntities(int order, GameState state) {
        addInactiveChipEntities(order, state);
        addInactiveCardEntities(order, state);
        addInactiveNobleEntities(order, state);
    }

    public void addAllActiveEntities(GameState state) {
        addActiveChipEntities(state);
        addActiveCardEntities(state);
        addActiveNobleEntities(state);
    }
}
