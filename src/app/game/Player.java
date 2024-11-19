package app.game;

import app.visualizers.PlayerVisualizer;
import jGameLib.core.GameState;

import java.util.*;

public class Player {
    public final Hand hand;
    private final int id;
    public final PlayerVisualizer visualizer;
    private int roundOrder;

    public Player(int id) {
        this.id = id;
        hand = new Hand();
        visualizer = new PlayerVisualizer(id, hand.chips, hand.cards, hand.nobles, hand::getPoints);
        roundOrder = id;
    }

    private Player(Hand newHand, int id, int roundOrder) {
        this.hand = newHand;
        this.id = id;
        this.roundOrder = roundOrder;
        visualizer = new PlayerVisualizer(id, hand.chips, hand.cards, hand.nobles, hand::getPoints);
    }

    public void addPlayer(GameState state) {
        if (roundOrder == 0) {
            visualizer.addPlayerActiveEntities(state);
        } else {
            visualizer.addPlayerInactiveEntities(roundOrder - 1, state);
        }
    }

    public void advancePlayer() {
        roundOrder--;
        roundOrder += 4;
        roundOrder %= 4;
    }

    public Player createClone() {
        return new Player(this.hand.createClone(), this.id, this.roundOrder);
    }
}
