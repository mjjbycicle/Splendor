package app.objects;

import app.Nobles;
import app.helpers.Price;

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
}
