package app.game;

import app.helpers.Color;
import app.helpers.Price;
import app.helpers.SingleValue;
import app.objects.Card;
import app.objects.CardStack;
import app.objects.ChipStack;
import app.objects.Patron;

import java.util.*;

public class Player {
    private final int id;
    private final Hand hand;
    private final List<Patron> patrons;

    public Player(int id) {
        this.id = id;
        hand = new Hand();
        patrons = new ArrayList<>();
    }


}
