package app.states.turn;

import app.constants.Color;
import app.constants.FinalLocation;
import app.constants.Styles;
import app.game.Game;
import jGameLib.core.GameState;
import jGameLib.ui2d.rendering.UIRendererRootComponent;
import jGameLib.ui2d.utils.ButtonEntity;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class TurnOneChipTaken extends GameState {
    private final Game game, prevGame;
    private final Color prevColor;
    private GameState nextState;
    private int framesAfterNext = 0;

    public TurnOneChipTaken(Game game, Game prevGame, Color prevColor) {
        this.game = game;
        this.prevGame = prevGame;
        this.prevColor = prevColor;
        List<Boolean> canTakeSecond = game.canTakeSecond();
        game.visualizer.useGrayStacks(
                Arrays.asList(
                        prevColor == Color.RED && canTakeSecond.get(0),
                        prevColor == Color.BLUE && canTakeSecond.get(1),
                        prevColor == Color.GREEN && canTakeSecond.get(2),
                        prevColor == Color.BLACK && canTakeSecond.get(3),
                        prevColor == Color.WHITE && canTakeSecond.get(4),
                        true
                )
        );
        game.visualizer.usePlayerGrayCards(game.getActivePlayer());
        game.addGame(this);
        new ButtonEntity(
                this,
                "cancel move",
                new java.awt.Color(0, 0, 0, 200),
                new java.awt.Color(255, 200, 0, 200),
                Styles.buttonText
        ).addClickListener(
                (entity, me) -> {
                    nextState = new TurnStartedState(prevGame);
                }
        ).withBoundingBox(
                b -> {
                    b.setAbsolutePosition(FinalLocation.CANCEL_BUTTON.getLocation());
                    b.setRenderOrder(99);
                }
        ).addComponents(
                new UIRendererRootComponent()
        );
    }

    @Override
    public void onUpdate() {
        Color clicked = game.getClickedChipStack();
        if (clicked != null) {
            if (clicked != Color.ANY) {
                if (prevColor == game.getClickedChipStack()) {
                    if (game.canTakeChip(clicked, true)) {
                        game.takeChip(clicked);
                        if (game.getActivePlayer().hand.overTenChips()) {
                            nextState = new TurnRemoveCards(game, prevGame);
                        } else {
                            nextState = new TurnFinishedState(game, prevGame);
                        }
                    }
                } else if (game.canTakeChip(clicked, false)) {
                    game.takeChip(clicked);
                    nextState = new TurnTwoChipsTaken(game, prevGame, prevColor, clicked);
                }
            }
        }
        if (nextState != null) framesAfterNext++;
    }

    @Override
    public boolean isFinished() {
        return nextState != null && framesAfterNext >= 10;
    }

    @Override
    public Iterator<? extends GameState> getStatesAfter() {
        game.visualizer.cancelGrayCards();
        game.visualizer.cancelGrayStacks();
        return iteratorOver(nextState);
    }
}
