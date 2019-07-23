package geometry.objacts;

import java.awt.Color;

import biuoop.DrawSurface;
import geometry.primitives.Velocity;
import geometry.primitives.Rectangle;
import geometry.primitives.Point;
import game.Sprite;
import game.Collidable;
import game.GameLevel;

/**
 * Paddle control by the user.
 *
 * @author Vered Sayag
 * @version 15.04.2018
 */
public class Paddle implements Sprite, Collidable {

    private biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private Frame frame;
    private int velocity;

    /**
     * constractor.
     *
     * @param upperLeft the point upp Left  of the paddle
     * @param width     the width of the paddle
     * @param height    the height of the paddle
     * @param color     the color of the paddle
     * @param frame     the frame the paddle live into
     * @param velocity  the velocity of the paddle
     */
    public Paddle(Point upperLeft, double width, double height, Color color, Frame frame, int velocity) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.rectangle.setColor(color);
        this.keyboard = frame.getGUI().getKeyboardSensor();
        this.frame = frame;
        this.velocity = velocity;
        this.rectangle.setStroke(Color.black);
    }

    /**
     * make the paddle move left.
     *
     * @param dt the dt
     */
    public void moveLeft(double dt) {

        this.rectangle.setUpperLeft(new Point(
                this.rectangle.getUpperLeft().getX() - velocity * dt, this.rectangle.getUpperLeft().getY()));

    }

    /**
     * make the paddle move right.
     *
     * @param dt the dt
     */
    public void moveRight(double dt) {

        this.rectangle.setUpperLeft(new Point(this.rectangle.getUpperLeft().getX()
                + velocity * dt, this.rectangle.getUpperLeft().getY()));

    }

    // Sprite

    /**
     * move the paddle.
     *
     * @param dt the dt
     */
    public void timePassed(double dt) {
        if (keyboard.isPressed("left") && this.rectangle.getUpperLeft().getX()
                >= this.frame.getRectangle().getUpperLeft().getX() + velocity * dt) {
            moveLeft(dt);
        }
        if (keyboard.isPressed("right") && this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth()
                <= this.frame.getRectangle().getUpperLeft().getX() + this.frame.getRectangle().getWidth()
                - velocity * dt) {
            moveRight(dt);
        }

    }

    /**
     * draw the paddle on the given DrawSurface.
     *
     * @param d given DrawSurface
     */
    public void drawOn(DrawSurface d) {
        this.rectangle.drwoRectangle(d);
    }


    /**
     * return the rectangle of this paddle.
     *
     * @return the rectangle of this paddle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * when a hit happened move the given velocity.
     *
     * @param hitter          the ball
     * @param collisionPoint  collision Point - data on the hit
     * @param currentVelocity current Velocity - the given velocity.
     * @return a new velocity after the hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint == null || currentVelocity == null) {
            return null;
        }

        if (collisionPoint.getX() == this.rectangle.getUpperLeft().getX()
                || collisionPoint.getX() == this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth()) {
            currentVelocity = new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }

        // chance dy
        if ((collisionPoint.getY() == this.rectangle.getUpperLeft().getY())) {
            currentVelocity = new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
        }

        // chance the angle of the spide according to  the hit point
        if (collisionPoint.getY() == this.rectangle.getUpperLeft().getY()) {
            if (collisionPoint.getX() >= this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() * 4 / 5) {
                currentVelocity = Velocity.fromAngleAndSpeed(120, currentVelocity.getSize());
            }
            if ((collisionPoint.getX() <= this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() / 5)) {
                currentVelocity = Velocity.fromAngleAndSpeed(240, currentVelocity.getSize());
            }
            if (collisionPoint.getX() > this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() * 3 / 5
                    && collisionPoint.getX() < this.rectangle.getUpperLeft().getX()
                    + this.rectangle.getWidth() * 4 / 5) {
                currentVelocity = Velocity.fromAngleAndSpeed(150, currentVelocity.getSize());
            }
            if ((collisionPoint.getX() > this.rectangle.getUpperLeft().getX() + this.rectangle.getWidth() / 5
                    && collisionPoint.getX() < this.rectangle.getUpperLeft().getX()
                    + this.rectangle.getWidth() * 2 / 5)) {
                currentVelocity = Velocity.fromAngleAndSpeed(210, currentVelocity.getSize());
            }
        }
        return currentVelocity;

    }


    /**
     * Add this paddle to the game.
     *
     * @param g given game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * set location.
     *
     * @param location point up left.
     */
    public void setLocation(Point location) {
        this.rectangle.setUpperLeft(location);
    }

}