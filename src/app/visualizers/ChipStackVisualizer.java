package app.visualizers;

import app.constants.ObjectLocations;
import app.constants.Sizes;
import app.objects.ChipStack;
import jGameLib.animation.Animation;
import jGameLib.animation.AnimationComponent;
import jGameLib.core.Entity;
import jGameLib.core.GameState;
import jGameLib.ui2d.input.MouseEvent;
import jGameLib.ui2d.input.UserInputHandlerComponent;
import jGameLib.ui2d.input.UserInputState;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.rendering.UIRendererRootComponent;
import jGameLib.ui2d.utils.HoverDetectionComponent;
import jGameLib.ui2d.utils.PositionAnimation;
import jGameLib.util.math.Vec2;

public class ChipStackVisualizer {
    public static void addInactiveChipStack(ChipStack stack, int order, GameState state) {
        for (int i = 0; i < stack.getValue().getNum(); i++) {
            int finalI = i;
            new UIEntity(state)
                    .withBoundingBox(
                            b -> {
                                b.setSize(Sizes.INACTIVE_CHIP.size);
                                try {
                                    b.setAbsolutePosition(
                                            ObjectLocations.INACTIVE_PLAYER_CHIPS.getInactiveLocation(order, stack.getValue().getColor(), finalI)
                                    );
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                                b.setRenderOrder(100 - finalI);
                            }
                    )
                    .addComponents(
                            new UIRendererRootComponent(),
                            ChipVisualizer.getChip(stack.getValue().getColor())
                    ).cast();
        }
    }

    public static void addActiveChipStack(ChipStack stack, GameState state) {
        for (int i = 0; i < stack.getValue().getNum(); i++) {
            int finalI = i;
            new UIEntity(state)
                    .withBoundingBox(
                            b -> {
                                b.setSize(Sizes.ACTIVE_CHIP.size);
                                try {
                                    b.setAbsolutePosition(
                                            ObjectLocations.ACTIVE_PLAYER_CHIPS.getActiveLocation(stack.getValue().getColor(), finalI)
                                    );
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                                b.setRenderOrder(100 - finalI);
                            }
                    )
                    .addComponents(
                            new UIRendererRootComponent(),
                            ChipVisualizer.getChip(stack.getValue().getColor())
                    ).cast();
        }
    }

    public static void addGameChipStack(ChipStack stack, GameState state, Runnable onTrue, Runnable onFalse) {
        System.out.println(stack.getValue().getNum());
        for (int i = stack.getValue().getNum(); i > -1; i--) {
            int finalI = i;
            new UIEntity(state)
                    .withBoundingBox(
                            b -> {
                                b.setSize(40, 40);
                                try {
                                    b.setAbsolutePosition(
                                            ObjectLocations.GAME_CHIPS.getChipLocation(stack.getValue().getColor(), finalI)
                                    );
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                                b.setRenderOrder(100 - finalI);
                            }
                    )
                    .addComponents(
                            new UIRendererRootComponent(),
                            new HoverDetectionComponent(),
                            new AnimationComponent(),
                            new UserInputHandlerComponent() {
                                @Override
                                protected void onMouseUp(MouseEvent me) {
                                    if (this.getEntity().getComponent(HoverDetectionComponent.class).contains(me.position()))
                                        onTrue.run();
                                }

                                @Override
                                protected void onMouseDown(MouseEvent me) {
                                    if (this.getEntity().getComponent(HoverDetectionComponent.class).contains(me.position()))
                                        onFalse.run();
                                }

                                @Override
                                protected void update(UserInputState state) {
                                    if (this.getEntity().getComponent(HoverDetectionComponent.class).contains(state.getMousePosition())
                                            && !this.getEntity().getComponent(AnimationComponent.class).isAnimating()) {
                                        this.getEntity().getComponent(AnimationComponent.class).applyAnimation(
                                                new PositionAnimation(
                                                        new Vec2(0, finalI * 30),
                                                        10,
                                                        false
                                                )
                                        );
                                    } else if (!this.getEntity().getComponent(AnimationComponent.class).isAnimating()) {
                                        try {
                                            this.getEntity().getComponent(AnimationComponent.class).applyAnimation(
                                                    new PositionAnimation(
                                                            ObjectLocations.GAME_CHIPS.getChipLocation(stack.getValue().getColor(), finalI),
                                                            10,
                                                            true
                                                    )
                                            );
                                        } catch (Exception e) {
                                            throw new RuntimeException(e);
                                        }
                                    }
                                }
                            },
                            ChipVisualizer.getChip(stack.getValue().getColor())
                    ).cast();
        }
    }
}
