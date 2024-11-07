package app.helpers;

import jGameLib.util.math.Vec2;

public class GameMatrixLocation {
    public static Vec2 getLocation(int i, int j) {
        Vec2 baseLocation = new Vec2(-400, -200);
        baseLocation = baseLocation.plus(0, 75 * i);
        baseLocation = baseLocation.plus(150 * j, 0);
        return baseLocation;
    }
}
