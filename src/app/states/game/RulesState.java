package app.states.game;

import app.constants.Styles;
import app.constants.FinalLocation;
import jGameLib.core.GameState;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.rendering.UIRendererRootComponent;
import jGameLib.ui2d.utils.ButtonEntity;
import jGameLib.ui2d.utils.ImageRendererComponent;

import java.util.Iterator;

public class RulesState extends GameState {
    private GameState nextState;

    public RulesState(GameState prev, Runnable run) {
        new UIEntity(this)
                .withBoundingBox(
                        b -> {
                            b.setSize(1920, 1080);
                            b.setAbsolutePosition(FinalLocation.ORIGIN.getLocation());
                        }
                )
                .addComponents(
                        new UIRendererRootComponent(),
                        new ImageRendererComponent("BG images/Rules Pic Download.png")
                );
        new ButtonEntity(
                this,
                "",
                null,
                null,
                Styles.titleText)
                .addClickListener(
                        (entity, me) -> {
                            run.run();
                            nextState = prev;
                        }
                )
                .withBoundingBox(
                        b -> {
                            b.setAbsolutePosition(FinalLocation.BACK_BUTTON.getLocation());
                            b.setSize(300, 200);
                            b.setRenderOrder(99);
                        }
                )
                .addComponent(
                        new UIRendererRootComponent()
                );
    }

    @Override
    public boolean isFinished() {
        return nextState != null;
    }

    @Override
    protected Iterator<? extends GameState> getStatesAfter() {
        return iteratorOver(nextState);
    }
}
