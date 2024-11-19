package app.game;

import app.constants.Color;
import app.helpers.Price;
import app.helpers.SingleValue;
import app.objects.Card;
import app.objects.CardDeck;
import app.objects.ChipStack;
import app.objects.Noble;
import app.visualizers.GameVisualizer;
import app.visualizers.entities.AnimatedCardMatrixEntity;
import jGameLib.core.GameState;
import jGameLib.util.Pair;

import java.util.*;

public class Game {
    private final List<Player> players;
    private final List<CardDeck> decks;
    private final List<ChipStack> chipStacks;
    private final List<Noble> nobles;
    private final List<List<Card>> cardMatrix;
    private int activePlayer = 0;
    public final GameVisualizer visualizer;

    public Game() {
        players = Arrays.asList(
                new Player(0),
                new Player(1),
                new Player(2),
                new Player(3)
        );
        decks = Arrays.asList(
                new CardDeck(3),
                new CardDeck(2),
                new CardDeck(1)
        );
        nobles = new ArrayList<>();
        chipStacks = Arrays.asList(
                        new ChipStack(new SingleValue(Color.RED, 7)),
                        new ChipStack(new SingleValue(Color.BLUE, 7)),
                        new ChipStack(new SingleValue(Color.GREEN, 7)),
                        new ChipStack(new SingleValue(Color.BLACK, 7)),
                        new ChipStack(new SingleValue(Color.WHITE, 7)),
                        new ChipStack(new SingleValue(Color.ANY, 5))
                );
        addNobles();
        cardMatrix = new ArrayList<>();
        initCards();
        visualizer = new GameVisualizer(players, decks, chipStacks, nobles, cardMatrix);
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

    private void addNobles() {
        Set<Integer> nobleIDs = new HashSet<>();
        while (nobleIDs.size() < 5) {
            int x = (int) (Math.random() * 10);
            nobleIDs.add(x);
        }
        for (int i : nobleIDs) nobles.add(new Noble(i));
    }

    public void addGame(GameState state) {
        visualizer.addAllEntities(state);
    }

    public AnimatedCardMatrixEntity addDealingGame(GameState state) {
        return visualizer.addAnimationEntities(state);
    }

    public Card getClickedCard() {
        return visualizer.getCardClicked();
    }

    public Color getClickedChipStack() {
        return visualizer.getChipStackClicked();
    }

    public void advanceRound() {
        activePlayer++;
        activePlayer %= 4;
        for (Player player : players) player.advancePlayer();
    }

    public Player getActivePlayer() {
        return players.get(activePlayer);
    }

    public void addChips(Price chips) {
        for (ChipStack stack : chipStacks) {
            stack.add(chips);
        }
    }

    public void takeAnyChip() {
        boolean anyChipTaken = false;
        for (ChipStack stack : chipStacks) {
            if (stack.getValue().getColor() == Color.ANY) {
                if (stack.getValue().getNum() > 0) {
                    stack.take(new SingleValue(Color.ANY, 1));
                    anyChipTaken = true;
                }
            }
        }
        if (anyChipTaken) {
            getActivePlayer().hand.addChips(new SingleValue(Color.ANY, 1));
        }
    }

    public void takeChip(Color color) {
        for (ChipStack stack : chipStacks) {
            if (stack.getValue().getColor() == color) {
                if (stack.getValue().getNum() > 0) {
                    stack.take(new SingleValue(color, 1));
                }
            }
        }
        getActivePlayer().hand.addChips(new SingleValue(color, 1));
    }

    public void replaceCard(Pair<Integer, Integer> index) {
        Card newCard = decks.get(index.a()).drawTopCard();
        visualizer.replaceCard(index, newCard);
    }

    public Pair<Integer, Integer> getClickedIndex() {
        return visualizer.getClickedIndex();
    }

    public Game createClone() {
        Game res = new Game();
        clonePlayers(res.players);
        cloneDecks(res.decks);
        cloneNobles(res.nobles);
        cloneChips(res.chipStacks);
        cloneCards(res.cardMatrix);
        res.activePlayer = this.activePlayer;
        return res;
    }

    private void clonePlayers(List<Player> newPlayers) {
        for (int i = 0; i < players.size(); i++) {
            newPlayers.set(i, players.get(i).createClone());
        }
    }

    private void cloneDecks(List<CardDeck> newDecks) {
        for (int i = 0; i < decks.size(); i++) {
            newDecks.set(i, decks.get(i).createClone());
        }
    }

    private void cloneNobles(List<Noble> newNobles) {
        newNobles.clear();
        newNobles.addAll(nobles);
    }

    private void cloneChips(List<ChipStack> newChips) {
        for (int i = 0; i < chipStacks.size(); i++) {
            newChips.set(i, chipStacks.get(i).createClone());
        }
    }

    private void cloneCards(List<List<Card>> newCards) {
        for (int i = 0; i < cardMatrix.size(); i++) {
            for (int j = 0; j < cardMatrix.getFirst().size(); j++) {
                newCards.get(i).set(j, cardMatrix.get(i).get(j));
            }
        }
    }
}
