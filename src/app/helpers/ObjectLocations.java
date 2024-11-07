package app.helpers;

import jGameLib.util.math.Vec2;

public enum ObjectLocations {
    INACTIVE_PLAYER_CARDS,
    INACTIVE_PLAYER_CHIPS,
    ACTIVE_PLAYER_CARDS,
    ACTIVE_PLAYER_CHIPS;

    public Vec2 getLocation(int order, Color color, int index) {
        Vec2 baseLocation = new Vec2(0, 0);
        switch (this) {
            case INACTIVE_PLAYER_CARDS -> baseLocation = new Vec2(500, -300);
            case INACTIVE_PLAYER_CHIPS -> baseLocation = new Vec2(500, -200);
            case ACTIVE_PLAYER_CARDS -> baseLocation = new Vec2(-200, -200);
            case ACTIVE_PLAYER_CHIPS -> baseLocation = new Vec2(-200, 400);
        }
        if (this == INACTIVE_PLAYER_CARDS || this == INACTIVE_PLAYER_CHIPS)
            baseLocation = baseLocation.plus(0, order * 300);
        baseLocation = baseLocation.plus(25 * color.getNumVal(), 0);
        baseLocation = baseLocation.plus(0, 25 * index);
        return baseLocation;
    }
}
