package app.helpers;

/**
 * Names each location that varies in a sequence.
 * <code>
 *     Location.getVariableLocation(VariableLocation.ACTIVE_PLAYER_CHIPS, 0)
 * </code>
 * returns a new {@link jGameLib.util.math.Vec2} with the position of the first ChipStack of {@link VariableLocation#ACTIVE_PLAYER_CHIPS}
 */
public enum VariableLocation {
    ACTIVE_PLAYER_CHIPS,
    GAME_CHIPS,
    ACTIVE_PLAYER_RESERVED,
    INACTIVE_PLAYER_NAME,
    DECK_LOCATION
}
