package app;

import app.states.ExampleState;
import lib.StateMachine;
import lib.engine.GameWindow;
import lib.states.AbstractGameState;

import java.util.Iterator;

public class App extends GameWindow {

    public App() {
        super("Splendor");
    }


    public void run() {
        StateMachine.run(new ExampleState(), canvas);
    }
}
