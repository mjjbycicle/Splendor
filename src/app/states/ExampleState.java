package app.states;

import lib.GameCanvas;
import lib.behaviors.TextStyle;
import lib.gameobjects.ButtonObject;
import lib.gameobjects.TextObject;
import lib.input.MouseEvent;
import lib.states.AbstractGameState;
import lib.util.FontLoader;

import java.awt.*;
import java.util.Iterator;

public class ExampleState extends AbstractGameState {
    // instantiate all objects
    // text objects, image objects, etc

    // also instantiate next state but don't define it
    private AbstractGameState nextState;

    //since there are many parameters, separate each into its own line
    private final TextObject exampleTextObject = (TextObject) new TextObject(
            "example text", // text to be shown
            FontLoader.load("font/TitilliumWeb-ExtraLight.ttf").deriveFont(40f), //font type and font size
            Color.BLACK, // text color
            TextStyle.TextAlign.ALIGN_CENTER // could also be aligned bottom, aligned left, aligned right, etc.

    ).setPosition(0, 0); // 0, 0 is center; negative is left or up, positive is right or down

    private final ButtonObject exampleButtonObject = (ButtonObject) new ButtonObject(
            "example button",
            FontLoader.load("font/TitilliumWeb-ExtraLight.ttf").deriveFont(40f),
            Color.WHITE, // bg color
            Color.CYAN, // border color
            Color.BLACK, // text color
            true // whether to underline or not when being hovered over
    ).setPosition(0, 100);

    /**
     * constructor for the state
     * you can have it take some parameters,
     * such as a reference to a game object that is passed between several states
     */
    public ExampleState() {

    }

    /**
     * this function is run every game loop to update the visual state of the game window
     * @param canvas The {@link GameCanvas} on which to draw things
     */
    @Override
    public void draw(GameCanvas canvas) {
        exampleTextObject.updateAndDraw(canvas); // objects have builtin functions to draw on canvasses
    }

    /**
     * this function is run whenever the cursor is clicked anywhere in the game window
     * @param me mouse event for the mouse click, you generally won't have to use it
     */
    @Override
    public void onMouseClick(MouseEvent me) {
        if (exampleButtonObject.isHovered()) {
            // do stuff
            // for example set the next state
            nextState = new ExampleState(); // make it another state progress to a different state
        }
    }

    /**
     * this function called during every game loop to check whether the state is finished
     */
    @Override
    public boolean isFinished() {
        return nextState != null;
    }

    /**
     * this function called when {@link #isFinished()} returns true
     * you can chain together as many states as you wish
     * when doing so, separate multiple states with commas
     */
    public Iterator<? extends AbstractGameState> getStatesAfter() {
        return makeIterator(nextState);
    }
}
