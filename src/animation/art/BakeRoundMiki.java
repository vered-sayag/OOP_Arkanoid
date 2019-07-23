package animation.art;

import biuoop.DrawSurface;
import game.Sprite;
import geometry.objacts.Ball;
import geometry.objacts.Block;
import geometry.primitives.Point;

import java.awt.Color;

/**
 * The type Bake round miki.
 */
public class BakeRoundMiki implements Sprite {

    /**
     * draw the sprite to the screen.
     *
     * @param d given draw surface.
     */
    public void drawOn(DrawSurface d) {
        new Block(new Point(0, 0), d.getWidth(), d.getHeight(), Color.red).drawOn(d);

        for (int i = 3; i < d.getWidth() + 20; i = i + 15) {
            for (int j = 0; j < d.getHeight() + 20; j = j + 15) {
                new Ball(i, j, 1, Color.white).drawOn(d);
            }

        }
        d.setColor(Color.black);
        d.fillRectangle(200, 450, 50, 200);
        new Ball(225, 480, 100, Color.BLACK).drawOn(d);
        new Ball(125, 410, 60, Color.BLACK).drawOn(d);
        new Ball(325, 410, 60, Color.BLACK).drawOn(d);


    }

    /**
     * notify the sprite that time has passed.
     * @param  dt  the dt
     */
    public void timePassed(double dt) {

    }
}
