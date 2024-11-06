package app.states;

import app.Styles;
import jGameLib.core.GameState;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.rendering.UIRendererRootComponent;
import jGameLib.ui2d.utils.TextRendererComponent;
import jGameLib.util.math.Vec2;

public class TestState extends GameState {
    public TestState(Vec2 location, String text) {
        new UIEntity(
                this
        ).withBoundingBox(
                b -> {
                    b.setAbsolutePosition(location);
                    b.setSize(100, 20);
                }
        ).addComponents(
                new UIRendererRootComponent(),
                new TextRendererComponent(
                        text,
                        Styles.titleText
                )
        ).cast();
    }
}
