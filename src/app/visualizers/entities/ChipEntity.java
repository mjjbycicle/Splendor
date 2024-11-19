package app.visualizers.entities;

import app.constants.Color;
import app.constants.Sizes;
import jGameLib.animation.AnimationComponent;
import jGameLib.core.GameState;
import jGameLib.ui2d.input.UserInputHandlerComponent;
import jGameLib.ui2d.input.UserInputState;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.utils.HoverDetectionComponent;
import jGameLib.ui2d.utils.ImageRendererComponent;
import jGameLib.ui2d.utils.PositionAnimation;
import jGameLib.ui2d.utils.RoundedRectRendererComponent;
import jGameLib.util.math.Vec2;

import java.util.function.BooleanSupplier;

public class ChipEntity extends UIEntity {
    public ChipEntity(GameState state, Color color, int i, BooleanSupplier isOneHovered, Vec2 loc, Vec2 absLoc, Vec2 size) {
        super(state);
        super.withBoundingBox(
                b -> {
                    b.setSize(size);
                    b.setRelativePosition(loc);
                }
        ).addComponents(
                new AnimationComponent(),
                new HoverDetectionComponent(),
                new UserInputHandlerComponent() {
                    @Override
                    protected void update(UserInputState state) {
                        if (!getComponent(AnimationComponent.class).isAnimating()) {
                            if (isOneHovered.getAsBoolean()) {
                                getComponent(AnimationComponent.class).applyAnimation(
                                        new PositionAnimation(
                                                new Vec2(0, -20 * i + 5),
                                                10,
                                                false
                                        )
                                );
                            } else {
                                getComponent(AnimationComponent.class).applyAnimation(
                                        new PositionAnimation(
                                                absLoc.minus(0, 3 * i),
                                                10,
                                                true
                                        )
                                );
                            }
                        }
                    }
                },
                new ImageRendererComponent(
                        "chips/" + color.name() + "_CHIP.png"
                )
        );
    }
}
