package app.objects;

import lib.GameCanvas;
import lib.behaviors.ButtonBehavior;
import lib.behaviors.ImageRendererBehavior;
import lib.behaviors.PositionAnimationBehavior;
import lib.gameobjects.ImageObject;
import lib.math.Vec2;
import lib.util.ImageLoader;
import lib.gameobjects.*;

/**
 * It is an {@link ImageObject}
 * with some extra functionality
 * such as {@link ButtonBehavior}
 * to detect clicks
 * and {@link PositionAnimationBehavior}
 * to be able to animate
 */
public class Card extends ImageObject {
    public final int num;
    private boolean faceDown;

    /**
     * @param num      number on the card
     * @param faceDown whether the card is face down
     */
    public Card(int num, boolean faceDown) {
        super(num + ".png");
        this.num = num;
        this.faceDown = faceDown;
        this.addBehaviors(
                new ButtonBehavior(), // make it able to be clicked
                new PositionAnimationBehavior() // make it able to move
        );
        // find the correct image based on faceDown
        if (!faceDown) this.findBehavior(ImageRendererBehavior.class).setImage(ImageLoader.get(num + ".png"));
        else this.findBehavior(ImageRendererBehavior.class).setImage(ImageLoader.get("back.png"));
    }

    /**
     * @param canvas the {@link GameCanvas} to draw on
     * @param i      which row the card is in
     * @param j      which column the card is in
     * @param startX the x value for the top left card in the matrix
     * @param startY the y value for the top left card in the matrix
     */
    public void updateAndDrawActive(GameCanvas canvas, int i, int j, int startX, int startY) {
        this.setSize(100, 160);
        this.setPosition(startX + j * 110, startY + i * 170);
        this.updateAndDraw(canvas);
    }

    /**
     * @param canvas the {@link GameCanvas} to draw on
     * @param i      which row the card is in
     * @param j      which column the card is in
     * @param startX the x value for the top left card in the matrix
     * @param startY the y value for the top left card in the matrix
     */
    public void updateAndDrawInactive(GameCanvas canvas, int i, int j, int startX, int startY) {
        this.setSize(35, 56);
        this.setPosition(startX + j * 45, startY + i * 60);
        this.updateAndDraw(canvas);
    }


    /**
     * @param canvas the {@link GameCanvas} to draw on
     * @param x      the x position of the deck
     * @param y      the y position of the deck
     */
    public void updateAndDrawDeck(GameCanvas canvas, int x, int y) {
        this.findBehavior(PositionAnimationBehavior.class).update();
        this.setSize(100, 160);
        this.setPosition(x, y);
        this.updateAndDraw(canvas);
    }


    /**
     * @return whether the card is being hovered on
     */
    public boolean clicked() {
        return this.findBehavior(ButtonBehavior.class).isHovered();
    }

    /**
     * @param faceDown what to set {@link Card#faceDown} to
     */
    public void setFaceDown(boolean faceDown) {
        this.faceDown = faceDown;
        if (!faceDown) this.findBehavior(ImageRendererBehavior.class).setImage(ImageLoader.get(num + ".png"));
        else this.findBehavior(ImageRendererBehavior.class).setImage(ImageLoader.get("back.png"));
    }

    /**
     * @return {@link Card#num}
     */
    public int getNum() {
        return num;
    }

    /**
     * @return {@link Card#faceDown}
     */
    public boolean isFaceDown() {
        return faceDown;
    }

    /**
     * @param newX new x position
     * @param newY new y position
     */
    public void moveTo(int newX, int newY) {
        this.findBehavior(PositionAnimationBehavior.class).moveTo(new Vec2(newX, newY), 15);
    }

    /**
     * @param newPosition new {@link Vec2} position
     */
    public void moveTo(Vec2 newPosition) {
        this.findBehavior(PositionAnimationBehavior.class).moveTo(newPosition, 30);
    }

    /**
     * @return {@link Vec2} current position
     */
    public Vec2 getPositionVec2() {
        return new Vec2(this.getPosition().x, this.getPosition().y);
    }

    /**
     * @return whether the card is finished moving
     */
    public boolean finishedMoving() {
        return this.findBehavior(PositionAnimationBehavior.class).finishedMoving();
    }
}