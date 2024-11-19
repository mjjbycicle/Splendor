package app.states.test;

import app.constants.Color;
import app.game.Game;
import app.helpers.Price;
import app.objects.Card;
import jGameLib.core.GameState;

public class TestState extends GameState {
    private final Game game, prevGame;

    public TestState() {
        game = new Game();
        prevGame = game.createClone();
        game.getActivePlayer().hand.addChips(
                new Price()
                        .set(Color.BLUE, 2)
                        .set(Color.RED, 2)
                        .set(Color.GREEN, 2)
                        .set(Color.BLACK, 2)
                        .set(Color.WHITE, 2)
        );
        prevGame.getActivePlayer().hand.addChips(
                new Price()
                        .set(Color.BLUE, 3)
                        .set(Color.RED, 3)
                        .set(Color.GREEN, 3)
                        .set(Color.BLACK, 3)
                        .set(Color.WHITE, 3)
        );
        game.getActivePlayer().hand.buyCard(new Card(0));
    }
}
