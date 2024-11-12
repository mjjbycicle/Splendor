package app.visualizers;

import app.constants.ObjectLocations;
import app.constants.Sizes;
import app.objects.Card;
import app.objects.CardDeck;
import jGameLib.core.GameState;
import jGameLib.ui2d.input.MouseEvent;
import jGameLib.ui2d.input.UserInputHandlerComponent;
import jGameLib.ui2d.input.UserInputState;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.rendering.UIRendererRootComponent;
import jGameLib.ui2d.utils.HoverDetectionComponent;
import jGameLib.ui2d.utils.RoundedRectRendererComponent;
import jGameLib.util.Pair;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameCardMatrixVisualizer {
    private final List<List<Boolean>> cardsClicked;
    private final List<List<Card>> cardMatrix;
    private final List<CardDeck> decks;

    public GameCardMatrixVisualizer(List<CardDeck> decks) {
        this.decks = decks;
        cardsClicked = Arrays.asList(
                Arrays.asList(false, false, false, false),
                Arrays.asList(false, false, false, false),
                Arrays.asList(false, false, false, false),
                Arrays.asList(false, false, false, false)
        );
        cardMatrix = new ArrayList<>();
        initCards();
    }

    private void initCards() {
        for (int i = 0; i < 3; i++) {
            cardMatrix.add(Arrays.asList(
                    decks.get(i).drawTopCard(),
                    decks.get(i).drawTopCard(),
                    decks.get(i).drawTopCard(),
                    decks.get(i).drawTopCard()
            ));
        }
    }

    public void addCards(GameState state) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                int finalI = i;
                int finalJ = j;
                new UIEntity(state)
                        .withBoundingBox(
                                b -> {
                                    try {
                                        b.setAbsolutePosition(ObjectLocations.GAME_CARD_MATRIX.getMatrixLocation(finalI, finalJ));
                                    } catch (Exception e) {
                                        throw new RuntimeException(e);
                                    }
                                    b.setSize(Sizes.ACTIVE_CARD.location);
                                }
                        )
                        .addComponents(
                                new UIRendererRootComponent(),
                                cardMatrix.get(i).get(j) == null ?
                                        new RoundedRectRendererComponent(10, new Color(100, 100, 100, 100), new Color(0, 0, 0, 0)) :
                                        cardMatrix.get(i).get(j).getImage(),
                                new HoverDetectionComponent(),
                                new UserInputHandlerComponent() {
                                    private int startTime;

                                    @Override
                                    protected void onMouseDown(MouseEvent me) {
                                        if (this.getEntity().getComponent(HoverDetectionComponent.class).contains(me.position())) {
                                            cardsClicked.get(finalI).set(finalJ, true);
                                        }
                                    }

                                    @Override
                                    protected void onMouseUp(MouseEvent me) {
                                        if (this.getEntity().getComponent(HoverDetectionComponent.class).contains(me.position()))
                                            cardsClicked.get(finalI).set(finalJ, false);
                                    }

                                    @Override
                                    protected void update(UserInputState state) {
                                        if (this.getEntity().getComponent(HoverDetectionComponent.class).contains(state.getMousePosition())) {
                                            if (startTime == -1) startTime = 0;
                                            else startTime++;
                                            this.getEntity().cast(UIEntity.class).withBoundingBox(b -> {
                                                if (startTime >= 10) b.setSize(200, 300);
                                                else
                                                    b.setSize(150 + (double) (startTime) / 0.2, 200 +  startTime / 0.1);
                                                b.setRenderOrder(100);
                                            });
                                        } else {
                                            startTime = -1;
                                            this.getEntity().cast(UIEntity.class).withBoundingBox(b -> {
                                                b.setSize(150, 200);
                                                b.setRenderOrder(0);
                                            });
                                        }

                                    }
                                }
                        ).cast();
            }
        }
    }

    public Pair<Integer, Integer> getCardClicked() {
        for (int i = 0; i < cardsClicked.size(); i++) {
            for (int j = 0; j < cardsClicked.getFirst().size(); j++) {
                if (cardsClicked.get(i).get(j)) {
                    if (cardMatrix.get(i).get(j) != null)
                        return new Pair<>(i, j);
                }
            }
        }
        return null;
    }

    public Card getCard(Pair<Integer, Integer> index) {
        Card res = cardMatrix.get(index.a()).get(index.b());
        Card next = decks.get(index.a()).drawTopCard();
        cardMatrix.get(index.a()).set(index.b(), next);
        return next;
    }
}
