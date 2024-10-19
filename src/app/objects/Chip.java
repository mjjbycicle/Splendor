package app.objects;

import app.helpers.*;

public class Chip {
    private final Color color;

    public Chip(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Value getValue() {
        return new SingleValue(color, 1);
    }
}
