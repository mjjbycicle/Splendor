package app.objects;

import app.Nobles;
import app.helpers.Price;
import app.visualizers.NobleVisualizer;
import jGameLib.ui2d.utils.ImageRendererComponent;

public class Noble {
    private final Price price;
    private final int id;

    public Noble(int id) {
        this.id = id;
        price = Nobles.noble_prices.get(id);
    }

    public Price getPrice() {
        return price;
    }

    public ImageRendererComponent getImage() {
        return NobleVisualizer.getImage(id);
    }
}
