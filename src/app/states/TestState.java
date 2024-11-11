package app.states;

import app.game.Game;
import jGameLib.core.GameState;

public class TestState extends GameState {
    private final Game game;

    public TestState() {
        game = new Game();
        game.addGame(this);
    }
}
