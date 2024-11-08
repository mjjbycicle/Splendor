package app.constants;

import jGameLib.util.math.Vec2;

public enum ObjectLocations {
    INACTIVE_PLAYER_CARDS,
    INACTIVE_PLAYER_CHIPS,
    ACTIVE_PLAYER_CARDS,
    ACTIVE_PLAYER_CHIPS,
    INACTIVE_NOBLE_LOCATIONS,
    ACTIVE_NOBLE_LOCATIONS,
    GAME_CARD_MATRIX,
    GAME_NOBLES;

    public Vec2 getInactiveLocation(int order, Color color, int index) throws Exception {
        Vec2 baseLocation;
        switch (this) {
            case INACTIVE_PLAYER_CARDS -> baseLocation = new Vec2(500, -300);
            case INACTIVE_PLAYER_CHIPS -> baseLocation = new Vec2(500, -200);
            default -> throw new Exception("Attempting to get inactive location of an active location");
        }
        baseLocation = baseLocation.plus(0, order * 325);
        baseLocation = baseLocation.plus(25 * color.getNumVal(), 0);
        baseLocation = baseLocation.plus(0, 25 * index);
        return baseLocation;
    }

    public Vec2 getActiveLocation(Color color, int index) throws Exception {
        Vec2 baseLocation;
        switch (this) {
            case ACTIVE_PLAYER_CARDS -> baseLocation = new Vec2(0, -200);
            case ACTIVE_PLAYER_CHIPS -> baseLocation = new Vec2(0, 400);
            default -> throw new Exception("Attempting to get active location of an inactive location");
        }
        baseLocation = baseLocation.plus(25 * color.getNumVal(), 0);
        baseLocation = baseLocation.plus(0, 25 * index);
        return baseLocation;
    }

    public Vec2 getNobleLocation(int order, int index) throws Exception {
        Vec2 baseLocation = new Vec2(0, 0);
        switch (this) {
            case INACTIVE_NOBLE_LOCATIONS -> baseLocation = new Vec2(500, -150);
            case ACTIVE_NOBLE_LOCATIONS -> baseLocation = new Vec2(0, 450);
            case GAME_NOBLES -> baseLocation = new Vec2(-600, 500);
            default -> throw new Exception("Attempting to get noble location of a non noble location");
        }
        if (this == INACTIVE_NOBLE_LOCATIONS) {
            baseLocation = baseLocation.plus(0, order * 325);
        }
        baseLocation = baseLocation.plus(25 * index, 0);
        return baseLocation;
    }

    public Vec2 getMatrixLocation(int i, int j) throws Exception {
        if (this != GAME_CARD_MATRIX) throw new Exception("Attempting to get matrix location of a non matrix location");
        Vec2 baseLocation = new Vec2(-300, -300);
        baseLocation = baseLocation.plus(j * 100, i * 200);
        return baseLocation;
    }
}
