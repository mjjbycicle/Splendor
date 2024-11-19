import app.constants.Color;
import app.game.Game;
import app.helpers.Price;
import app.states.game_start.DealCardsState;
import app.states.test.TestState;
import jGameLib.core.StateMachine;
import jGameLib.ui2d.GameWindow;
import jGameLib.ui2d.input.UserInputSystem;
import jGameLib.ui2d.rendering.UIRendererSystem;

public class Main {
    public static void main(String[] args) {
        GameWindow.enableHardwareAcceleration();
        var a = new GameWindow("Splendor");

        Game game = new Game();
        game.getActivePlayer().hand.addChips(
                new Price()
                        .set(Color.BLUE, 2)
                        .set(Color.RED, 2)
                        .set(Color.GREEN, 2)
                        .set(Color.BLACK, 2)
                        .set(Color.WHITE, 2)
        );
        StateMachine.globalInstance.addSystem(new UserInputSystem(a.canvas));
        StateMachine.globalInstance.addSystem(new UIRendererSystem(a.canvas));
        StateMachine.globalInstance.run(new DealCardsState(game));
    }
}