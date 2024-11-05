package app.states;

import app.Styles;
import app.helpers.FinalLocation;
import app.helpers.Location;
import jGameLib.core.GameState;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.rendering.UIRendererRootComponent;
import jGameLib.ui2d.utils.ButtonEntity;
import jGameLib.ui2d.utils.ImageRendererComponent;

import java.awt.*;

public class RulesState extends GameState {
    private boolean finished = false;

    public RulesState() {
        new UIEntity(this)
                .withBoundingBox(
                        b -> {
                            b.setSize(1920, 1080);
                            b.setAbsolutePosition(Location.getLocation(FinalLocation.ORIGIN));
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
                Styles.titleText
        ).addClickListener(
                        (entity, me) -> {
                            finished = true;
                        }
                )
                .withBoundingBox(
                b -> {
                    b.setAbsolutePosition(Location.getLocation(FinalLocation.BACK_BUTTON));
                    b.setSize(500, 300);
                }
        );
    }

    public
}
