package app.states.game;

import app.constants.Styles;
import app.constants.FinalLocation;
import jGameLib.core.GameState;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.rendering.UIRendererRootComponent;
import jGameLib.ui2d.utils.ButtonEntity;
import jGameLib.ui2d.utils.ImageRendererComponent;
import jGameLib.ui2d.utils.TextRendererComponent;

import java.util.Iterator;

public class EndScreenState extends GameState {
    private GameState nextState;

    public EndScreenState(int winner) {
        new UIEntity(this)
                .withBoundingBox(
                        b -> {
                            b.setSize(1920, 1080);
                            b.setAbsolutePosition(FinalLocation.ORIGIN.getLocation());
                        }
                )
                .addComponents(
                        new UIRendererRootComponent(),
                        new ImageRendererComponent("BG images/End Screen.png")
                );
        new UIEntity(this)
                .withBoundingBox(
                        b -> {
                            b.setAbsolutePosition(FinalLocation.WINNER_TEXT.getLocation());
                        }
                )
                .addComponents(
                        new TextRendererComponent(
                                "" + winner,
                                Styles.winnerText
                        ),
                        new UIRendererRootComponent()
                );
        new ButtonEntity(
                this,
                "",
                null,
                null,
                Styles.titleText)
                .addClickListener(
                        (entity, me) -> {
                            nextState = new TitleState();
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
