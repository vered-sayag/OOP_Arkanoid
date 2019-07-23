package animation.art;

import biuoop.DrawSurface;
import game.Sprite;
import geometry.objacts.Ball;
import geometry.objacts.Block;
import geometry.primitives.Point;

import java.awt.Color;

/**
 * The type Bus.
 */
public class Bus implements Sprite {
    private int i = 0;

    /**
     * draw the sprite to the screen.
     *
     * @param d given draw surface.
     */
    public void drawOn(DrawSurface d) {
        new Block(new Point(0, 0), d.getWidth(), d.getHeight(), new Color(80, 80, 200)).drawOn(d);
        new Block(new Point(0, 530), d.getWidth(), d.getHeight(), Color.gray).drawOn(d);
        int j = 0;
        for (int k = 0; k < 800; k = k + 30) {
            if (j % 2 == 0) {
                d.setColor(Color.red);
            } else {
                d.setColor(Color.white);
            }
            d.fillRectangle(k, 510, 30, 20);
            j++;
        }

        d.setColor(Color.yellow);
        d.fillRectangle(850 - i, 350, 380, 200);
        d.fillRectangle(800 - i, 450, 100, 100);
        new Ball(930 - i, 560, 30, Color.black).drawOn(d);
        new Ball(1170 - i, 558, 30, Color.black).drawOn(d);

        for (j = 870 - i; j < 1180 - i; j = j + 70) {
            d.setColor(new Color(80, 80, 200));
            d.fillRectangle(j, 370, 50, 60);

        }

    }

    /**
     * notify the sprite that time has passed.
     *
     * @param dt the dt
     */
    public void timePassed(double dt) {
        i++;
        if (i > 1300) {
            i = 0;
        }
    }
}
