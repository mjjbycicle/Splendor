package app.visualizers;

import app.constants.ObjectLocations;
import app.objects.ChipStack;
import jGameLib.core.GameState;
import jGameLib.ui2d.input.MouseEvent;
import jGameLib.ui2d.input.UserInputHandlerComponent;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.rendering.UIRendererRootComponent;
import jGameLib.ui2d.utils.HoverDetectionComponent;

public class ChipStackVisualizer {
    public static void addInactiveChipStack(ChipStack stack, int order, GameState state) {
        for (int i = 0; i < stack.getValue().getNum(); i++) {
            int finalI = i;
            new UIEntity(state)
                    .withBoundingBox(
                            b -> {
                                b.setSize(25, 25);
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
                                b.setSize(40, 40);
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
        for (int i = 0; i < stack.getValue().getNum(); i++) {
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
                            },
                            ChipVisualizer.getChip(stack.getValue().getColor())
                    ).cast();
        }
    }
}
