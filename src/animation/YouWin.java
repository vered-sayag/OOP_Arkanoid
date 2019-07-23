package animation;

import game.indicators.Counter;
import game.SpriteCollection;


/**
 * The type You win.
 */
public class YouWin extends EndGame {

    /**
     * Instantiates a new You win.
     *
     * @param gameScreen the game screen
     * @param score      the score
     */
    public YouWin(SpriteCollection gameScreen, Counter score) {
        super(gameScreen, score);

    }

    /**
     * return "You Win!".
     *
     * @return String kind
     */
    protected String kind() {
        return "You Win!";
    }
}
