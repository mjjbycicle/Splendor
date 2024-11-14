package app.visualizers.entities;

import app.constants.ObjectLocations;
import app.constants.Sizes;
import app.objects.Card;
import app.visualizers.animations.SizeAnimation;
import jGameLib.animation.AnimationComponent;
import jGameLib.core.GameState;
import jGameLib.ui2d.input.MouseEvent;
import jGameLib.ui2d.input.UserInputHandlerComponent;
import jGameLib.ui2d.input.UserInputState;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.utils.HoverDetectionComponent;
import jGameLib.ui2d.utils.ImageRendererComponent;
import jGameLib.ui2d.utils.RoundedRectRendererComponent;
import jGameLib.util.math.Vec2;

import java.awt.*;
import java.util.List;
import java.util.function.IntSupplier;

public class CardEntity extends UIEntity {
    private final Vec2 loc;

    public CardEntity(GameState state, Card card, IntSupplier numHovered, int i, int j, List<List<Boolean>> cardsClicked, boolean gray) {
        super(state);
        try {
            loc = ObjectLocations.GAME_CARD_MATRIX.getMatrixLocation(i, j);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        super.withBoundingBox(
                b -> {
                    b.setSize(Sizes.ACTIVE_CARD.size);
                    b.setAbsolutePosition(loc);
                }
        ).addComponents(
                new ImageRendererComponent(
                        "cards/card face pics/CARD_" + card.getID() + ".png"
                ),
                new AnimationComponent(),
                new HoverDetectionComponent()
        );
        if (gray) {
            super.withBoundingBox(
                    b -> {
                        b.addChild(
                                new UIEntity(state)
                                        .withBoundingBox(
                                                b2 -> {
                                                    b2.setSize(Sizes.ACTIVE_CARD.size);
                                                    b2.setRelativePosition(0, 0);
                                                    b2.setRenderOrder(1000);
                                                }
                                        ).addComponent(
                                                new RoundedRectRendererComponent(
                                                        5,
                                                        null,
                                                        new Color(0, 0, 0, 186)
                                                )
                                        ).cast(UIEntity.class).getBoundingBox()
                        );
                    }
            );
        } else {
            super.addComponent(
                    new UserInputHandlerComponent() {
                        @Override
                        protected void onMouseDown(MouseEvent me) {
                            if (getComponent(HoverDetectionComponent.class).contains(me.position())) {
                                cardsClicked.get(i).set(j, true);
                            }
                        }

                        @Override
                        protected void onMouseUp(MouseEvent me) {
                            if (getComponent(HoverDetectionComponent.class).contains(me.position())) {
                                cardsClicked.get(i).set(j, false);
                            }
                        }

                        @Override
                        protected void update(UserInputState state) {
                            if (!getComponent(AnimationComponent.class).isAnimating()) {
                                if (numHovered.getAsInt() == 1
                                        && getComponent(HoverDetectionComponent.class).contains(state.getMousePosition())) {
                                    getComponent(AnimationComponent.class).applyAnimation(
                                            new SizeAnimation(
                                                    7,
                                                    getBoundingBox().getSize(),
                                                    Sizes.HOVERED_ACTIVE_CARD.size
                                            )
                                    );
                                } else if (!getComponent(HoverDetectionComponent.class).contains(state.getMousePosition())) {
                                    getComponent(AnimationComponent.class).applyAnimation(
                                            new SizeAnimation(
                                                    7,
                                                    getBoundingBox().getSize(),
                                                    Sizes.ACTIVE_CARD.size
                                            )
                                    );
                                }
                            }
                            if (getComponent(HoverDetectionComponent.class).contains(state.getMousePosition())) {
                                getBoundingBox().setRenderOrder(100);
                            } else {
                                getBoundingBox().setRenderOrder(0);
                            }
                        }
                    }
            );
        }
    }
}
