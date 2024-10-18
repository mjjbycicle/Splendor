package app;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Cards {
    public static Map<int, Value> cardPrices = new HashMap<>();
    static {
        FileReader reader;
        try {
            reader = new FileReader("res/cards/card_prices.txt");
            Scanner sc = new Scanner(reader);
            while (sc.hasNextInt()) {
                int id = sc.nextInt();
                int white = sc.nextInt();
                int blue = sc.nextInt();
                int green = sc.nextInt();
                int red = sc.nextInt();
                int black = sc.nextInt();
                cardPrices.put(new Value()
                        .with(Color.WHITE, white)
                        .with(Color.BLUE, blue)
                        .with(Color.GREEN, green)
                        .with(Color.RED, red)
                        .with(Color.BLACK, black));

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
