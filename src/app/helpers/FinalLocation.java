package app.helpers;

/**
 * Names each location that does not vary based on an index.
 * Used with {@link Location} to allow easy conversion from a name to a {@link lib.math.Vec2}
 * for positioning on the screen.
 * <code>
 *     Location.getFinalLocation(FinalLocation.BUTTON_1)
 * </code>
 * returns a new {@link lib.math.Vec2} with the position of {@link FinalLocation#BUTTON_1}
 */
public enum FinalLocation {
    BUTTON_1,
    BUTTON_2,
    BUTTON_3,
    DIALOG_BOX,
    ACTIVE_PLAYER_NAME,
    TITLE,
    START_BUTTON,
    TITLE_RULES_BUTTON,
    TURN_RULES_BUTTON
}
