package app;

import app.helpers.*;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Cards {
    public static Map<Integer, Price> cardPrices = new HashMap<>();
    public static Map<Integer, SingleValue> cardColors = new HashMap<>();
    public static Map<Integer, Integer> cardPoints = new HashMap<>();
    static {
        FileReader priceReader, colorReader, pointsReader;
        try {
            priceReader = new FileReader("res/cards/card_prices.txt");
            Scanner sc = new Scanner(priceReader);
            while (sc.hasNextInt()) {
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
            colorReader = new FileReader("res/cards/card_colors.txt");
            Scanner sc2 = new Scanner(colorReader);
            while (sc2.hasNextInt()) {
                int start = sc2.nextInt();
                int end = sc2.nextInt();
                char color = sc.next().charAt(0);
                sc.nextLine();
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
            pointsReader = new FileReader("res/cards/card_points.txt");
            Scanner sc3 = new Scanner(pointsReader);
            while (sc3.hasNextInt()) {
                int id = sc3.nextInt();
                int points = sc3.nextInt();
                cardPoints.put(id, points);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
