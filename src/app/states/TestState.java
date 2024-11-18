package app.states;

import app.constants.Color;
import app.game.Game;
import app.helpers.Price;
import app.objects.Card;
import jGameLib.core.GameState;
import jGameLib.util.Pair;

public class TestState extends GameState {
    private final Game game;

    public TestState() {
        game = new Game();
        game.getActivePlayer().hand.addChips(
                new Price()
                        .set(Color.BLUE, 2)
                        .set(Color.RED, 2)
                        .set(Color.GREEN, 2)
                        .set(Color.BLACK, 2)
                        .set(Color.WHITE, 2)
        );
        game.visualizer.usePlayerGrayCards(game.getActivePlayer());
        game.addGame(this);
    }
}
