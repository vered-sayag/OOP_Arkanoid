package game.levels;

import animation.art.BakeRoundComputer;
import animation.art.ColorFull;
import game.Sprite;
import geometry.objacts.Block;
import geometry.primitives.Point;
import geometry.primitives.Velocity;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Level information 2.
 */
public class LevelInformation4 implements LevelInformation {
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
        return 3;
    }

    /**
     * Initial ball velocities list.
     *
     * @return the list
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        int up = 179;
        int dAngel = 20;

        list.add(Velocity.fromAngleAndSpeed(up, 600));

        list.add(Velocity.fromAngleAndSpeed(up + dAngel, 600));
        list.add(Velocity.fromAngleAndSpeed(up - dAngel, 600));
        return list;
    }

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    public int paddleSpeed() {
        return 1200;
    }

    /**
     * Paddle width int.
     *
     * @return the int
     */
    public int paddleWidth() {
        return 160;
    }

    /**
     * Level name string.
     *
     * @return the string
     */
    public String levelName() {
        return "Recursion";
    }

    /**
     * Gets background.
     *
     * @return the background
     */
    public Sprite getBackground() {
        return new BakeRoundComputer();
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
        for (int i = 2 * heightBlaks; i < height / 3 + heightBlaks; i = i + heightBlaks) {
            for (int j = 2 * widthBlaks; j < width - 2 * widthBlaks; j = j + widthBlaks) {
                block = new Block(new Point(j, i), widthBlaks, heightBlaks, colorFull.getColor3(k), (k % 4) + 1);
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
        for (int i = 2 * heightBlaks; i < height / 3 + heightBlaks; i = i + heightBlaks) {
            for (int j = 2 * widthBlaks; j < width - 2 * widthBlaks; j = j + widthBlaks) {
                numBlocks = numBlocks + 1;
            }
        }
        return numBlocks;
    }
}
