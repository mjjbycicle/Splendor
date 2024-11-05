package app.visualizers;

import app.game.Player;
import jGameLib.core.GameState;
import jGameLib.ui2d.rendering.UIEntity;

public class PlayerVisualizer extends UIEntity {
    public PlayerVisualizer(GameState state, Player player) {
        super(state);
    }
}
