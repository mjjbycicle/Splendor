package app.states.game;

import app.constants.Styles;
import app.constants.FinalLocation;
import app.game.Game;
import jGameLib.core.GameState;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.rendering.UIRendererRootComponent;
import jGameLib.ui2d.utils.*;
import jGameLib.util.math.Vec2;

import java.awt.*;
import java.util.Iterator;

public class TitleState extends GameState {
    private GameState nextState;

    public TitleState() {
        new ButtonEntity(
                this,
                "start game",
                new Color(10, 10, 10, 100),
                null,
                Styles.titleText
        )
                .withBoundingBox(
                        b -> {
                            b.setAbsolutePosition(FinalLocation.START_BUTTON.getLocation());
                            b.setRenderOrder(99);
                        }
                )
                .addComponents(
                        new UIRendererRootComponent()
                )
                .cast(ButtonEntity.class)
                .addClickListener((entity, me) -> {
                    nextState = new DealCardsState(new Game());
                });

        new ButtonEntity(
                this,
                "how to play",
                new Color(10, 10, 10, 100),
                null,
                Styles.titleText
        ).addClickListener(
                        (entity, me) -> {
                            nextState = new RulesState(this, () -> nextState = null);
                        }
                )
                .withBoundingBox(
                        b -> {
                            b.setAbsolutePosition(FinalLocation.TITLE_RULES_BUTTON.getLocation());
                            b.setRenderOrder(98);
                        }
                )
                .addComponents(
                        new UIRendererRootComponent()
                );

        new UIEntity(
                this
        ).withBoundingBox(
                        b -> {
                            b.setAbsolutePosition(new Vec2(0, 0));
                            b.setSize(1920, 1080);
                            b.setRenderOrder(-100);
                        }
                )
                .addComponents(
                        new ImageRendererComponent("BG images/Start Screen.jpg"),
                        new UIRendererRootComponent()
                )
                .cast();
    }

    protected Iterator<? extends GameState> getStatesAfter() {
        return iteratorOver(nextState);
    }

    protected void onUpdate() {
    }

    protected void onSchedule() {
    }

    protected void onExecutionStart() {
    }

    protected void onExecutionEnd() {
    }

    protected boolean isFinished() {
        return nextState != null;
    }
}
