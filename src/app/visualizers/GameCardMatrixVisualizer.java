package app.visualizers;

import app.constants.ObjectLocations;
import app.objects.CardDeck;
import jGameLib.core.GameState;
import jGameLib.ui2d.input.MouseEvent;
import jGameLib.ui2d.input.UserInputHandlerComponent;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.rendering.UIRendererRootComponent;
import jGameLib.ui2d.utils.HoverDetectionComponent;
import jGameLib.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameCardMatrixVisualizer {
    private final List<List<UIEntity>> cards;
    private final List<List<Boolean>> cardsClicked;
    private final List<CardDeck> decks;

    public GameCardMatrixVisualizer(List<CardDeck> decks) {
        cards = new ArrayList<>();
        this.decks = decks;
        cardsClicked = Arrays.asList(
                Arrays.asList(false, false, false, false),
                Arrays.asList(false, false, false, false),
                Arrays.asList(false, false, false, false),
                Arrays.asList(false, false, false, false)
        );
    }

    public void addCards(GameState state) {
        for (int i = 0; i < decks.size(); i++) {
            cards.add(new ArrayList<>());
            for (int j = 0; j < 4; j++) {
                int finalI = i;
                int finalJ = j;
                cards.get(i).add(
                        new UIEntity(state)
                                .withBoundingBox(
                                        b -> {
                                            try {
                                                b.setAbsolutePosition(ObjectLocations.GAME_CARD_MATRIX.getMatrixLocation(finalI, finalJ));
                                            } catch (Exception e) {
                                                throw new RuntimeException(e);
                                            }
                                            b.setWidth(75);
                                        }
                                )
                                .addComponents(
                                        new UIRendererRootComponent(),
                                        decks.get(i).drawTopCard().getImage(),
                                        new HoverDetectionComponent(),
                                        new UserInputHandlerComponent() {
                                            @Override
                                            protected void onMouseDown(MouseEvent me) {
                                                if (this.getEntity().getComponent(HoverDetectionComponent.class).contains(me.position()))
                                                    cardsClicked.get(finalI).set(finalJ, true);
                                            }

                                            @Override
                                            protected void onMouseUp(MouseEvent me) {
                                                if (this.getEntity().getComponent(HoverDetectionComponent.class).contains(me.position()))
                                                    cardsClicked.get(finalI).set(finalJ, false);
                                            }
                                        }
                                ).cast()
                );
            }
        }
    }

    public Pair<Integer, Integer> getCardClicked() {
        for (int i = 0; i < cardsClicked.size(); i++) {
            for (int j = 0; j < cardsClicked.get(0).size(); j++) {
                if (cardsClicked.get(i).get(j)) return new Pair<>(i, j);
            }
        }
        return null;
    }
}
