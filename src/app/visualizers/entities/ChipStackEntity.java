package app.visualizers.entities;

import app.constants.ObjectLocations;
import app.constants.Sizes;
import app.objects.ChipStack;
import app.visualizers.animations.SizeAnimation;
import jGameLib.animation.AnimationComponent;
import jGameLib.core.GameState;
import jGameLib.ui2d.input.MouseEvent;
import jGameLib.ui2d.input.UserInputHandlerComponent;
import jGameLib.ui2d.input.UserInputState;
import jGameLib.ui2d.rendering.BoundingBoxComponent;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.rendering.UIRendererRootComponent;
import jGameLib.ui2d.utils.HoverDetectionComponent;
import jGameLib.ui2d.utils.RoundedRectRendererComponent;
import jGameLib.util.math.Vec2;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class ChipStackEntity extends UIEntity {
    private boolean isHovered = false;

    public ChipStackEntity(GameState state, ChipStack stack, int i, List<Boolean> clickedList, Vec2 loc, Vec2 size, boolean gray) {
        super(state);
        super.withBoundingBox(
                b -> {
                    b.setAbsolutePosition(loc);
                    b.setRenderOrder(500);
                    for (int x = 0; x < stack.getValue().getNum(); x++) {
                        b.addChild(
                                new ChipEntity(
                                        state,
                                        stack.getValue().getColor(),
                                        x,
                                        () -> isHovered,
                                        new Vec2(0, -3 * x),
                                        loc,
                                        size
                                ).cast(UIEntity.class).getBoundingBox()
                        );
                    }
                }
        ).addComponents(
                new UIRendererRootComponent(),
                new HoverDetectionComponent()
        );
        if (gray) clickedList.set(i, false);
        if (!(gray && stack.getValue().getNum() > 0)) {
            super.addComponent(
                    new UserInputHandlerComponent() {
                        @Override
                        protected void onMouseDown(MouseEvent me) {
                            if (isHovered) clickedList.set(i, true);
                            else clickedList.set(i, false);
                        }

                        @Override
                        protected void onMouseUp(MouseEvent me) {
                            clickedList.set(i, false);
                        }

                        @Override
                        protected void update(UserInputState state) {
                            boolean hovering = false;
                            for (BoundingBoxComponent b2 : getBoundingBox().getChildren()) {
                                if (b2.getEntity().getComponent(HoverDetectionComponent.class).contains(state.getMousePosition())) {
                                    hovering = true;
                                    break;
                                }
                            }
                            isHovered = hovering;
                        }
                    }
            );
        } else {
            super.addComponent(
                    new UserInputHandlerComponent() {
                        @Override
                        protected void update(UserInputState state) {
                            clickedList.set(i, false);
                        }
                    }
            );
        }
    }
}
