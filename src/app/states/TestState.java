package app.states;

import app.game.Player;
import app.objects.Card;
import jGameLib.core.GameState;
import jGameLib.util.math.Vec2;

public class TestState extends GameState {
    public TestState() {
        Player player = new Player(0);
        player.hand.buyCard(new Card(0));
        player.addPlayer(this);
    }
}
