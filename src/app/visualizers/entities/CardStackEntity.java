package app.visualizers.entities;

import app.constants.Color;
import app.constants.ObjectLocations;
import app.constants.Sizes;
import app.objects.Card;
import app.objects.CardStack;
import jGameLib.core.GameState;
import jGameLib.ui2d.input.UserInputHandlerComponent;
import jGameLib.ui2d.input.UserInputState;
import jGameLib.ui2d.rendering.BoundingBoxComponent;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.rendering.UIRendererRootComponent;
import jGameLib.ui2d.utils.HoverDetectionComponent;
import jGameLib.util.math.Vec2;

import java.util.ArrayList;
import java.util.List;

public class CardStackEntity extends UIEntity {
    private boolean isOneHovered;
    private final Vec2 loc;
    private final List<Boolean> clickedList = new ArrayList<>();
    private final List<Card> cards;

    public CardStackEntity(GameState state, CardStack stack, int order) {
        super(state);
        cards = stack.getCards();
        try {
            loc = ObjectLocations.INACTIVE_PLAYER_CARDS.getInactiveLocation(order, stack.getColor(), 0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        super.withBoundingBox(
                b -> {
                    b.setAbsolutePosition(loc);
                    b.setRenderOrder(1000);
                    b.setSize(Sizes.INACTIVE_CARD.size);
                    for (int x = 0; x < stack.getValue().getNum(); x++) {
                        clickedList.add(false);
                        try {
                            b.addChild(
                                    new CardEntity(
                                            state,
                                            stack.getCards().get(x),
                                            x,
                                            () -> isOneHovered,
                                            ObjectLocations.INACTIVE_PLAYER_CARDS.getInactiveLocation(order, stack.getColor(), x),
                                            stack.getColor() == Color.ANY,
                                            false,
                                            clickedList
                                    ).getBoundingBox()
                            );
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        ).addComponents(
                new UIRendererRootComponent(),
                new HoverDetectionComponent(),
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
                        isOneHovered = hovering;
                    }
                }
        );
    }

    public CardStackEntity(GameState state, CardStack stack) {
        super(state);
        cards = stack.getCards();
        try {
            loc = ObjectLocations.ACTIVE_PLAYER_CARDS.getActiveLocation(stack.getColor(), 0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        super.withBoundingBox(
                b -> {
                    b.setAbsolutePosition(loc);
                    b.setRenderOrder(1000);
                    b.setSize(Sizes.ACTIVE_CARD.size);
                    for (int x = 0; x < stack.getValue().getNum(); x++) {
                        clickedList.add(false);
                        try {
                            b.addChild(
                                    new CardEntity(
                                            state,
                                            stack.getCards().get(x),
                                            x,
                                            () -> isOneHovered,
                                            ObjectLocations.ACTIVE_PLAYER_CARDS.getActiveLocation(stack.getColor(), x),
                                            stack.getColor() == Color.ANY,
                                            true,
                                            clickedList
                                    ).getBoundingBox()
                            );
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
        ).addComponents(
                new UIRendererRootComponent(),
                new HoverDetectionComponent(),
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
                        isOneHovered = hovering;
                    }
                }
        );
    }

    public Card getClickedCard() {
        for (int i = 0; i < clickedList.size(); i++) {
            if (clickedList.get(i)) {
                Card res = cards.get(i);
                clickedList.remove(i);
                return res;
            }
        }
        return null;
    }
}
