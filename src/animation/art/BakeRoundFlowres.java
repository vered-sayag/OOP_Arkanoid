package animation.art;

import biuoop.DrawSurface;
import game.Sprite;
import geometry.objacts.Ball;
import geometry.objacts.Block;
import geometry.primitives.Point;

import java.awt.Color;

/**
 * The type Bake round flowres.
 */
public class BakeRoundFlowres implements Sprite {

    private int i = 0;
    private int velocity = 1;

    /**
     * draw the sprite to the screen.
     *
     * @param d given draw surface.
     */
    public void drawOn(DrawSurface d) {
        new Block(new Point(0, 0), d.getWidth(), d.getHeight(), Color.cyan).drawOn(d);
        new Block(new Point(0, 595), d.getWidth(), d.getHeight(), Color.green).drawOn(d);
        d.setColor(Color.green);
        d.fillRectangle(100 + i, 450, 5, 200);
        new Ball(120 + i, 420, 20, Color.white).drawOn(d);
        new Ball(120 + i, 480, 20, Color.white).drawOn(d);
        new Ball(140 + i, 450, 20, Color.white).drawOn(d);
        new Ball(60 + i, 450, 20, Color.white).drawOn(d);
        new Ball(80 + i, 420, 20, Color.white).drawOn(d);
        new Ball(80 + i, 480, 20, Color.white).drawOn(d);
        new Ball(100 + i, 450, 20, Color.yellow).drawOn(d);
        d.setColor(Color.green);
        d.fillRectangle(200 + i, 480, 5, 200);
        new Ball(220 + i, 450, 20, Color.red).drawOn(d);
        new Ball(220 + i, 510, 20, Color.red).drawOn(d);
        new Ball(240 + i, 480, 20, Color.red).drawOn(d);
        new Ball(160 + i, 480, 20, Color.red).drawOn(d);
        new Ball(180 + i, 450, 20, Color.red).drawOn(d);
        new Ball(180 + i, 510, 20, Color.red).drawOn(d);
        new Ball(200 + i, 480, 20, Color.pink).drawOn(d);

    }

    /**
     * notify the sprite that time has passed.
     *@param  dt  the dt
     */
    public void timePassed(double dt) {
        i = i + velocity;
        if (i >= 500 || i <= 0) {
            velocity = -velocity;
        }
    }
}
