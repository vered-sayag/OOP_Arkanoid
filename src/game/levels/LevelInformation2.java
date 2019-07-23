package game.levels;

import animation.art.Bus;
import animation.art.ColorFull;
import game.Sprite;
import geometry.objacts.Block;
import geometry.primitives.Point;
import geometry.primitives.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Level information 4.
 */
public class LevelInformation2 implements LevelInformation {
    private int width = 800;
    private int height = 600;
    private int widthBlaks = 50;
    private int heightBlaks = 20;

    /**
     * Number of balls int.
     *
     * @return the int
     */
    public int numberOfBalls() {
        return 8;
    }

    /**
     * Initial ball velocities list.
     *
     * @return the list
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        int up = 180;
        int dAngel = 5;


        for (int i = 0; i < numberOfBalls(); i++) {
            if (i % 2 == 0) {
                list.add(Velocity.fromAngleAndSpeed(up + dAngel, 360));
            } else {
                list.add(Velocity.fromAngleAndSpeed(up - dAngel, 360));
            }
            dAngel = dAngel + 5;
        }
        return list;
    }

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    public int paddleSpeed() {
        return 900;
    }

    /**
     * Paddle width int.
     *
     * @return the int
     */
    public int paddleWidth() {
        return 340;
    }

    /**
     * Level name string.
     *
     * @return the string
     */
    public String levelName() {
        return "Bus";
    }

    /**
     * Gets background.
     *
     * @return the background
     */
    public Sprite getBackground() {
        return new Bus();
    }

    /**
     * Blocks list.
     *
     * @return the list
     */
    public List<Block> blocks() {
        ColorFull colorFull = new ColorFull();
        List<Block> blocks = new ArrayList<Block>();
        int k = 0;
        Block block;

        int i = 6 * heightBlaks;
            for (int j =  widthBlaks; j < width -  widthBlaks; j = j + widthBlaks) {
                if (k % 2 == 0) {
                    block = new Block(new Point(j, i), widthBlaks, heightBlaks, Color.red, 2);
                    blocks.add(block);
                } else {
                    block = new Block(new Point(j, i), widthBlaks, heightBlaks, Color.white, 1);
                    blocks.add(block);
                }
                k++;
            }
        return blocks;

    }

    /**
     * Number of blocks to remove int.
     *
     * @return the int
     */
    public int numberOfBlocksToRemove() {
        int numBlocks = 0;

        for (int j =  widthBlaks; j < width -  widthBlaks; j = j + widthBlaks) {
            numBlocks++;
        }

        return numBlocks;
    }
}
