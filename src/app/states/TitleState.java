package app.states;

import app.Styles;
import app.helpers.FinalLocation;
import app.helpers.Location;
import jGameLib.core.GameState;
import jGameLib.ui2d.input.MouseEvent;
import jGameLib.ui2d.input.UserInputHandlerComponent;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.rendering.UIRendererRootComponent;
import jGameLib.ui2d.utils.*;
import jGameLib.util.math.Vec2;

import java.awt.*;
import java.util.Iterator;

public class TitleState extends GameState {
    // instantiate all objects
    // text objects, image objects, etc

    // also instantiate the next state but don't define it
    private GameState nextState;

    /**
     * constructor for the state
     * you can have it take some parameters,
     * such as a reference to a game object that is passed between several states
     */
    public TitleState() {
        new ButtonEntity(
                this,
                "start screen",
                new Color(10, 10, 10, 100),
                null,
                Styles.titleText
        )
                .withBoundingBox(
                        b -> {
                            b.setAbsolutePosition(Location.getLocation(FinalLocation.START_BUTTON));
                            b.setRenderOrder(99);
                            b.setSize(500, 65);
                        }
                )
                .addComponents(
                        new UIRendererRootComponent()
                )
                .cast(ButtonEntity.class)
                .addClickListener((entity, me) -> {
                    nextState = new TitleState();
                });

        new ButtonEntity(
                this,
                "how to play",
                new Color(10, 10, 10, 100),
                null,
                Styles.titleText
        ).addClickListener(
                        (entity, me) -> {
                            System.out.println("clicked");
                            nextState = new RulesState();
                        }
                )
                .withBoundingBox(
                        b -> {
                            b.setAbsolutePosition(Location.getLocation(FinalLocation.TITLE_RULES_BUTTON)).setRenderOrder(98);
                            b.setSize(450, 65);
                        }
                )
                .addComponents(
                        new UIRendererRootComponent()
                );

        new UIEntity(
                this
        ).withBoundingBox(
                        b -> b.setAbsolutePosition(new Vec2(0, 0)).setSize(1920, 1080).setRenderOrder(-100)
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
