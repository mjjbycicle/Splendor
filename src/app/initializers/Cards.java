package app.initializers;

import app.constants.Color;
import app.helpers.*;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Cards {
    public static Map<Integer, Price> cardPrices = new HashMap<>();
    public static Map<Integer, SingleValue> cardColors = new HashMap<>();
    public static Map<Integer, Integer> cardPoints = new HashMap<>();
    public static Map<Integer, Integer> tiers = new HashMap<>();
    static {
        FileReader priceReader, colorReader, pointsReader;
        try {
            Scanner sc = new Scanner(new File("res/cards/card_prices.txt"));
            for (int i = 0; i < 90; i++) {
                int id = sc.nextInt();
                int white = sc.nextInt();
                int blue = sc.nextInt();
                int green = sc.nextInt();
                int red = sc.nextInt();
                int black = sc.nextInt();
                cardPrices.put(id, new Price()
                        .set(Color.WHITE, white)
                        .set(Color.BLUE, blue)
                        .set(Color.GREEN, green)
                        .set(Color.RED, red)
                        .set(Color.BLACK, black));
            }
            sc.nextLine();
            for (int j = 0; j < 15; j++) {
                String line = sc.nextLine();
                Scanner inp = new Scanner(line);
                int start = inp.nextInt();
                int end = inp.nextInt();
                char color = inp.next().charAt(0);
                for (int i = start; i <= end; i++) {
                    cardColors.put(i, new SingleValue(
                                    switch (color) {
                                        case 'k' -> Color.BLACK;
                                        case 'u' -> Color.BLUE;
                                        case 'w' -> Color.WHITE;
                                        case 'g' -> Color.GREEN;
                                        case 'r' -> Color.RED;
                                        default -> Color.ANY;
                                    }, 1
                            ));
                }
            }
            while (sc.hasNextInt()) {
                int id = sc.nextInt();
                int points = sc.nextInt();
                cardPoints.put(id, points);
            }
            cardPrices.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < 40; i++) {
            tiers.put(i, 1);
        }
        for (int i = 40; i < 70; i++) {
            tiers.put(i, 2);
        }
        for (int i = 70; i < 90; i++) {
            tiers.put(i, 3);
        }
    }
}
