package app.visualizers;

import app.objects.ChipStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameChipStacksVisualizer {
    private final List<ChipStack> chipStacks;
    private final List<Boolean> chipStackClicked;

    public GameChipStacksVisualizer(List<ChipStack> chipStacks) {
        this.chipStacks = chipStacks;
        chipStackClicked = new ArrayList<>();
        for (int i = 0; i < chipStacks.size(); i++) chipStackClicked.add(false);
    }
}
