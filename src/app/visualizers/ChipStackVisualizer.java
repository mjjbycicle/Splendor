package app.visualizers;

import app.constants.ObjectLocations;
import app.objects.ChipStack;
import jGameLib.core.GameState;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.rendering.UIRendererRootComponent;

public class ChipStackVisualizer {
    public static void addInactiveChipStack(ChipStack stack, int order, GameState state) {
        for (int i = 0; i < stack.getValue().getNum(); i++) {
            int finalI = i;
            new UIEntity(state)
                    .withBoundingBox(
                            b -> {
                                b.setSize(25, 25);
                                try {
                                    b.setAbsolutePosition(
                                            ObjectLocations.INACTIVE_PLAYER_CHIPS.getInactiveLocation(order, stack.getValue().getColor(), finalI)
                                    );
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                                b.setRenderOrder(100 - finalI);
                            }
                    )
                    .addComponents(
                            new UIRendererRootComponent(),
                            ChipVisualizer.getChip(stack.getValue().getColor())
                    ).cast();
        }
    }

    public static void addActiveChipStack(ChipStack stack, GameState state) {
        for (int i = 0; i < stack.getValue().getNum(); i++) {
            int finalI = i;
            new UIEntity(state)
                    .withBoundingBox(
                            b -> {
                                b.setSize(40, 40);
                                try {
                                    b.setAbsolutePosition(
                                            ObjectLocations.ACTIVE_PLAYER_CHIPS.getActiveLocation(stack.getValue().getColor(), finalI)
                                    );
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                                b.setRenderOrder(100 - finalI);
                            }
                    )
                    .addComponents(
                            new UIRendererRootComponent(),
                            ChipVisualizer.getChip(stack.getValue().getColor())
                    ).cast();
        }
    }
}
