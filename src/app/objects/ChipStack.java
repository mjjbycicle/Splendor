package app.objects;

import app.helpers.*;
import org.jetbrains.annotations.NotNull;

public class ChipStack implements Comparable<ChipStack> {
    private SingleValue value;
    private final Color color;

    public ChipStack(SingleValue value) {
        this.value = value;
        this.color = value.getColor();
    }

    public void add(Value chips) {
        value.plus(chips);
    }

    public void take(Value chips) {
        value.minus(chips);
    }

    public boolean canTake(Value chips) {
        return value.contains(new SingleValue(color, chips.getGems().get(color)));
    }

    public SingleValue getValue() {
        return value;
    }

    @Override
    public int compareTo(@NotNull ChipStack o) {
        return color.getNumVal() - o.color.getNumVal();
    }
}