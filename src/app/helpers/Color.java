package app.helpers;

/**
 * Explicitly names each color for better code readability vs using a map or integers to denote each color.
 * <code>
 *     Color.RED == Color.RED
 * </code>
 * returns true,
 * <code>
 *     Color.RED == Color.BLUE
 * </code>
 * returns false
 */
public enum Color {
    RED(0),
    BLUE(1),
    GREEN(2),
    BLACK(3),
    WHITE(4),
    ANY(5);

    private int numVal;

    Color(int numVal) {
        this.numVal = numVal;
    }

    public int getNumVal() {
        return numVal;
    }
}
