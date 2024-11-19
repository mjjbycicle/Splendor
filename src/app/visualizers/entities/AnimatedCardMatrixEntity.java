package app.visualizers.entities;

import app.objects.Card;
import jGameLib.core.GameState;
import jGameLib.ui2d.rendering.UIEntity;

import java.util.ArrayList;
import java.util.List;

public class AnimatedCardMatrixEntity extends UIEntity {
    private final List<List<Card>> cards;
    private final List<List<MovingCardEntity>> animatedCards;

    public AnimatedCardMatrixEntity(GameState state, List<List<Card>> cards) {
        super(state);
        this.cards = cards;
        animatedCards = new ArrayList<>();
    }

    public void addCards(GameState state) {
        for (int i = 0; i < cards.size(); i++) {
            animatedCards.add(new ArrayList<>());
            for (int j = 0; j < cards.getFirst().size(); j++) {
                animatedCards.get(i).add(
                        new MovingCardEntity(
                                state,
                                cards.get(i).get(j),
                                i, j
                        )
                );
            }
        }
    }

    public void startAnimations() {
        for (int i = 0; i < cards.size(); i++) {
            for (int j = 0; j < cards.getFirst().size(); j++) {
                animatedCards.get(i).get(j).startAnimation();
            }
        }
    }
}
