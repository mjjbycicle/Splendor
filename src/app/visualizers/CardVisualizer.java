package app.visualizers;

import jGameLib.ui2d.utils.ImageRendererComponent;

import java.util.function.BooleanSupplier;

public class CardVisualizer {
    private final ImageRendererComponent faceUpImage, faceDownImage;
    private final BooleanSupplier faceDown;

    public CardVisualizer(int id, int tier, BooleanSupplier faceDown) {
        faceUpImage = new ImageRendererComponent(
                "cards/card face pics/CARD_" + id + ".png"
        );
        faceDownImage = new ImageRendererComponent(
                "cards/face down pics/TIER_" + tier + ".png"
        );
        this.faceDown = faceDown;
    }

    public ImageRendererComponent getImage() {
        if (faceDown.getAsBoolean()) {
            return faceDownImage;
        }
        return faceUpImage;
    }
}
