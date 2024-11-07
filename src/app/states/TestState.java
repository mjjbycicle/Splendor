package app.states;

import app.game.Player;
import app.objects.Card;
import jGameLib.core.GameState;

public class TestState extends GameState {
    public TestState() {
        Player player0 = new Player(0);
        if (player0.hand.canBuyCard(new Card(0)))
            player0.hand.buyCard(new Card(0));
        player0.addPlayer(this);
        Player player1 = new Player(1);
        player1.addPlayer(this);
        Player player2 = new Player(2);
        player2.addPlayer(this);
        Player player3 = new Player(3);
        player3.addPlayer(this);
    }
}
