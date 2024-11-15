package app.visualizers;

import app.objects.Card;
import app.objects.CardDeck;
import app.visualizers.entities.CardMatrixEntity;
import jGameLib.core.GameState;
import jGameLib.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameCardMatrixVisualizer {
    private final List<List<Boolean>> cardsClicked;
    private final List<List<Card>> cardMatrix;
    private final List<CardDeck> decks;
    private final List<List<Boolean>> grayCards;

    public GameCardMatrixVisualizer(List<CardDeck> decks, List<List<Boolean>> grayCards) {
        this.decks = decks;
        cardsClicked = Arrays.asList(
                Arrays.asList(false, false, false, false),
                Arrays.asList(false, false, false, false),
                Arrays.asList(false, false, false, false),
                Arrays.asList(false, false, false, false)
        );
        cardMatrix = new ArrayList<>();
        initCards();
        this.grayCards = grayCards;
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
        new CardMatrixEntity(state, cardMatrix, cardsClicked, grayCards);
        for (CardDeck deck : decks) {
            deck.addAllEntities(state);
        }
    }

    public Pair<Integer, Integer> getIndexClicked() {
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

    public Card getCardClicked() {
        if (getIndexClicked() == null) return null;
        return cardMatrix.get(getIndexClicked().a()).get(getIndexClicked().b());
    }

    public Card getCard(Pair<Integer, Integer> index) {
        Card res = cardMatrix.get(index.a()).get(index.b());
        Card next = decks.get(index.a()).drawTopCard();
        cardMatrix.get(index.a()).set(index.b(), next);
        return next;
    }

    List<List<Card>> getCardMatrix() {
        return cardMatrix;
    }
}
