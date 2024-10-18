package app.helpers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Value {

    private final Map<Color, Integer> gems;

    public Value() {
        gems = new HashMap<>();
        for (Color c : Color.values()) {
            gems.put(c, 0);
        }
    }

    public Map<Color, Integer> getGems() {
        return gems;
    }

    public Value with(Color color, int value) {
        gems.put(color, value);
        return this;
    }

    public Value plus(Value value) {
        for (Color i : Arrays.stream(Color.values()).toList()) {
            gems.put(i, gems.get(i) + value.gems.get(i));
        }
        return this;
    }

    public Value minus(Value value) {
        for (Color i : Arrays.stream(Color.values()).toList()) {
            gems.put(i, gems.get(i) - value.gems.get(i));
        }
        return this;
    }

    public boolean contains(Value value) {
        for (Color i : Arrays.stream(Color.values()).toList()) {
            if (gems.containsKey(i)) {
                if (gems.get(i) < value.getGems().get(i)) {
                    return false;
                }
            }
        }
        return true;
    }
}
