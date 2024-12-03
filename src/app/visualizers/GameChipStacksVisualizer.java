package app.visualizers;

import app.constants.Color;
import app.constants.ObjectLocations;
import app.constants.Sizes;
import app.objects.ChipStack;
import app.visualizers.entities.ChipStackEntity;
import jGameLib.core.GameState;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.rendering.UIRendererRootComponent;
import jGameLib.ui2d.utils.RoundedRectRendererComponent;
import jGameLib.util.math.Vec2;

import java.util.ArrayList;
import java.util.List;

public class GameChipStacksVisualizer {
    private final List<ChipStack> chipStacks;
    private final List<Boolean> chipStacksClicked;
    private final List<Boolean> grayStacks;

    public GameChipStacksVisualizer(List<ChipStack> chipStacks) {
        this.chipStacks = chipStacks;
        chipStacksClicked = new ArrayList<>();
        for (int i = 0; i < chipStacks.size(); i++) chipStacksClicked.add(false);
        grayStacks = new ArrayList<>();
        for (int i = 0; i < chipStacks.size(); i++) grayStacks.add(false);
    }

    public void addChipStacks(GameState state) {
        for (int i = 0; i < chipStacks.size(); i++) {
            Vec2 size = Sizes.ACTIVE_CHIP.size;
            Vec2 loc;
            ChipStack stack = chipStacks.get(i);
            try {
                loc = ObjectLocations.GAME_CHIPS.getChipLocation(stack.getValue().getColor());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            new ChipStackEntity(
                    state, chipStacks.get(i),
                    i,
                    chipStacksClicked,
                    loc,
                    size,
                    grayStacks.get(i)
            );
            if (grayStacks.get(i)) {
                new UIEntity(state).withBoundingBox(
                        b -> {
                            b.setAbsolutePosition(loc.minus(0, stack.getValue().getNum() * 1.25));
                            b.setRenderOrder(10000);
                            b.setSize(size.times(1, 1 + stack.getValue().getNum() * 0.05));
                        }
                ).addComponents(
                        new UIRendererRootComponent(),
                        new RoundedRectRendererComponent(
                                size.x / 3,
                                null,
                                new java.awt.Color(0, 0, 0, 186)
                        )
                );
            }
        }
    }

    public Color getClickedChipStack() {
        for (int i = 0; i < chipStacksClicked.size(); i++) {
            if (chipStacksClicked.get(i)) {
                if (chipStacks.get(i).getValue().getNum() != 0) return chipStacks.get(i).getValue().getColor();
            }
        }
        return null;
    }

    public void useGrayStacks(List<Boolean> gray) {
        for (int i = 0; i < grayStacks.size(); i++) {
            grayStacks.set(i, gray.get(i));
        }
    }

    public void cancelGrayStacks() {
        grayStacks.replaceAll(gray -> false);
    }
}
