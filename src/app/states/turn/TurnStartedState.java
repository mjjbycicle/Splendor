package app.states.turn;

import app.constants.Color;
import app.game.Game;
import app.objects.Card;
import app.states.test.TestGameState;
import jGameLib.core.GameState;

import java.util.Arrays;
import java.util.Iterator;

public class TurnStartedState extends GameState {
    private final Game game;
    private final Game prevGame;
    private GameState nextState;
    private int framesAfterNext = 0;

    public TurnStartedState(Game game) {
        this.game = game;
        prevGame = game.createClone();
        game.visualizer.usePlayerGrayCards(game.getActivePlayer());
        game.visualizer.useGrayStacks(
                Arrays.asList(
                        false, false, false, false, false, true
                )
        );
        game.addGame(this);
    }

    @Override
    public void onUpdate() {
        Card clickedCard = game.visualizer.getCardClicked();
        if (clickedCard != null) {
            if (game.getActivePlayer().hand.canBuyCard(clickedCard)) {
                game.addChips(game.getActivePlayer().hand.buyCard(clickedCard));
                game.replaceCard(game.getClickedIndex());
            }
            else if (game.getActivePlayer().hand.canReserve()) {
                game.takeAnyChip();
                game.getActivePlayer().hand.addReservedCard(clickedCard);
                game.replaceCard(game.getClickedIndex());
            }
            nextState = new TurnFinishedState(game, prevGame);
        }
        Color clicked = game.getClickedChipStack();
        if (clicked != null) {
            if (clicked != Color.ANY) {
                game.takeChip(clicked);
                nextState = new TurnOneChipTaken(game, prevGame, clicked);
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
        game.visualizer.cancelGrayCards();
        game.visualizer.cancelGrayStacks();
        return iteratorOver(nextState);
    }
}
