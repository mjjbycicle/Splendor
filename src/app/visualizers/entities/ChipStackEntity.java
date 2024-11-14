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
    private final Vec2 loc;

    public ChipStackEntity(GameState state, ChipStack stack, int i, List<Boolean> clickedList) {
        super(state);
        try {
            loc = ObjectLocations.GAME_CHIPS.getChipLocation(stack.getValue().getColor());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        super.withBoundingBox(
                b -> {
                    b.setAbsolutePosition(loc);
                    b.setRenderOrder(1000);
                    b.setSize(Sizes.ACTIVE_CHIP.size);
                    for (int x = 0; x < stack.getValue().getNum(); x++) {
                        b.addChild(
                                new ChipEntity(
                                        state,
                                        stack.getValue().getColor(),
                                        x,
                                        () -> isHovered,
                                        loc.minus(0, 3 * x)
                                ).cast(UIEntity.class).getBoundingBox()
                        );
                    }
                }
        ).addComponents(
                new UIRendererRootComponent(),
                new HoverDetectionComponent(),
                new UserInputHandlerComponent() {
                    @Override
                    protected void onMouseDown(MouseEvent me) {
                        if (getComponent(HoverDetectionComponent.class).contains(me.position())) {
                            clickedList.set(i, true);
                        }
                    }

                    @Override
                    protected void onMouseUp(MouseEvent me) {
                        if (getComponent(HoverDetectionComponent.class).contains(me.position())) {
                            clickedList.set(i, false);
                        }
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
    }

    public ChipStackEntity(GameState state, ChipStack stack, Vec2 loc) {
        this(
                state,
                stack,
                0,
                Arrays.asList(false)
        );
    }
}
