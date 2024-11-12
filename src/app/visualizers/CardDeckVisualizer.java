package app.visualizers;

import app.constants.ObjectLocations;
import app.constants.Sizes;
import jGameLib.core.GameState;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.rendering.UIRendererRootComponent;
import jGameLib.ui2d.utils.ImageRendererComponent;

public class CardDeckVisualizer {
    private static ImageRendererComponent getDeckImage(int tier) {
        return new ImageRendererComponent(
                "card/card face pics/FACE_DOWN_TIER_" + tier + ".png"
        );
    }

    public static void addAllEntities(int tier, GameState state) {
        new UIEntity(state)
                .withBoundingBox(
                        b -> {
                            try {
                                b.setAbsolutePosition(
                                        ObjectLocations.GAME_CARD_MATRIX.getMatrixLocation(-1, tier - 1)
                                );
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                            b.setSize(Sizes.ACTIVE_CARD.location);
                        }
                )
                .addComponents(
                        new UIRendererRootComponent(),
                        app.visualizers.CardDeckVisualizer.getDeckImage(tier)
                );
    }

}
