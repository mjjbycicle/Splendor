package app.visualizers;

import app.constants.Color;
import app.constants.ObjectLocations;
import app.objects.ChipStack;
import app.visualizers.entities.ChipStackEntity;
import jGameLib.core.GameState;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.util.math.Vec2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameChipStacksVisualizer {
    private final List<ChipStack> chipStacks;
    private final List<Boolean> chipStacksClicked;
    private final Map<Color, Integer> chipStacksHovered;

    public GameChipStacksVisualizer(List<ChipStack> chipStacks) {
        this.chipStacks = chipStacks;
        chipStacksClicked = new ArrayList<>();
        for (int i = 0; i < chipStacks.size(); i++) chipStacksClicked.add(false);
        chipStacksHovered = new HashMap<>();
        for (Color color : Color.values()) chipStacksHovered.put(color, 0);
    }

    public void addChipStacks(GameState state) {
        for (int i = 0; i < chipStacks.size(); i++) {
            int finalI = i;
            try {
                new ChipStackEntity(
                        state, chipStacks.get(i),
                        ObjectLocations.GAME_CHIPS.getChipLocation(chipStacks.get(i).getValue().getColor())
                );
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public int getClickedChipStack() {
        for (int i = 0; i < chipStacksClicked.size(); i++) {
            if (chipStacksClicked.get(i)) {
                if (chipStacks.get(i).getValue().getNum() != 0) return i;
            }
        }
        return -1;
    }

    public boolean canTakeChip(int i, boolean second) {
        if (second) {
            return chipStacks.get(i).canTakeTwo();
        } else {
            return chipStacks.get(i).getValue().getNum() != 0;
        }
    }

    public void takeChip(int i) {
        chipStacks.get(i).takeOne();
    }
}
