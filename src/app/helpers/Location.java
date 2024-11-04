package app.helpers;

import app.Constants;
import jGameLib.util.math.Vec2;

public class Location {
    public static Vec2 getLocation(FinalLocation loc) {
        return switch (loc) {
            case BUTTON_1 -> new Vec2(-100, 200);
            case BUTTON_2 -> new Vec2(-120, 200);
            case BUTTON_3 -> new Vec2(-140, 200);
            case DIALOG_BOX -> new Vec2(-120, 240);
            case ACTIVE_PLAYER_NAME -> new Vec2(0, 200);
            case TITLE -> new Vec2(0, -100);
            case START_BUTTON -> new Vec2(0, 50);
            case TITLE_RULES_BUTTON -> new Vec2(200, 300);
            case TURN_RULES_BUTTON -> new Vec2(200, 300);
        };
    }
}
