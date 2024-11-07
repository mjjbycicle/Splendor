package app.game;

import app.visualizers.PlayerVisualizer;

import java.util.*;

public class Player {
    private final Hand hand;
    private final int id;
    private final PlayerVisualizer visualizer;

    public Player(int id) {
        this.id = id;
        hand = new Hand();
        visualizer = new PlayerVisualizer(id, hand.chips, hand.cards);
    }
}
