package app.visualizers;

import jGameLib.ui2d.utils.ImageRendererComponent;

public class NobleVisualizer {
    public static ImageRendererComponent getImage(int id) {
        return new ImageRendererComponent(
                "nobles/noble face pics/PATRON_" + id + ".png"
        );
    }
}
