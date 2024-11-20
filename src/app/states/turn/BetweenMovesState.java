package app.states.turn;

import app.game.Game;
import jGameLib.core.GameState;

import java.util.Iterator;

public class BetweenMovesState extends GameState {
    private final int duration;
    private final GameState nextState;
    private int t = 0;

    public BetweenMovesState(Game currentGame, GameState nextState, int duration) {
        this.nextState = nextState;
        this.duration = duration;
        currentGame.addGame(this);
    }

    @Override
    public void onUpdate() {
        t++;
    }

    @Override
    public boolean isFinished() {
        return t >= duration;
    }

    @Override
    public Iterator<? extends GameState> getStatesAfter() {
        return iteratorOver(nextState);
    }
}
