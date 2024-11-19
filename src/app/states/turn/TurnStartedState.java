package app.states.turn;

import app.constants.Color;
import app.game.Game;
import app.objects.Card;
import app.states.test.TestGameState;
import jGameLib.core.GameState;

import java.util.Iterator;

public class TurnStartedState extends GameState {
    private final Game game;
    private final Game prevGame;
    private GameState nextState;

    public TurnStartedState(Game game) {
        this.game = game;
        prevGame = game.createClone();
        game.visualizer.usePlayerGrayCards(game.getActivePlayer());
        game.addGame(this);
    }

    @Override
    public void onUpdate() {
        Card clickedCard = game.visualizer.getCardClicked();
        if (clickedCard != null) {
            if (game.getActivePlayer().hand.canBuyCard(clickedCard)) {
                game.addChips(game.getActivePlayer().hand.buyCard(clickedCard));
                game.replaceCard(game.getClickedIndex());
                game.visualizer.cancelGrayCards();
            }
            else if (game.getActivePlayer().hand.canReserve()) {
                game.takeAnyChip();
                game.getActivePlayer().hand.addReservedCard(clickedCard);
                game.replaceCard(game.getClickedIndex());
            }
            nextState = new TurnFinishedState(game, prevGame);
        }
        if (game.getClickedChipStack() != null) {
            if (game.getClickedChipStack() != Color.ANY) {
                game.takeChip(game.getClickedChipStack());
                nextState = new TestGameState(game);
            }
        }
    }

    @Override
    public boolean isFinished() {
        return nextState != null;
    }

    @Override
    public Iterator<? extends GameState> getStatesAfter() {
        game.visualizer.cancelGrayCards();
        return iteratorOver(nextState);
    }
}
