package app.helpers;

import app.constants.Color;

import java.util.Map;

/**
 * A class that allows easier arithmetic between {@link Price} and {@link SingleValue}.
 * <code>
 *     new Price().with(Color.RED, 2).plus(new SingleValue(Color.BLUE, 3))
 * </code>
 * returns a new {@link Price} with 2 {@link Color#RED}, 3 {@link Color#BLUE}
 */
public abstract class Value {
    public abstract Map<Color, Integer> getGems();

    public abstract Value set(Color color, int value);

    public abstract Value plus(Value value);

    public abstract Value minus(Value value);

    public abstract boolean contains(Value value);
}
