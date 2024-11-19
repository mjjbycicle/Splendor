package app.states.game_start;

import app.game.Game;
import app.states.test.TestGameState;
import app.states.turn.TurnStartedState;
import app.visualizers.entities.AnimatedCardMatrixEntity;
import jGameLib.core.GameState;

import java.util.Iterator;

public class DealCardsState extends GameState {
    public int framesElapsed = 0;
    private final Game game;
    private final AnimatedCardMatrixEntity cards;

    public DealCardsState(Game game) {
        this.game = game;
        cards = game.addDealingGame(this);
        cards.startAnimations();
    }

    @Override
    public void onUpdate() {
        framesElapsed++;
    }

    @Override
    public boolean isFinished() {
        return framesElapsed >= 25;
    }

    @Override
    public Iterator<? extends GameState> getStatesAfter() {
        return iteratorOver(new TurnStartedState(game));
    }
}
