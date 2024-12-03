package app.constants;

import jGameLib.util.math.Vec2;

/**
 * Names each location that does not vary based on an index.
 * <code>
 *     Location.getFinalLocation(FinalLocation.BUTTON_1)
 * </code>
 * returns a new {@link jGameLib.util.math.Vec2} with the position of {@link FinalLocation#BUTTON_1}
 */
public enum FinalLocation {
    BUTTON_1(-100, 200),
    BUTTON_2(-120, 200),
    BUTTON_3(-140, 200),
    DIALOG_BOX(-120, 240),
    ACTIVE_PLAYER_NAME(0, 200),
    START_BUTTON(0, 50),
    TITLE_RULES_BUTTON(0, 200),
    TURN_RULES_BUTTON(200, 300),
    ORIGIN(0, 0),
    BACK_BUTTON(775, 475),
    CONTINUE_BUTTON(375, 460),
    CANCEL_BUTTON(550, 460),
    WINNER_TEXT(-375, 67);

    private final Vec2 location;

    FinalLocation(int x, int y) {
        this.location = new Vec2(x, y);
    }

    public Vec2 getLocation() {
        return location;
    }
}
