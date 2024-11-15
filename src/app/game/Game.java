package app.game;

import app.constants.Color;
import app.helpers.SingleValue;
import app.objects.Card;
import app.objects.CardDeck;
import app.objects.ChipStack;
import app.objects.Noble;
import app.visualizers.GameVisualizer;
import jGameLib.core.GameState;

import java.util.*;

public class Game {
    private final List<Player> players;
    private final List<CardDeck> decks;
    private final List<ChipStack> chipStacks;
    private final List<Noble> nobles;
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
        visualizer = new GameVisualizer(players, decks, chipStacks, nobles);
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

    public Card getClickedCard() {
        return visualizer.getCardClicked();
    }

    public int getClickedChipStack() {
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
}
