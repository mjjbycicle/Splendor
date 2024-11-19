package app.objects;

import app.constants.Color;
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

    public void takeOne() {
        value.minus(
                new SingleValue(color, 1)
        );
    }

    public boolean canTakeTwo() {
        return value.getNum() >= 4;
    }

    public SingleValue getValue() {
        return value;
    }

    @Override
    public int compareTo(@NotNull ChipStack o) {
        return color.getNumVal() - o.color.getNumVal();
    }

    public ChipStack createClone() {
        return new ChipStack(
                new SingleValue(
                        value.getColor(),
                        value.getNum()
                )
        );
    }
}