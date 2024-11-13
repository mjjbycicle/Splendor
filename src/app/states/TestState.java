package app.states;

import app.game.Game;
import jGameLib.core.GameState;
import jGameLib.util.Pair;

public class TestState extends GameState {
    private final Game game;

    public TestState() {
        game = new Game();
        game.addGame(this);
    }

    @Override
    protected void onUpdate() {
        Pair<Integer, Integer> clicked = game.getClickedCard();
        if (clicked != null) System.out.println("row: " + clicked.a() + " col: " + clicked.b());
        int clickedChips = game.getClickedChipStack();
        if (clickedChips != -1) System.out.println("chip : clickedChips");
    }
}
