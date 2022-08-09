
package interfaces;
import gameobjects.Block;
import helpers.Velocity;
import java.util.List;
/**
 * @author Noa Amit <noa1amit2@gmail.com>.
 * @version 1.0
 * @since 7.6.2021
 */
public interface LevelInformation {
    /**
     * numberOfBalls - The initial of the balls.
     * @return the numbers of the ball in the start of the level.
     */
    int numberOfBalls();
    /**
     * initialBallVelocities - The initial velocity of each ball.
     * @return a list of the velocities of the balls.
     */
    List<Velocity> initialBallVelocities();
    /**
     * paddleSpeed.
     * @return the paddle's speed in the level.
     */
    int paddleSpeed();
    /**
     * paddleWidth.
     * @return the paddle's width in the level.
     */
    int paddleWidth();
    /**
     * levelName - the level name will be displayed at the top of the screen.
     * @return the level name.
     */
    String levelName();
    /**
     * getBackground.
     * @return a sprite with the background of the level
     */
    Sprite getBackground();
    /**
     * blocks - The Blocks that make up this level, each block contains its size, color and location.
     * @return the list of the blocks in the level.
     */
    List<Block> blocks();
    /**
     * numberOfBlocksToRemove.
     * @return the number of the ball that should be removed before the level is considered to be "cleared".
     */
    int numberOfBlocksToRemove();
}
