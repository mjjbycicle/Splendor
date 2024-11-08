package app.visualizers;

import app.game.Player;
import app.objects.CardDeck;
import app.objects.Noble;

import java.util.List;

public class GameVisualizer {
    private final List<Player> players;
    private final List<Noble> nobles;

    public GameVisualizer(List<Player> players, List<CardDeck> decks, List<Noble> nobles) {
        this.players = players;
        this.nobles = nobles;
    }
}
