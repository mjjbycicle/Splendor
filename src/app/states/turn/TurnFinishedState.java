package app.states.turn;

import app.constants.FinalLocation;
import app.constants.Styles;
import app.game.Game;
import app.states.game.EndScreenState;
import app.states.game.TitleState;
import jGameLib.core.GameState;
import jGameLib.ui2d.rendering.UIRendererRootComponent;
import jGameLib.ui2d.utils.ButtonEntity;

import java.awt.*;
import java.util.Iterator;

public class TurnFinishedState extends GameState {
    private final Game game;
    private GameState nextState;
    private int framesAfterNext = 0;

    public TurnFinishedState(Game game, Game prevGame) {
        this.game = game;
        game.visualizer.usePlayerGrayCards(game.getActivePlayer());
        game.addGame(this);
        new ButtonEntity(
                this,
                "continue",
                new Color(0, 0, 0, 200),
                new Color(255, 200, 0, 200),
                Styles.buttonText
        ).addClickListener(
                (entity, me) -> {
                    if (game.isLastRound() && game.getActivePlayer().id == 3) {
                        nextState = new EndScreenState(game.getWinningPlayerID());
                    } else {
                        game.advanceRound();
                        nextState = new TurnStartedState(game);
                    }
                }
        ).withBoundingBox(
                b -> {
                    b.setAbsolutePosition(FinalLocation.CONTINUE_BUTTON.getLocation());
                    b.setRenderOrder(99);
                }
        ).addComponents(
                new UIRendererRootComponent()
        );
        new ButtonEntity(
                this,
                "cancel move",
                new Color(0, 0, 0, 200),
                new Color(255, 200, 0, 200),
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
        if (nextState != null) framesAfterNext++;
    }

    @Override
    public boolean isFinished() {
        return nextState != null && framesAfterNext >= 20;
    }

    @Override
    public Iterator<? extends GameState> getStatesAfter() {
        game.visualizer.cancelGrayCards();
        game.visualizer.cancelGrayCards();
        return iteratorOver(nextState);
    }
}
