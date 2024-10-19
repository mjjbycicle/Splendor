package app.helpers;

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
