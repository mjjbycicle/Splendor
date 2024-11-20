package app.states.turn;

import app.constants.Color;
import app.game.Game;
import jGameLib.core.GameState;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TurnOneChipTaken extends GameState {
    private final Game game, prevGame;
    private final Color prevColor;
    private GameState nextState;

    public TurnOneChipTaken(Game game, Game prevGame, Color prevColor) {
        this.game = game;
        this.prevGame = prevGame;
        this.prevColor = prevColor;
        List<Boolean> canTakeSecond = game.canTakeSecond();
        game.visualizer.useGrayStacks(
                Arrays.asList(
                        prevColor == Color.RED && canTakeSecond.get(0),
                        prevColor == Color.BLUE || prevColor2 == Color.BLUE,
                        prevColor1 == Color.GREEN || prevColor2 == Color.GREEN,
                        prevColor1 == Color.BLACK || prevColor2 == Color.BLACK,
                        prevColor1 == Color.WHITE || prevColor2 == Color.WHITE,
                        true
                )
        );
        game.addGame(this);
    }

    @Override
    public void onUpdate() {
        Color clicked = game.getClickedChipStack();
        if (clicked != null) {
            if (clicked != Color.ANY) {
                if (prevColor == game.getClickedChipStack()) {
                    if (game.canTakeChip(clicked, true)) {
                        game.takeChip(clicked);
                        nextState = new TurnFinishedState(game, prevGame);
                    }
                } else if (game.canTakeChip(clicked, false)) {
                    game.takeChip(clicked);
                    nextState = new TurnTwoChipsTaken(game, prevGame, prevColor, clicked);
                }
            }
        }
    }

    @Override
    public boolean isFinished() {
        return nextState != null;
    }

    @Override
    public Iterator<? extends GameState> getStatesAfter() {
        return iteratorOver(new BetweenMovesState(game, nextState, 10));
    }
}
