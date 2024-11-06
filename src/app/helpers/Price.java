package app.helpers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * a class that holds amounts of every {@link Color}
 */
public class Price extends Value{

    private final Map<Color, Integer> gems;

    public Price() {
        gems = new HashMap<>();
        for (Color c : Color.values()) {
            gems.put(c, 0);
        }
    }

    @Override
    public Map<Color, Integer> getGems() {
        return gems;
    }

    @Override
    public Price set(Color color, int value) {
        gems.put(color, value);
        return this;
    }

    @Override
    public Price plus(Value value) {
        for (Color i : Arrays.stream(Color.values()).toList()) {
            if (value.getGems().containsKey(i))
                if (gems.containsKey(i))
                    gems.put(i, gems.get(i) + value.getGems().get(i));
                else {
                    gems.put(i, value.getGems().get(i));
                }
        }
        return this;
    }

    @Override
    public Price minus(Value value) {
        for (Color i : Arrays.stream(Color.values()).toList()) {
            if (gems.containsKey(i))
                gems.put(i, gems.get(i) - value.getGems().get(i));
        }
        return this;
    }

    @Override
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
