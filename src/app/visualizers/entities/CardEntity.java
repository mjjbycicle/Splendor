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
import jGameLib.ui2d.utils.PositionAnimation;
import jGameLib.ui2d.utils.RoundedRectRendererComponent;
import jGameLib.util.math.Vec2;

import java.awt.*;
import java.util.List;
import java.util.function.BooleanSupplier;
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
                    b.setSize(Sizes.GAME_CARD.size);
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
                                                    b2.setSize(Sizes.GAME_CARD.size);
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
                                                    Sizes.HOVERED_GAME_CARD.size
                                            )
                                    );
                                } else if (!getComponent(HoverDetectionComponent.class).contains(state.getMousePosition())) {
                                    getComponent(AnimationComponent.class).applyAnimation(
                                            new SizeAnimation(
                                                    7,
                                                    getBoundingBox().getSize(),
                                                    Sizes.GAME_CARD.size
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

    public CardEntity(GameState state, Card card, int i, BooleanSupplier isOneHovered, Vec2 absLoc, boolean gray, boolean active, List<Boolean> clickedList) {
        super(state);
        loc = absLoc;
        Vec2 size = active?Sizes.ACTIVE_CARD.size:Sizes.INACTIVE_CARD.size;
        super.withBoundingBox(
                b -> {
                    b.setSize(size);
                    b.setAbsolutePosition(absLoc);
                }
        ).addComponents(
                new ImageRendererComponent(
                        "cards/card face pics/CARD_" + card.getID() + ".png"
                ),
                new AnimationComponent(),
                new HoverDetectionComponent(),
                new UserInputHandlerComponent() {
                    @Override
                    protected void update(UserInputState state) {
                        if (!getComponent(AnimationComponent.class).isAnimating()) {
                            if (isOneHovered.getAsBoolean()) {
                                getComponent(AnimationComponent.class).applyAnimation(
                                        new PositionAnimation(
                                                new Vec2(0, (active ? 130 : 75) * i + 10),
                                                10,
                                                false
                                        )
                                );
                            } else {
                                getComponent(AnimationComponent.class).applyAnimation(
                                        new PositionAnimation(
                                                absLoc,
                                                10,
                                                true
                                        )
                                );
                            }
                        }
                    }

                    @Override
                    public void onMouseDown(MouseEvent me) {
                        if (getComponent(HoverDetectionComponent.class).contains(me.position())) {
                            clickedList.set(i, true);
                        } else {
                            clickedList.set(i, false);
                        }
                    }

                    @Override
                    public void onMouseUp(MouseEvent me) {
                        if (getComponent(HoverDetectionComponent.class).contains(me.position())) {
                            clickedList.set(i, false);
                        }
                    }
                }
        );
        if (gray) {
            super.withBoundingBox(
                    b -> {
                        b.addChild(
                                new UIEntity(state)
                                        .withBoundingBox(
                                                b2 -> {
                                                    b2.setSize(size);
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
        }
    }
}
