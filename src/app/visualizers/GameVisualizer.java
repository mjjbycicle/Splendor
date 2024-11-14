package app.visualizers;

import app.constants.ObjectLocations;
import app.game.Player;
import app.objects.CardDeck;
import app.objects.ChipStack;
import app.objects.Noble;
import jGameLib.animation.Animation;
import jGameLib.core.Entity;
import jGameLib.core.GameState;
import jGameLib.ui2d.rendering.UIEntity;
import jGameLib.ui2d.rendering.UIRendererRootComponent;
import jGameLib.ui2d.utils.ImageRendererComponent;
import jGameLib.util.Pair;
import jGameLib.util.math.Vec2;

import java.util.List;

public class GameVisualizer {
    private final List<Player> players;
    private final List<Noble> nobles;
    private final GameCardMatrixVisualizer cards;
    private final GameChipStacksVisualizer chips;

    public GameVisualizer(List<Player> players, List<CardDeck> decks, List<ChipStack> chips, List<Noble> nobles) {
        this.players = players;
        this.nobles = nobles;

        cards = new GameCardMatrixVisualizer(decks);
        this.chips = new GameChipStacksVisualizer(chips);
    }

    public Pair<Integer, Integer> getCardClicked() {
        return cards.getCardClicked();
    }

    public int getChipStackClicked() {
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
}
