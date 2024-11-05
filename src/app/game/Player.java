package app.game;

import java.util.*;

public class Player {
    private final Hand hand;
    private final int id;

    public Player(int id) {
        this.id = id;
        hand = new Hand();
    }
}
