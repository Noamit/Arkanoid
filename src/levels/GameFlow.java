package levels;
import animations.AnimationRunner;
import animations.GameOver;
import animations.KeyPressStoppableAnimation;
import animations.WinScreen;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gamethings.GameLevel;
import helpers.Counter;
import interfaces.LevelInformation;
import java.util.List;
/**
 * @author Noa Amit <noa1amit2@gmail.com>.
 * @version 1.0
 * @since 7.6.2021
 */
public class GameFlow {
    //CONSTANT PARAMETER
    static final int FINISH_LEVEL = 100;
    static final int WIDTH = 800;
    static final int HIGHT = 600;
    static final int LIVES = 3;
    //Fields
    private final GUI gui;
    private final AnimationRunner runner;
    private final KeyboardSensor keyboard;
    private final Counter counterForScore;
    private final Counter lives;
    /**
     * Contractor.
     */
    public GameFlow() {
        this.gui = new GUI("Game", WIDTH, HIGHT);
        this.runner = new AnimationRunner(this.gui);
        this.keyboard = this.gui.getKeyboardSensor();
        this.counterForScore = new Counter(0);
        this.lives = new Counter(LIVES);
    }

    /**
     * runLevels. run the levels by their order.
     * @param levels - a list of levels in order.
     */
    public void runLevels(List<LevelInformation> levels) {
        Boolean winner = true;
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.gui, this.runner, this.keyboard, this.counterForScore,
                    lives);
            level.initialize();

            while (!level.shouldStop()) {
                level.run();
            }
            if (level.getCounterForBall().getValue() == 0) {
                lives.decrease(1);
                while (lives.getValue() != 0 && level.getCounterForBlock().getValue() != 0) {
                    //remove the paddle from the game and create another one that starts from the center.
                    level.removeSprite(level.getPaddle());
                    level.removeCollidable(level.getPaddle());
                    level.updatePaddleToTheGame();
                    level.updateBallToTheGame();
                    level.setRunning(true);
                    while (!level.shouldStop()) {
                        level.run();
                    }
                    if (level.getCounterForBlock().getValue() == 0) {
                        break;
                    } else {
                        lives.decrease(1);
                    }
                }
            }
            // the level is over because the player is lost or removed all blocks.
            if (this.lives.getValue() == 0) {
                winner = false;
                break;
            } else {
                this.counterForScore.increase(FINISH_LEVEL);
            }
        }
        //the animation at the end of the game depend if the player lose or win.
        if (winner) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new WinScreen(this.counterForScore.getValue())));
        } else {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new GameOver(this.counterForScore.getValue())));
        }
        gui.close();
    }
}

