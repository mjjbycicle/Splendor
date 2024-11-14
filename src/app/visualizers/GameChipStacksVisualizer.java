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

    public GameChipStacksVisualizer(List<ChipStack> chipStacks) {
        this.chipStacks = chipStacks;
        chipStacksClicked = new ArrayList<>();
        for (int i = 0; i < chipStacks.size(); i++) chipStacksClicked.add(false);
    }

    public void addChipStacks(GameState state) {
        for (int i = 0; i < chipStacks.size(); i++) {
            try {
                new ChipStackEntity(
                        state, chipStacks.get(i),
                        i,
                        chipStacksClicked
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
