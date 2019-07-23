package game.levels;

import animation.art.BakeRoundFlowres;
import animation.art.ColorFull;
import game.Sprite;
import geometry.objacts.Block;
import geometry.primitives.Point;
import geometry.primitives.Velocity;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Level information 1.
 */
public class LevelInformation3 implements LevelInformation {
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
        return 2;
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
                list.add(Velocity.fromAngleAndSpeed(up + dAngel, 400));
            } else {
                list.add(Velocity.fromAngleAndSpeed(up - dAngel, 400));
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
        return 600;
    }

    /**
     * Paddle width int.
     *
     * @return the int
     */
    public int paddleWidth() {
        return 120;
    }

    /**
     * Level name string.
     *
     * @return the string
     */
    public String levelName() {
        return "garden";
    }

    /**
     * Gets background.
     *
     * @return the background
     */
    public Sprite getBackground() {
        return new BakeRoundFlowres();
    }

    /**
     * Blocks list.
     *
     * @return the list
     */
    public List<Block> blocks() {

        ColorFull colorFull = new ColorFull();
        List<Block> blocks = new ArrayList<Block>();
        int k = 1;
        Block block;
        for (int i = 2 * heightBlaks; i < height / 3 + heightBlaks; i = i + heightBlaks) {
            for (int j = widthBlaks + k * widthBlaks; j < width; j = j + widthBlaks) {
                block = new Block(new Point(j, i), widthBlaks, heightBlaks, colorFull.getColor1(k), 1);
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
        int k = 1;
        int numBlocks = 0;
        for (int i = 2 * heightBlaks; i < height / 3 + heightBlaks; i = i + heightBlaks) {
            for (int j = widthBlaks + k * widthBlaks; j < width; j = j + widthBlaks) {
                numBlocks = numBlocks + 1;
            }
            k++;
        }
        return numBlocks;
    }
}
