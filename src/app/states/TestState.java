package app.states;

import app.constants.Color;
import app.constants.ObjectLocations;
import app.game.Game;
import app.helpers.SingleValue;
import app.objects.ChipStack;
import app.visualizers.entities.ChipStackEntity;
import jGameLib.core.GameState;
import jGameLib.util.Pair;
import jGameLib.util.math.Vec2;

public class TestState extends GameState {
    private final Game game;

    public TestState() {
        game = new Game();
        game.addGame(this);
    }
}
