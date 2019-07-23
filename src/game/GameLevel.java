package game;

import animation.PauseScreen;
import animation.KeyPressStoppableAnimation;
import animation.AnimationRunner;
import animation.Animation;
import biuoop.DrawSurface;
import animation.CountdownAnimation;

import java.awt.Color;
import java.util.List;

import biuoop.KeyboardSensor;
import game.indicators.Counter;
import game.indicators.LevelIndicator;
import game.indicators.LivesIndicator;
import game.indicators.ScoreIndicator;
import game.levels.LevelInformation;
import geometry.objacts.Ball;
import geometry.objacts.Frame;
import geometry.objacts.Paddle;
import geometry.objacts.Block;
import geometry.primitives.Point;
import game.lisiners.BallRemover;
import game.lisiners.BlockRemover;
import game.lisiners.HitListener;
import game.lisiners.ScoreTrackingListener;


/**
 * game - initialize and run.
 *
 * @author Vered Sayag
 * @version 15.04.2018
 */
public class GameLevel implements Animation {


    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private Frame frame;
    private int width = 800;
    private int height = 600;
    private int heightPaddel;
    private int rdiusBal;
    private Counter score;
    private Counter lives;
    private Paddle paddle;
    private boolean lose = false;
    private boolean win = false;
    private AnimationRunner runner;
    private KeyboardSensor keyboard;
    private LevelInformation levelInfo;

    /**
     * constructor.
     *
     * @param levelInfo       the level info
     * @param keyboardSensor  the keyboard sensor
     * @param animationRunner the animation runner
     * @param frame           the frame
     * @param score           the score
     * @param lives           the lives
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboardSensor,
                     AnimationRunner animationRunner, Frame frame, Counter score, Counter lives) {

        this.heightPaddel = 10;
        this.rdiusBal = 5;
        this.score = score;
        this.lives = lives;
        this.frame = frame;
        this.levelInfo = levelInfo;
        this.runner = animationRunner;
        this.keyboard = keyboardSensor;

    }

    /**
     * remove collidable.
     *
     * @param c collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * remove Sprite.
     *
     * @param s Sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * add a Collidable to the game.
     *
     * @param c Collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add a Sprite to the game.
     *
     * @param s Sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle)
     * and add them to the game.
     */
    public void initialize() {

        frame.addToGame(this);
        HitListener listener1 = new BlockRemover(this, new Counter(levelInfo.numberOfBlocksToRemove()));
        HitListener listener2 = new ScoreTrackingListener(score);

        addSprite(levelInfo.getBackground());

        List<Block> blocks = levelInfo.blocks();
        for (int i = 0; i < blocks.size(); i++) {
            Block block = blocks.get(i).copy();
            block.addHitListener(listener1);
            block.addHitListener(listener2);
            block.addToGame(this);
        }


        //create paddle
        paddle = new Paddle(new Point(width / 2 - levelInfo.paddleWidth() / 2,
                height - heightPaddel), levelInfo.paddleWidth(), heightPaddel,
                Color.white, this.frame, levelInfo.paddleSpeed());
        paddle.addToGame(this);


        Block block;
        block = new Block(new Point(0, 0), width, 20, Color.lightGray);
        block.addToGame(this);
        //create balls
        addSprite(new ScoreIndicator(score));
        addSprite(new LivesIndicator(lives));
        addSprite(new LevelIndicator(levelInfo.levelName()));


    }

    /**
     * Run the game -- start the animation loop.
     */
    public void playOneTurn() {

        createBallsOnTopOfPaddle();
        this.runner.run(new CountdownAnimation(2, 3, sprites));

        runner.run(this);

        if (win) {
            score.increase(100);
        }

    }


    /**
     * Gets ennvironment.
     *
     * @return the GameEnvironment of the game.
     */
    public GameEnvironment getEnnvironment() {
        return this.environment;
    }


    /**
     * Lose.
     */
    public void lose() {
        lives.decrease(1);
        lose = true;
    }

    /**
     * Try again.
     */
    public void tryAgain() {
        if (lives.getValue() > 0) {
            lose = false;
        }
    }

    /**
     * Win.
     */
    public void win() {
        win = true;
    }

    /**
     * Should stop boolean.
     *
     * @return the boolean
     */
    public boolean shouldStop() {
        return win || lose;
    }

    /**
     * Do one frame.
     *
     * @param d  the d
     * @param dt the dt
     */
    public void doOneFrame(DrawSurface d, double dt) {
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, "space", new PauseScreen()));
        }
        sprites.notifyAllTimePassed(dt);
        sprites.drawAllOn(d);

    }

    /**
     * Create balls on top Of paddle.
     */
    private void createBallsOnTopOfPaddle() {
        frame.addHitListener(new BallRemover(this, new Counter(levelInfo.numberOfBalls())));
        Ball b;

        for (int i = 0; i < levelInfo.numberOfBalls(); i++) {
            b = new Ball(width / 2, height - heightPaddel - rdiusBal, rdiusBal, Color.LIGHT_GRAY);
            b.setVelocity(levelInfo.initialBallVelocities().get(i));
            b.addToGame(this);
        }
        paddle.setLocation(new Point(width / 2 - levelInfo.paddleWidth() / 2, height - heightPaddel));
    }

    /**
     * Gets sprites.
     *
     * @return the sprites
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

}

