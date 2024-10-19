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

    public void push(Value chips) {
        value.plus(chips);
    }

    public boolean canTakeTwo() {
        return value.getNum() >= 4;
    }

    @Override
    public int compareTo(@NotNull ChipStack o) {
        return color.getNumVal() - o.color.getNumVal();
    }
}
