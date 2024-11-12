package app.constants;

import jGameLib.util.math.Vec2;

public enum Sizes {
    INACTIVE_CARD(75, 100),
    ACTIVE_CARD(150, 200),
    NOBLE(150, 150),
    INACTIVE_CHIP(75, 75),
    ACTIVE_CHIP(100, 100);

    public final Vec2 location;

    Sizes(int width, int height) {
        location = new Vec2(width, height);
    }
}
