package lib.behaviors;

import lib.GameCanvas;
import lib.UIBehavior;
import lib.math.Vec2;
import lib.util.ImageLoader;

import java.awt.*;

/**
 * Allows the gameObject to draw an image
 */
@SuppressWarnings("unused")
public class ImageRendererBehavior extends UIBehavior {
    protected Image image;

    public ImageRendererBehavior(Image image) {
        this.image = image;
    }

    public ImageRendererBehavior(String image) {
        this(ImageLoader.get(image));
    }

    public void setImage(Image image){
        this.image = image;
    }

    @Override
    public void draw(GameCanvas canvas) {
        if(image != null)
            canvas.drawImage(image, gameObject.getAbsoluteTopLeft(), gameObject.size());
    }

    public Vec2 getImageSize() {
        return new Vec2(image.getWidth(null), image.getHeight(null));
    }

    public Image getImage() {
        return image;
    }

    public double getAspectRatio() {
        return 1.0 * image.getHeight(null) / image.getWidth(null);
    }
}