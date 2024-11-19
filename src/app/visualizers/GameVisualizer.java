package app.visualizers;

import app.constants.Color;
import app.constants.ObjectLocations;
import app.game.Player;
import app.objects.Card;
import app.objects.CardDeck;
import app.objects.ChipStack;
import app.objects.Noble;
import app.visualizers.entities.AnimatedCardMatrixEntity;
import jGameLib.animation.Animation;
import jGameLib.core.Entity;
import jGameLib.core.GameState;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.rendering.UIRendererRootComponent;
import jGameLib.ui2d.utils.ImageRendererComponent;
import jGameLib.util.Pair;
import jGameLib.util.math.Vec2;

import java.util.ArrayList;
import java.util.List;

public class GameVisualizer {
    private final List<Player> players;
    private final List<Noble> nobles;
    private final List<List<Card>> cardMatrix;
    private final GameCardMatrixVisualizer cards;
    private final GameChipStacksVisualizer chips;
    private final List<List<Boolean>> grayCards;

    public GameVisualizer(List<Player> players, List<CardDeck> decks, List<ChipStack> chips, List<Noble> nobles) {
        this.players = players;
        this.nobles = nobles;
        grayCards = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            grayCards.add(new ArrayList<>());
            for (int j = 0; j < 4; j++) {
                grayCards.get(i).add(false);
            }
        }
        cards = new GameCardMatrixVisualizer(decks, grayCards);
        cardMatrix = cards.getCardMatrix();
        this.chips = new GameChipStacksVisualizer(chips);
    }

    public Card getCardClicked() {
        return cards.getCardClicked();
    }

    public Color getChipStackClicked() {
        return chips.getClickedChipStack();
    }

    public void addAllEntities(GameState state) {
        cards.addCards(state);
        chips.addChipStacks(state);
        addNobles(state);
        for (Player player : players) {
            player.addPlayer(state);
        }
        new UIEntity(state)
                .withBoundingBox(
                        b -> {
                            b.setAbsolutePosition(new Vec2(0, 0));
                            b.setSize(1920, 1080);
                            b.setRenderOrder(-100);
                        }
                ).addComponents(
                        new UIRendererRootComponent(),
                        new ImageRendererComponent("BG images/Gameplay BG.png")
                );
    }

    public AnimatedCardMatrixEntity addAnimationEntities(GameState state) {
        chips.addChipStacks(state);
        addNobles(state);
        for (Player player : players) {
            player.addPlayer(state);
        }
        new UIEntity(state)
                .withBoundingBox(
                        b -> {
                            b.setAbsolutePosition(new Vec2(0, 0));
                            b.setSize(1920, 1080);
                            b.setRenderOrder(-100);
                        }
                ).addComponents(
                        new UIRendererRootComponent(),
                        new ImageRendererComponent("BG images/Gameplay BG.png")
                );
        return cards.addAnimatedCards(state);
    }

    private void addNobles(GameState state) {
        for (int i = 0; i < nobles.size(); i++) {
            int finalI = i;
            new UIEntity(state)
                    .withBoundingBox(
                            b -> {
                                b.setSize(100, 100);
                                try {
                                    b.setAbsolutePosition(
                                            ObjectLocations.GAME_NOBLES.getNobleLocation(0, finalI)
                                    );
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                    )
                    .addComponents(
                            new UIRendererRootComponent(),
                            nobles.get(i).getImage()
                    ).cast();
        }
    }

    public void usePlayerGrayCards(Player player) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                boolean canBuyCard =
                        player.hand.canBuyCard(cardMatrix.get(i).get(j));
                grayCards.get(i).set(
                        j, !canBuyCard
                );
            }
        }
    }

    public void cancelGrayCards() {
        for (var i : grayCards) {
            for (int j = 0; j < 4; j++) {
                i.set(j, false);
            }
        }
    }

    public void replaceCard(Pair<Integer, Integer> index, Card newCard) {
        cardMatrix.get(index.a()).set(index.b(), newCard);
    }

    public Pair<Integer, Integer> getClickedIndex() {
        return cards.getIndexClicked();
    }
}
