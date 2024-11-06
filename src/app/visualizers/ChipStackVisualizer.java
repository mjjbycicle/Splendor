package app.visualizers;

import app.helpers.ObjectLocations;
import app.objects.ChipStack;
import jGameLib.core.GameState;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.rendering.UIRendererRootComponent;

import java.util.ArrayList;
import java.util.List;

public class ChipStackVisualizer {
    public static List<UIEntity> getInactiveChipStack(ChipStack stack, int order, GameState state) {
        List<UIEntity> res = new ArrayList<>();
        for (int i = 0; i < stack.getValue().getNum(); i++) {
            int finalI = i;
            res.add(
                    new UIEntity(state)
                            .withBoundingBox(
                                    b -> {
                                        b.setSize(25, 25);
                                        b.setAbsolutePosition(
                                                ObjectLocations.INACTIVE_PLAYER_CHIPS.getLocation(order, stack.getValue().getColor(), finalI)
                                        );
                                        b.setRenderOrder(100 - finalI);
                                    }
                            )
                            .addComponents(
                                    new UIRendererRootComponent(),
                                    ChipVisualizer.getChip(stack.getValue().getColor())
                            ).cast()
            );
        }
        return res;
    }

    public static List<UIEntity> getActiveChipStack(ChipStack stack, GameState state) {
        List<UIEntity> res = new ArrayList<>();
        for (int i = 0; i < stack.getValue().getNum(); i++) {
            int finalI = i;
            res.add(
                    new UIEntity(state)
                            .withBoundingBox(
                                    b -> {
                                        b.setSize(40, 40);
                                        b.setAbsolutePosition(
                                                ObjectLocations.ACTIVE_PLAYER_CHIPS.getLocation(0, stack.getValue().getColor(), finalI)
                                        );
                                        b.setRenderOrder(100 - finalI);
                                    }
                            )
                            .addComponents(
                                    new UIRendererRootComponent(),
                                    ChipVisualizer.getChip(stack.getValue().getColor())
                            ).cast()
            );
        }
        return res;
    }
}
