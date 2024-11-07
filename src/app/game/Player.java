package app.game;

import app.visualizers.PlayerVisualizer;
import jGameLib.core.GameState;

import java.util.*;

public class Player {
    public final Hand hand;
    private final int id;
    private final PlayerVisualizer visualizer;
    private int roundOrder;

    public Player(int id) {
        this.id = id;
        hand = new Hand();
        visualizer = new PlayerVisualizer(id, hand.chips, hand.cards, hand.nobles);
        roundOrder = id;
    }

    public void addPlayer(GameState state) {
        if (roundOrder == 0) {
            visualizer.addPlayerActiveEntities(state);
        } else {
            visualizer.addPlayerInactiveEntities(roundOrder - 1, state);
        }
    }

    public void advancePlayer() {
        roundOrder++;
        roundOrder %= 4;
    }
}
