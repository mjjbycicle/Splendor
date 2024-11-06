package app.helpers;

/**
 * Names each location that varies in a matrix.
 * Used with {@link Location} to allow easy conversion from a name to a {@link lib.math.Vec2}
 * for positioning on the screen.
 * <code>
 *     Location.getMatrixLocation(MatrixLocation.ACTIVE_PLAYER_CARDS, 0, 0)
 * </code>
 * returns a new {@link lib.math.Vec2} with the position of the top left card of {@link MatrixLocation#ACTIVE_PLAYER_CARDS}
 */
public enum MatrixLocation {
    ACTIVE_PLAYER_CARDS,
    GAME_MATRIX
}
