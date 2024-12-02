package app.states.turn;

import app.constants.Color;
import app.game.Game;
import jGameLib.core.GameState;

import java.util.Arrays;
import java.util.Iterator;

public class TurnTwoChipsTaken extends GameState {
    private final Game game, prevGame;
    private final Color prevColor1, prevColor2;
    private GameState nextState;
    private int framesAfterNext = 0;

    public TurnTwoChipsTaken(Game game, Game prevGame, Color prevColor1, Color prevColor2) {
        this.game = game;
        this.prevGame = prevGame;
        this.prevColor1 = prevColor1;
        this.prevColor2 = prevColor2;
        game.visualizer.useGrayStacks(
                Arrays.asList(
                        prevColor1 == Color.RED || prevColor2 == Color.RED,
                        prevColor1 == Color.BLUE || prevColor2 == Color.BLUE,
                        prevColor1 == Color.GREEN || prevColor2 == Color.GREEN,
                        prevColor1 == Color.BLACK || prevColor2 == Color.BLACK,
                        prevColor1 == Color.WHITE || prevColor2 == Color.WHITE,
                        true
                )
        );
        game.visualizer.usePlayerGrayCards(game.getActivePlayer());
        game.addGame(this);
    }

    @Override
    public void onUpdate() {
        Color clicked = game.getClickedChipStack();
        if (clicked != null) {
            System.out.println(clicked.name());
            if (clicked != Color.ANY && clicked != prevColor1 && clicked != prevColor2) {
                game.takeChip(clicked);
                nextState = new TurnFinishedState(game, prevGame);
            }
        }
        if (nextState != null) framesAfterNext++;
    }

    @Override
    public boolean isFinished() {
        return nextState != null && framesAfterNext >= 10;
    }

    @Override
    public Iterator<? extends GameState> getStatesAfter() {
        game.visualizer.cancelGrayStacks();
        game.visualizer.cancelGrayStacks();
        return iteratorOver(nextState);
    }
}
