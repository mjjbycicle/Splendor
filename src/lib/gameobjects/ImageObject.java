package lib.gameobjects;

import lib.GameObject;
import lib.behaviors.ImageRendererBehavior;
import lib.util.ImageLoader;

import java.awt.*;

public class ImageObject extends GameObject {
	public ImageObject(String file) {
		super(new ImageRendererBehavior(ImageLoader.get(file)));
	}

	public ImageObject(Image image) {
		super(new ImageRendererBehavior(image));
	}

	public Image getImage() {
		return findBehavior(ImageRendererBehavior.class).getImage();
	}
}
