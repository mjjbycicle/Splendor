package app.states;

import app.Styles;
import jGameLib.core.GameState;
import jGameLib.ui2d.input.MouseEvent;
import jGameLib.ui2d.input.UserInputHandlerComponent;
import jGameLib.ui2d.input.UserInputSystem;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.rendering.UIRendererRootComponent;
import jGameLib.ui2d.utils.*;
import jGameLib.util.files.FontLoader;
import jGameLib.util.math.Vec2;

import java.awt.*;
import java.util.Iterator;

public class ExampleState extends GameState {
    // instantiate all objects
    // text objects, image objects, etc

    // also instantiate the next state but don't define it
    private GameState nextState;

    /**
     * constructor for the state
     * you can have it take some parameters,
     * such as a reference to a game object that is passed between several states
     */
    public ExampleState() {
        new ButtonEntity(
                this,
                "start screen",
                null,
                null,
                Styles.titleText
        )
                .withBoundingBox(b -> b.setAbsolutePosition(new Vec2(0, 0)).setRenderOrder(99))
                .addComponents(
                        new UIRendererRootComponent()
                )
                .cast(ButtonEntity.class)
                .addClickListener((entity, me) -> {
                    nextState = new ExampleState();
                });

        new ButtonEntity(
                this,
                "how to play",
                null,
                null,
                Styles.titleText
        )
                .withBoundingBox(b -> b.setAbsolutePosition(new Vec2(0, 100)).setRenderOrder(98))
                .addComponent(
                        new UIRendererRootComponent()
                )
                .cast(ButtonEntity.class)
                .addClickListener((event, me) -> {
                    nextState = new ExampleState();
                });

        new UIEntity(
                this
        ).withBoundingBox(
                        b -> b.setAbsolutePosition(new Vec2(0, 0)).setSize(1920, 1080).setRenderOrder(-100)
                )
                .addComponents(
                        new ImageRendererComponent("BG images/Start Screen.jpg")
                        ,
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
        return false;
    }
}
