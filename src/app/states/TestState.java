package app.states;

import app.constants.Color;
import app.game.Game;
import app.game.Player;
import app.helpers.Price;
import app.objects.Card;
import app.visualizers.HandVisualizer;
import jGameLib.core.GameState;

public class TestState extends GameState {
//    private final Game game;
//
//    public TestState() {
//        game = new Game();
//        game.addGame(this);
//    }
//
//    @Override
//    public void onUpdate() {
//        if (game.getClickedCard() != null) {
//            game.getActivePlayer().hand.buyCard(game.getClickedCard());
//        }
//    }

    public TestState() {
        Player p = new Player(0);
        p.hand.addChips(
                new Price()
                        .set(Color.RED, 30)
                        .set(Color.WHITE, 30)
                        .set(Color.BLUE, 30)
                        .set(Color.BLACK, 30)
                        .set(Color.GREEN, 30)
                        .set(Color.ANY, 30)
        );
        p.hand.buyCard(new Card(0));
//        p.hand.buyCard(new Card(20));
//        p.hand.buyCard(new Card(40));
//        p.hand.buyCard(new Card(60));
//        p.hand.buyCard(new Card(80));
        p.visualizer.addPlayerActiveEntities(this);
    }
}
