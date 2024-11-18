package app.helpers;

import app.constants.Color;

import java.util.HashMap;
import java.util.Map;

/**
 * class that holds an amount of a single {@link Color}
 */
public class SingleValue extends Value {
    private final Color color;
    private int num;

    public SingleValue(Color color, int num) {
        this.color = color;
        this.num = num;
    }

    @Override
    public Map<Color, Integer> getGems() {
        Map<Color, Integer> gems = new HashMap<>();
        for (Color color : Color.values()) {
            if (color == this.color) {
                gems.put(color, num);
            } else {
                gems.put(color, 0);
            }
        }
        return gems;
    }

    @Override
    public Value set(Color color, int value) {
        if (color.equals(this.color)) {
            this.num = value;
        }
        return this;
    }

    @Override
    public Value plus(Value other) {
        num += other.getGems().get(color);
        return this;
    }

    @Override
    public Value minus(Value other) {
        num -= other.getGems().get(color);
        return this;
    }

    @Override
    public boolean contains(Value value) {
       if (!value.getGems().containsKey(color)) {
           return true;
       }
       return value.getGems().get(color) <= num;
    }

    public int getNum() {
        return num;
    }

    public Color getColor() {
        return color;
    }
}
