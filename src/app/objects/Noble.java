package app.objects;

import app.Nobles;
import app.helpers.Price;

public class Noble {
    private final int id;
    private final Price price;

    public Noble(int id) {
        this.id = id;
        this.price = Nobles.noble_prices.get(id);
    }

    public Price getPrice() {
        return price;
    }
}
