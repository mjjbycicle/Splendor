package app.visualizers;

import app.objects.ChipStack;
import jGameLib.core.GameState;
import jGameLib.ui2d.rendering.UIEntity;

import java.util.ArrayList;
import java.util.List;

public class GameChipStacksVisualizer {
    private final List<ChipStack> chipStacks;
    private final List<Boolean> chipStacksClicked;

    public GameChipStacksVisualizer(List<ChipStack> chipStacks) {
        this.chipStacks = chipStacks;
        chipStacksClicked = new ArrayList<>();
        for (int i = 0; i < chipStacks.size(); i++) chipStacksClicked.add(false);
    }

    public void addChipStacks(GameState state) {
        System.out.println("added chips");
        for (int i = 0; i < chipStacks.size(); i++) {
            int finalI = i;
            ChipStackVisualizer.addGameChipStack(
                    chipStacks.get(i),
                    state,
                    () -> chipStacksClicked.set(finalI, true),
                    () -> chipStacksClicked.set(finalI, false)
            );
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
