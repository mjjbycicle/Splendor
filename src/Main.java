import app.Cards;
import app.game.Hand;
import app.helpers.Color;
import app.helpers.Price;
import app.objects.Card;
import app.states.TitleState;
import jGameLib.core.StateMachine;
import jGameLib.ui2d.GameWindow;
import jGameLib.ui2d.input.UserInputSystem;
import jGameLib.ui2d.rendering.UIRendererSystem;

public class Main {
    public static void main(String[] args) {
//        GameWindow.enableHardwareAcceleration();
//        var a = new GameWindow("Splendor");
//
//        StateMachine.globalInstance.addSystem(new UserInputSystem(a.canvas));
//        StateMachine.globalInstance.addSystem(new UIRendererSystem(a.canvas));
//        StateMachine.globalInstance.run(new TitleState());

        Cards.initCards();
        Hand hand = new Hand();
        hand.addChips(
                new Price()
                        .set(Color.RED, 3)
                        .set(Color.ANY, 2)
        );
        Card card = new Card(56);
        System.out.println(hand.canBuyCard(card));
    }
}