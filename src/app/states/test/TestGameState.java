package app.states.test;

import app.constants.Color;
import app.game.Game;
import app.objects.Card;
import jGameLib.core.GameState;

import java.util.Iterator;

public class TestGameState extends GameState {
    private GameState nextState;
    private final Game game;

    public TestGameState(Game game) {
        this.game = game;
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
            }
            else {
                game.takeAnyChip();
                game.getActivePlayer().hand.addReservedCard(clickedCard);
                game.replaceCard(game.getClickedIndex());
            }
            nextState = new TestGameState(game);
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
        return iteratorOver(nextState);
    }
}
