package app.helpers;

import java.util.HashMap;
import java.util.Map;

public class SingleValue extends Value {
    private final Color color;
    private int num;

    public SingleValue(Color color, int num) {
        this.color = color;
    }

    @Override
    public Map<Color, Integer> getGems() {
        Map<Color, Integer> gems = new HashMap<>();
        gems.put(color, num);
        return gems;
    }

    @Override
    public Value with(Color color, int value) {
        if (color.equals(this.color)) {
            this.num = value;
        }
        return this;
    }

    /**
     * @param other the {@link Value} to add to this.
     *              It only adds the value of the same color gem as
     *              {@link SingleValue#color} to itself.
     * @return this to allow chaining
     */
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
        for (Color color : Color.values()) {
            if (value.getGems().containsKey(color)) {
                if (!(color == this.color) && value.getGems().get(color) != 0) {
                    return false;
                } else {
                    if (num < value.getGems().get(color)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public int getNum() {
        return num;
    }

    public Color getColor() {
        return color;
    }
}
