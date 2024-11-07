package app.visualizers;

import app.constants.Color;
import jGameLib.ui2d.utils.ImageRendererComponent;

public class ChipVisualizer {
    public static ImageRendererComponent getChip(Color color) {
        return new ImageRendererComponent(
                "chips/" + color.name() + "_CHIP.png"
        );
    }
}
