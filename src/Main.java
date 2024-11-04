import app.helpers.FinalLocation;
import app.helpers.Location;
import app.states.TestState;
import app.states.TitleState;
import jGameLib.core.StateMachine;
import jGameLib.ui2d.GameWindow;
import jGameLib.ui2d.input.UserInputSystem;
import jGameLib.ui2d.rendering.UIRendererSystem;

public class Main {
    public static void main(String[] args) {

        GameWindow.enableHardwareAcceleration();
        var a = new GameWindow("Splendor");

        StateMachine.globalInstance.addSystem(new UserInputSystem(a.canvas));
        StateMachine.globalInstance.addSystem(new UIRendererSystem(a.canvas));

//        StateMachine.globalInstance.run(new TestState(Location.getLocation(FinalLocation.TITLE_RULES_BUTTON), "how to play"));
        StateMachine.globalInstance.run(new TitleState());
    }
}