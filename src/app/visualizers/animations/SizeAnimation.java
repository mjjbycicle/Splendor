package app.visualizers.animations;

import jGameLib.animation.Animation;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.util.math.Vec2;
import jGameLib.core.Entity;

public class SizeAnimation extends Animation<Entity> {
    private final Vec2 prevSize, newSize;

    public SizeAnimation(int totalAnimationFrames, Vec2 prevSize, Vec2 newSize) {
        super(totalAnimationFrames);
        this.prevSize = prevSize;
        this.newSize = newSize;
    }

    @Override
    public void onAnimationStart(Entity t) {
        t.cast(UIEntity.class).withBoundingBox(
                b -> {
                    b.setSize(prevSize);
                }
        );
    }

    @Override
    public void updateAnimation(Entity t, double v) {
        t.cast(UIEntity.class).withBoundingBox(
                b -> b.setSize(
                        prevSize.plus(newSize.minus(prevSize).times(v))
                )
        );
    }

    @Override
    public void onAnimationEnd(Entity t) {
        t.cast(UIEntity.class).withBoundingBox(
                b -> {
                    b.setSize(newSize);
                }
        );
    }
}
