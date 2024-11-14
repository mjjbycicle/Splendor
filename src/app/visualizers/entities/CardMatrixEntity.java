package app.visualizers.entities;

import app.objects.Card;
import jGameLib.core.GameState;
import jGameLib.ui2d.input.MouseEvent;
import jGameLib.ui2d.input.UserInputHandlerComponent;
import jGameLib.ui2d.input.UserInputState;
import jGameLib.ui2d.rendering.BoundingBoxComponent;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.rendering.UIRendererRootComponent;
import jGameLib.ui2d.utils.HoverDetectionComponent;

import java.util.List;

public class CardMatrixEntity extends UIEntity {
    private int numHovered = 0;

    public CardMatrixEntity(GameState state, List<List<Card>> cardMatrix, List<List<Boolean>> cardsClicked) {
        super(state);
        super.withBoundingBox(
                b -> {
                    for (int i = 0; i < cardMatrix.size(); i++) {
                        for (int j = 0; j < cardMatrix.getFirst().size(); j++) {
                            b.addChild(
                                    new CardEntity(
                                            state,
                                            cardMatrix.get(i).get(j),
                                            () -> numHovered,
                                            i, j,
                                            cardsClicked,
                                            false
                                    ).getBoundingBox()
                            );
                        }
                    }
                }
        ).addComponents(
                new UIRendererRootComponent(),
                new UserInputHandlerComponent() {

                    @Override
                    protected void update(UserInputState state) {
                        int hovering = 0;
                        for (BoundingBoxComponent b2 : getBoundingBox().getChildren()) {
                            if (b2.getEntity().getComponent(HoverDetectionComponent.class).contains(state.getMousePosition())) {
                                hovering++;
                            }
                        }
                        numHovered = hovering;
                    }
                }
        );
    }
}
