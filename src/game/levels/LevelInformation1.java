package game.levels;

import animation.art.BakeRoundMiki;
import animation.art.ColorFull;
import game.Sprite;
import geometry.objacts.Block;
import geometry.primitives.Point;
import geometry.primitives.Velocity;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Level information 3.
 */
public class LevelInformation1 implements LevelInformation {
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
        return 1;
    }

    /**
     * Initial ball velocities list.
     *
     * @return the list
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();

        list.add(Velocity.fromAngleAndSpeed(180, 360));

        return list;
    }

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    public int paddleSpeed() {
        return 1000;
    }

    /**
     * Paddle width int.
     *
     * @return the int
     */
    public int paddleWidth() {
        return 200;
    }

    /**
     * Level name string.
     *
     * @return the string
     */
    public String levelName() {
        return "Miki";
    }

    /**
     * Gets background.
     *
     * @return the background
     */
    public Sprite getBackground() {
        return new BakeRoundMiki();
    }

    /**
     * Blocks list.
     *
     * @return the list
     */
    public List<Block> blocks() {

        ColorFull colorFull = new ColorFull();
        List<Block> blocks = new ArrayList<Block>();
        blocks.add(new Block(new Point(375, 200), widthBlaks, heightBlaks, colorFull.getColor2(1), 1));
        return blocks;

    }

    /**
     * Number of blocks to remove int.
     *
     * @return the int
     */
    public int numberOfBlocksToRemove() {

        return 1;
    }
}
