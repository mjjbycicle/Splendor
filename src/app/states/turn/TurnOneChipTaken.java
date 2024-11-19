package app.states.turn;

import app.game.Game;
import jGameLib.core.GameState;

public class TurnOneChipTaken extends GameState {
    private final Game game;
    private GameState nextState;

    public TurnOneChipTaken(Game game, Game prevGame) {
        this.game = game;
        game.addGame(this);
    }
}
