package app.visualizers;

import app.helpers.ObjectLocations;
import app.objects.Card;
import app.objects.CardStack;
import jGameLib.animation.AnimationComponent;
import jGameLib.core.GameState;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.rendering.UIRendererRootComponent;
import jGameLib.ui2d.utils.HoverDetectionComponent;

import java.util.ArrayList;
import java.util.List;

public class CardStackVisualizer {
    public static List<UIEntity> getInactiveCardStack(CardStack stack, int order, GameState state) {
        List<UIEntity> res = new ArrayList<>();
        List<Card> cards = stack.getCards();
        for (int i = 0; i < cards.size(); i++) {
            int finalI = i;
            res.add(
                    new UIEntity(state)
                            .withBoundingBox(
                                    b -> {
                                        b.setWidth(50);
                                        b.setAbsolutePosition(
                                                ObjectLocations.INACTIVE_PLAYER_CARDS.getLocation(order, stack.getColor(), finalI)
                                        );
                                        b.setRenderOrder(100 - finalI);
                                    }
                            )
                            .addComponents(
                                    new UIRendererRootComponent(),
                                    cards.get(i).getImage()
                            ).cast()
            );
        }
        return res;
    }

    public static List<UIEntity> getActiveCardStack(CardStack stack, GameState state) {
        List<UIEntity> res = new ArrayList<>();
        List<Card> cards = stack.getCards();
        for (int i = 0; i < cards.size(); i++) {
            int finalI = i;
            res.add(
                    new UIEntity(state)
                            .withBoundingBox(
                                    b -> {
                                        b.setWidth(75);
                                        b.setAbsolutePosition(
                                                ObjectLocations.ACTIVE_PLAYER_CARDS.getLocation(0, stack.getColor(), finalI)
                                        );
                                        b.setRenderOrder(100 - finalI);
                                    }
                            )
                            .addComponents(
                                    new UIRendererRootComponent(),
                                    cards.get(i).getImage(),
                                    new HoverDetectionComponent(),
                                    new AnimationComponent()
                            ).cast()
            );
        }
        return res;
    }
}
