package app.visualizers.entities;

import app.constants.ObjectLocations;
import app.constants.Sizes;
import app.initializers.Cards;
import app.objects.Card;
import app.visualizers.animations.SizeAnimation;
import jGameLib.animation.AnimationComponent;
import jGameLib.core.GameState;
import jGameLib.ui2d.input.MouseEvent;
import jGameLib.ui2d.input.UserInputHandlerComponent;
import jGameLib.ui2d.input.UserInputState;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.rendering.UIRendererRootComponent;
import jGameLib.ui2d.utils.HoverDetectionComponent;
import jGameLib.ui2d.utils.ImageRendererComponent;
import jGameLib.ui2d.utils.PositionAnimation;
import jGameLib.ui2d.utils.RoundedRectRendererComponent;
import jGameLib.util.math.Vec2;

import java.awt.*;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.IntSupplier;

public class MovingCardEntity extends UIEntity {
    private final Vec2 loc;
    private final int i, j;

    public MovingCardEntity(GameState state, Card card, int i, int j) {
        super(state);
        this.i = i;
        this.j = j;
        try {
            loc = ObjectLocations.GAME_CARD_MATRIX.getMatrixLocation(
                    3 - Cards.tiers.get(card.getID()), -1
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        super.withBoundingBox(
                b -> {
                    b.setSize(Sizes.GAME_CARD.size);
                    b.setAbsolutePosition(loc);
                }
        ).addComponents(
                new ImageRendererComponent(
                        "cards/card face pics/CARD_" + card.getID() + ".png"
                ),
                new AnimationComponent(),
                new HoverDetectionComponent(),
                new UIRendererRootComponent()
        );
    }

    public void startAnimation() {
        try {
            getComponent(AnimationComponent.class).applyAnimation(
                    new PositionAnimation(
                            ObjectLocations.GAME_CARD_MATRIX.getMatrixLocation(i, j),
                            20,
                            true
                    )
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
