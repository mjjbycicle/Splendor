package app.visualizers;

import app.constants.Styles;
import app.constants.Color;
import app.constants.NameLocations;
import app.objects.CardStack;
import app.objects.ChipStack;
import app.objects.Noble;
import jGameLib.core.GameState;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.rendering.UIRendererRootComponent;
import jGameLib.ui2d.utils.TextRendererComponent;

import java.util.List;
import java.util.Map;

public class PlayerVisualizer {
    private final HandVisualizer hand;
    private final int id;

    public PlayerVisualizer(int id, Map<Color, ChipStack> chips, Map<Color, CardStack> cards, List<Noble> nobles) {
        hand = new HandVisualizer(chips, cards, nobles);
        this.id = id;
    }

    private TextRendererComponent getInactiveNameComponent() {
        return new TextRendererComponent(
                "player " + id,
                Styles.titleText
        );
    }

    public void addPlayerInactiveEntities(int order, GameState state) {
        hand.addAllInactiveEntities(order, state);
        new UIEntity(state)
                .withBoundingBox(
                        b -> {
                            b.setAbsolutePosition(NameLocations.INACTIVE_PLAYER_NAME.getLocation(order));
                        }
                ).addComponents(
                        new UIRendererRootComponent(),
                        getInactiveNameComponent()
                ).cast();
    }

    public void addPlayerActiveEntities(GameState state) {
        hand.addAllActiveEntities(state);
        new UIEntity(state)
                .withBoundingBox(
                        b -> {
                            b.setAbsolutePosition(NameLocations.ACTIVE_PLAYER_NAME.getLocation(0));
                        }
                ).addComponents(
                        new UIRendererRootComponent(),
                        getInactiveNameComponent()
                ).cast();
    }
}
