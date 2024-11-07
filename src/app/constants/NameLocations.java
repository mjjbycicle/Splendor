package app.constants;

import jGameLib.util.math.Vec2;

public enum NameLocations {
    INACTIVE_PLAYER_NAME,
    ACTIVE_PLAYER_NAME;

    public Vec2 getLocation(int order) {
        Vec2 baseLocation = new Vec2(0, 0);
        switch (this) {
            case INACTIVE_PLAYER_NAME -> baseLocation = new Vec2(500, -100);
            case ACTIVE_PLAYER_NAME -> baseLocation = new Vec2(-200, 500);
        }
        if (this == INACTIVE_PLAYER_NAME) {
            baseLocation = baseLocation.plus(0, 300 * order);
        }
        return baseLocation;
    }
}
