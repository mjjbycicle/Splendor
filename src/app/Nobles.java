package app;

import app.helpers.Color;
import app.helpers.Price;
import app.helpers.SingleValue;

import java.io.FileReader;
import java.util.Map;
import java.util.Scanner;

public class Nobles {
    public static Map<Integer, Price> noble_prices;
    static {
        FileReader priceReader;
        try {
            priceReader = new FileReader("res/cards/card_prices.txt");
            Scanner sc = new Scanner(priceReader);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Scanner in = new Scanner(line);
                int id = in.nextInt();
                noble_prices.put(id, new Price());
                while (in.hasNext()) {
                    int n = in.nextInt();
                    char c = in.next().charAt(0);
                    noble_prices.get(id).set(
                            switch (c) {
                                case 'w' -> Color.WHITE;
                                case 'u' -> Color.BLUE;
                                case 'b' -> Color.BLACK;
                                case 'r' -> Color.RED;
                                case 'g' -> Color.GREEN;
                                default -> Color.ANY;
                            },
                            n
                    );
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
