package app.states.turn;

import app.constants.Color;
import app.constants.FinalLocation;
import app.constants.Styles;
import app.game.Game;
import app.objects.Card;
import app.states.game.RulesState;
import app.states.test.TestGameState;
import jGameLib.core.GameState;
import jGameLib.ui2d.rendering.UIRendererRootComponent;
import jGameLib.ui2d.utils.ButtonEntity;

import java.util.Arrays;
import java.util.Iterator;

public class TurnStartedState extends GameState {
    private final Game game;
    private final Game prevGame;
    private GameState nextState;
    private int framesAfterNext = 0;

    public TurnStartedState(Game game) {
        this.game = game;
        prevGame = game.createClone();
        game.visualizer.cancelGrayCards();
        game.visualizer.cancelGrayStacks();
        game.visualizer.usePlayerGrayCards(game.getActivePlayer());
        game.visualizer.useGrayStacks(
                Arrays.asList(
                        false, false, false, false, false, true
                )
        );
        game.addGame(this);new ButtonEntity(
                this,
                "",
                null,
                null,
                Styles.titleText
        ).addClickListener(
                        (entity, me) -> {
                            nextState = new RulesState(this, () -> nextState = null);
                        }
                )
                .withBoundingBox(
                        b -> {
                            b.setAbsolutePosition(FinalLocation.BACK_BUTTON.getLocation()).setRenderOrder(98);
                            b.setSize(250, 65);
                        }
                )
                .addComponents(
                        new UIRendererRootComponent()
                );
    }

    @Override
    public void onUpdate() {
        if (nextState == null) {
            Card clickedCard = game.visualizer.getCardClicked();
            if (clickedCard != null) {
                if (game.getActivePlayer().hand.canBuyCard(clickedCard)) {
                    game.addChips(game.getActivePlayer().hand.buyCard(clickedCard));
                    game.replaceCard(game.getClickedIndex());
                } else if (game.getActivePlayer().hand.canReserve()) {
                    game.takeAnyChip();
                    game.getActivePlayer().hand.addReservedCard(clickedCard);
                    game.replaceCard(game.getClickedIndex());
                }
                nextState = new TurnFinishedState(game, prevGame);
            }
            Color clicked = game.getClickedChipStack();
            if (clicked != null) {
                if (clicked != Color.ANY) {
                    game.takeChip(clicked);
                    nextState = new TurnOneChipTaken(game, prevGame, clicked);
                }
            }
            Card clickedReservedCard = game.getActivePlayer().visualizer.getClickedReservedCard();
            if (clickedReservedCard != null) {
                if (game.getActivePlayer().hand.canBuyCard(clickedReservedCard)) {
                    game.addChips(game.getActivePlayer().hand.buyCard(clickedReservedCard));
                    nextState = new TurnFinishedState(game, prevGame);
                }
            }
        } else {
            framesAfterNext++;
        }
    }

    @Override
    public boolean isFinished() {
        return nextState != null && framesAfterNext >= 10;
    }

    @Override
    public Iterator<? extends GameState> getStatesAfter() {
        return iteratorOver(nextState);
    }
}
