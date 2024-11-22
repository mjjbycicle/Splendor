package app.states.turn;

import app.constants.Color;
import app.constants.FinalLocation;
import app.constants.Styles;
import app.game.Game;
import app.helpers.SingleValue;
import jGameLib.core.GameState;
import jGameLib.ui2d.rendering.UIRendererRootComponent;
import jGameLib.ui2d.utils.ButtonEntity;

import java.util.Iterator;

public class TurnRemoveCards extends GameState {
    private final Game game, prevGame;
    private GameState nextState;

    public TurnRemoveCards(Game game, Game prevGame) {
        this.game = game;
        this.prevGame = prevGame;
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
        Color clicked = game.getActivePlayer().visualizer.getClickedChipStack();
        if (clicked != null) {
            game.getActivePlayer().hand.removeChips(
                    new SingleValue(
                            clicked,
                            1
                    )
            );
            if (game.getActivePlayer().hand.overTenChips()) {
                nextState = new TurnRemoveCards(game, prevGame);
            } else {
                nextState = new TurnFinishedState(game, prevGame);
            }
        }
    }

    @Override
    public boolean isFinished() {
        return nextState != null;
    }

    @Override
    public Iterator<? extends GameState> getStatesAfter() {
        return iteratorOver(nextState);
    }
}
