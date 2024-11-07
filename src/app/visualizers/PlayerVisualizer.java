package app.visualizers;

import app.Styles;
import app.game.Player;
import app.helpers.Color;
import app.objects.CardStack;
import app.objects.ChipStack;
import app.objects.Noble;
import jGameLib.core.GameState;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.utils.TextRendererComponent;
import jGameLib.ui2d.utils.TextStyleBuilder;

import java.util.List;
import java.util.Map;

public class PlayerVisualizer {
    private final HandVisualizer hand;
    private final int id;

    public PlayerVisualizer(int id, Map<Color, ChipStack> chips, Map<Color, CardStack> cards, List<Noble> nobles) {
        hand = new HandVisualizer(chips, cards, nobles);
        this.id = id;
    }

    private TextRendererComponent getInactiveNameComponent(GameState state) {
        return new TextRendererComponent(
                "player " + id,
                Styles.titleText
        );
    }
}
