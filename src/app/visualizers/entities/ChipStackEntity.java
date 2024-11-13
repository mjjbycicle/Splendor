package app.visualizers.entities;

import app.constants.Color;
import app.constants.ObjectLocations;
import app.objects.ChipStack;
import app.visualizers.ChipVisualizer;
import jGameLib.core.GameState;
import jGameLib.ui2d.input.UserInputHandlerComponent;
import jGameLib.ui2d.input.UserInputState;
import jGameLib.ui2d.rendering.BoundingBoxComponent;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.rendering.UIRendererRootComponent;
import jGameLib.ui2d.utils.HoverDetectionComponent;
import jGameLib.ui2d.utils.ImageRendererComponent;
import jGameLib.util.math.Vec2;

public class ChipStackEntity extends UIEntity {
    private boolean isHovered = false;

    public ChipStackEntity(GameState state, ChipStack stack, Vec2 baseLoc) {
        super(state);
        super.withBoundingBox(
                        b -> {
                            b.setAbsolutePosition(baseLoc);
                            for (int i = 0; i < stack.getValue().getNum(); i++) {
                                int finalI = i;
                                b.addChild(
                                        new ChipEntity(
                                                state,
                                                stack.getValue().getColor(),
                                                i,
                                                () -> isHovered,
                                                baseLoc.plus(0, 3 * finalI)
                                        ).cast(UIEntity.class).getBoundingBox()
                                );
                            }
                        }
                ).addComponents(
                        new UIRendererRootComponent(),
                        new UserInputHandlerComponent() {
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
}
