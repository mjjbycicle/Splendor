package app.visualizers.entities;

import app.constants.Color;
import jGameLib.animation.AnimationComponent;
import jGameLib.core.GameState;
import jGameLib.ui2d.input.UserInputHandlerComponent;
import jGameLib.ui2d.input.UserInputState;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.utils.HoverDetectionComponent;
import jGameLib.ui2d.utils.ImageRendererComponent;
import jGameLib.ui2d.utils.PositionAnimation;
import jGameLib.util.math.Vec2;

import java.util.function.BooleanSupplier;

public class ChipEntity extends UIEntity {
    public ChipEntity(GameState state, Color color, int i, BooleanSupplier isOneHovered, Vec2 absLoc) {
        super(state);
        super.withBoundingBox(
                b -> {
                    b.setSize(40, 40);
                    b.setAbsolutePosition(absLoc);
                }
        ).addComponents(
                new ImageRendererComponent(
                        "chips/" + color.name() + "_CHIP.png"
                ),
                new AnimationComponent(),
                new HoverDetectionComponent(),
                new UserInputHandlerComponent() {
                    @Override
                    protected void update(UserInputState state) {
                        if (getComponent(HoverDetectionComponent.class).contains(state.getMousePosition()))
                            if (!getComponent(AnimationComponent.class).isAnimating()) {
                                if (isOneHovered.getAsBoolean()) {
                                    getComponent(AnimationComponent.class).applyAnimation(
                                            new PositionAnimation(
                                                    new Vec2(0, -8 * i),
                                                    10,
                                                    false
                                            )
                                    );
                                } else {
                                    getComponent(AnimationComponent.class).applyAnimation(
                                            new PositionAnimation(
                                                    absLoc,
                                                    10,
                                                    false
                                            )
                                    );
                                }
                            }
                    }
                }
        );
    }
}
