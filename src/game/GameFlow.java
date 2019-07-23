package game;


import animation.GameOver;
import animation.YouWin;
import animation.Animation;
import animation.Menu;
import animation.KeyPressStoppableAnimation;
import animation.AnimationRunner;
import animation.MenuAnimation;
import animation.art.BackroundMenu;
import animation.art.HighScoresAnimation;
import biuoop.DialogManager;

import biuoop.KeyboardSensor;
import game.indicators.Counter;
import game.indicators.HighScoresTable;
import game.indicators.ScoreInfo;

import game.levels.LevelInformation;
import geometry.objacts.Frame;

import java.io.InputStreamReader;

import read.LevelSpecificationReader;

import java.io.LineNumberReader;
import java.io.File;
import java.io.InputStream;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Game flow.
 */
public class GameFlow {

    private Counter score;
    private Counter lives;
    private Frame frame;
    private KeyboardSensor k;
    private AnimationRunner animationRunner;
    private int numLives;

    /**
     * Instantiates a new Game flow.
     *
     * @param lives the lives
     */
    public GameFlow(int lives) {
        this.frame = new Frame(800, 600);
        this.numLives = lives;
        this.k = frame.getGUI().getKeyboardSensor();
        this.animationRunner = new AnimationRunner(frame.getGUI(), 60);
    }

    /**
     * Run levels.
     *
     * @param inputStream inputStream the levels
     */
    public void runLevels(InputStream inputStream) {

        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Arkanoid", k, new BackroundMenu(), animationRunner);
        menu.addSubMenu("s", "start game", createSubMenu(inputStream));
        Task<Void> hisoore = new Task<Void>() {
            public Void run() {
                showScore();
                return null;
            }
        };

        Task<Void> end = new Task<Void>() {
            public Void run() {
                frame.getGUI().close();
                System.exit(0);
                return null;
            }
        };

        menu.addSelection("h", "high scores", hisoore);
        menu.addSelection("q", "quit", end);


        while (true) {
            animationRunner.run(menu);
            // wait for user selection
            Task<Void> task = menu.getStatus();
            task.run();

        }


    }

    /**
     * save the score.
     */
    private void saveScore() {
        HighScoresTable table = HighScoresTable.loadFromFile(new File("highscores.scr"));
        if (table.getRank(score.getValue()) < table.size()) {
            DialogManager dialog = frame.getGUI().getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            table.add(new ScoreInfo(name, score.getValue()));
            try {
                table.save(new File("highscores.scr"));
            } catch (IOException e) {
                System.out.println(" Something went wrong while reading !");
            }
        }
    }

    /**
     * create SubMenu.
     *
     * @param inputStream file
     * @return SubMenu.
     */
    private Menu<Task<Void>> createSubMenu(InputStream inputStream) {
        Menu<Task<Void>> subMenu = new MenuAnimation<Task<Void>>("Level Sets", k, new BackroundMenu(), animationRunner);
        String line = null;
        LineNumberReader lineNumberReader = null;
        String key = null;
        String maseg = null;

        try {
            lineNumberReader = new LineNumberReader(new InputStreamReader(inputStream));
            while ((line = lineNumberReader.readLine()) != null) {
                if (lineNumberReader.getLineNumber() % 2 == 1) {
                    Pattern patternKey = Pattern.compile("[^ ]:");
                    Pattern patternMaseg = Pattern.compile(":([^ ]*)");
                    Matcher matcherKey = patternKey.matcher(line);
                    Matcher matcherMaseg = patternMaseg.matcher(line);
                    if (matcherKey.find()) {
                        key = line.substring(matcherKey.start(), matcherKey.start() + 1);
                    }
                    if (matcherMaseg.find()) {
                        maseg = line.substring(matcherMaseg.start() + 1, matcherMaseg.end());
                    }
                }
                if (lineNumberReader.getLineNumber() % 2 == 0) {
                    InputStreamReader is = new InputStreamReader(
                            ClassLoader.getSystemClassLoader().getResourceAsStream(line));
                    List<LevelInformation> levels = LevelSpecificationReader.levelsReader(is);
                    if (key != null && maseg != null && levels != null) {
                        subMenu.addSelection(key, maseg, new LevelsTask(levels));
                    }
                    key = null;
                    maseg = null;
                }

            }
        } catch (IOException io) {
            System.out.println("can't read level sets file");

        } finally {
            try {
                if (lineNumberReader != null) {
                    lineNumberReader.close();
                }
            } catch (IOException e) {
                System.out.println("can't close level sets file");
            }
        }
        return subMenu;
    }

    /**
     * show scores.
     */
    private void showScore() {
        HighScoresTable table = HighScoresTable.loadFromFile(new File("highscores.scr"));
        Animation highScoresAnimation = new KeyPressStoppableAnimation(k,
                "space", new HighScoresAnimation(table, "space"));
        animationRunner.run(highScoresAnimation);

    }

    /**
     * task - run levels.
     */
    final class LevelsTask implements Task<Void> {
        private List<LevelInformation> levels;

        /**
         * constrector.
         *
         * @param levels levels to run.
         */
        private LevelsTask(List<LevelInformation> levels) {
            this.levels = levels;
        }

        /**
         * run.
         *
         * @return noting
         */
        public Void run() {
            score = new Counter(0);
            lives = new Counter(numLives);
            GameLevel level = null;

            for (LevelInformation levelInfo : levels) {

                level = new GameLevel(levelInfo, k, animationRunner, frame, score, lives);

                level.initialize();

                while (!level.shouldStop()) {
                    level.playOneTurn();
                    level.tryAgain();
                }

                frame.removeAllHitListener();

                if (lives.getValue() == 0) {
                    animationRunner.run(new KeyPressStoppableAnimation(k,
                            "space", new GameOver(level.getSprites(), score)));
                    saveScore();
                    showScore();
                    return null;
                }
            }
            if (level != null) {
                animationRunner.run(new KeyPressStoppableAnimation(k, "space",
                        new YouWin(level.getSprites(), score)));
                saveScore();
                showScore();

            }
            return null;
        }
    }
}
