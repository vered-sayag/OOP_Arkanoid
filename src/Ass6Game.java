

import game.GameFlow;

import java.io.InputStream;


/**
 * The type Ass 6 game.
 */
public class Ass6Game {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GameFlow game = new GameFlow(7);
        InputStream is;
        if (args.length > 0) {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream(args[0]);

        } else {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream("level_sets.txt");
        }
        game.runLevels(is);
    }
}
