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
    GAME_NOBLES,
    GAME_CHIPS;

    public Vec2 getInactiveLocation(int order, Color color, int index) throws Exception {
        Vec2 baseLocation;
        switch (this) {
            case INACTIVE_PLAYER_CARDS -> baseLocation = new Vec2(500, -475);
            case INACTIVE_PLAYER_CHIPS -> baseLocation = new Vec2(500, -300);
            default -> throw new Exception("Attempting to get inactive location of an active location");
        }
        baseLocation = baseLocation.plus(0, order * 300);
        baseLocation = baseLocation.plus(55 * color.getNumVal(), 0);
        baseLocation = baseLocation.plus(0, 25 * index);
        return baseLocation;
    }

    public Vec2 getActiveLocation(Color color, int index) throws Exception {
        Vec2 baseLocation;
        switch (this) {
            case ACTIVE_PLAYER_CARDS -> baseLocation = new Vec2(-139, -145);
            case ACTIVE_PLAYER_CHIPS -> baseLocation = new Vec2(-139, 375);
            default -> throw new Exception("Attempting to get active location of an inactive location");
        }
        baseLocation = baseLocation.plus(102.5 * color.getNumVal(), 0);
        baseLocation = baseLocation.plus(0, 25 * index);
        return baseLocation;
    }

    public Vec2 getNobleLocation(int order, int index) throws Exception {
        Vec2 baseLocation = new Vec2(0, 0);
        switch (this) {
            case INACTIVE_NOBLE_LOCATIONS -> baseLocation = new Vec2(500, -150);
            case ACTIVE_NOBLE_LOCATIONS -> baseLocation = new Vec2(0, 450);
            case GAME_NOBLES -> baseLocation = new Vec2(-854, 250);
            default -> throw new Exception("Attempting to get noble location of a non noble location");
        }
        if (this == INACTIVE_NOBLE_LOCATIONS) {
            baseLocation = baseLocation.plus(0, order * 325);
        }
        baseLocation = baseLocation.plus(139 * index, 0);
        return baseLocation;
    }

    public Vec2 getMatrixLocation(int i, int j) throws Exception {
        if (this != GAME_CARD_MATRIX) throw new Exception("Attempting to get matrix location of a non matrix location");
        Vec2 baseLocation = new Vec2(-715, -275);
        baseLocation = baseLocation.plus(j * 139, i * 185);
        return baseLocation;
    }

    public Vec2 getChipLocation(Color color) throws Exception {
        Vec2 baseLocation;
        if (this == ObjectLocations.GAME_CHIPS) {
            baseLocation = new Vec2(-854, 400);
        } else {
            throw new Exception("Attempting to get game chip location");
        }
        baseLocation = baseLocation.plus(110 * color.getNumVal(), 0);
        return baseLocation;
    }
}
