package animation.art;

import biuoop.DrawSurface;
import game.Sprite;
import geometry.objacts.Ball;
import geometry.objacts.Block;
import geometry.primitives.Point;

import java.awt.Color;

/**
 * The type Bake round computer.
 */
public class BakeRoundComputer implements Sprite {

    /**
     * draw the sprite to the screen.
     *
     * @param d given draw surface.
     */
    public void drawOn(DrawSurface d) {
        new Block(new Point(0, 0), d.getWidth(), d.getHeight(), Color.blue).drawOn(d);

        d.setColor(Color.black);
        d.fillRectangle(200, 450, 50, 200);
        new Block(new Point(100, 425), 250, 150, Color.BLACK).drawOn(d);
        new Block(new Point(105, 430), 240, 140, Color.blue).drawOn(d);


        ColorFull colorFull = new ColorFull();

        int k = 0;

        for (int i = 2 * 4 + 430; i < 140 / 3 + 4 + 430; i = i + 4) {
            for (int j = 2 * 10 + 105; j < 240 - 2 * 10 + 105; j = j + 10) {
                new Block(new Point(j, i), 10, 4, colorFull.getColor3(k)).drawOn(d);

            }

            k++;
        }
        new Block(new Point(180, 568), 20, 2, Color.white).drawOn(d);

        new Ball(180, 560, 2, Color.lightGray).drawOn(d);

    }

    /**
     * notify the sprite that time has passed.
     *
     * @param dt the dt
     */
    public void timePassed(double dt) {

    }
}
