package app.constants;

import jGameLib.util.math.Vec2;

public enum Sizes {
    INACTIVE_CARD(75, 100),
    ACTIVE_CARD(110, 160),
    HOVERED_ACTIVE_CARD(130, 200),
    NOBLE(150, 150),
    INACTIVE_CHIP(40, 40),
    ACTIVE_CHIP(75, 75);

    public final Vec2 size;

    Sizes(int width, int height) {
        size = new Vec2(width, height);
    }
}
