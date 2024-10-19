package app.helpers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class Value {
    public abstract Map<Color, Integer> getGems();

    public abstract Value with(Color color, int value);

    public abstract Value plus(Value value);

    public abstract Value minus(Value value);

    public abstract boolean contains(Value value);
}
