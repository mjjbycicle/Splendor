package app.visualizers;

import app.constants.ObjectLocations;
import app.objects.Card;
import app.objects.CardStack;
import jGameLib.animation.AnimationComponent;
import jGameLib.core.GameState;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.rendering.UIRendererRootComponent;
import jGameLib.ui2d.utils.HoverDetectionComponent;

import java.util.List;

public class CardStackVisualizer {
    public static void addInactiveCardStack(CardStack stack, int order, GameState state) {
        List<Card> cards = stack.getCards();
        for (int i = 0; i < cards.size(); i++) {
            int finalI = i;
            new UIEntity(state)
                    .withBoundingBox(
                            b -> {
                                b.setWidth(50);
                                try {
                                    b.setAbsolutePosition(
                                            ObjectLocations.INACTIVE_PLAYER_CARDS.getInactiveLocation(order, stack.getColor(), finalI)
                                    );
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                                b.setRenderOrder(100 - finalI);
                            }
                    )
                    .addComponents(
                            new UIRendererRootComponent(),
                            cards.get(i).getImage()
                    ).cast();
        }
    }

    public static void addActiveCardStack(CardStack stack, GameState state) {
        List<Card> cards = stack.getCards();
        for (int i = 0; i < cards.size(); i++) {
            int finalI = i;
            new UIEntity(state)
                    .withBoundingBox(
                            b -> {
                                b.setWidth(75);
                                try {
                                    b.setAbsolutePosition(
                                            ObjectLocations.ACTIVE_PLAYER_CARDS.getActiveLocation(stack.getColor(), finalI)
                                    );
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                                b.setRenderOrder(100 - finalI);
                            }
                    )
                    .addComponents(
                            new UIRendererRootComponent(),
                            cards.get(i).getImage(),
                            new HoverDetectionComponent(),
                            new AnimationComponent()
                    ).cast();
        }
    }
}
