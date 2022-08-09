package levels;
import gameobjects.Block;
import geometry.Point;
import geometry.Rectangle;
import helpers.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Noa Amit <noa1amit2@gmail.com>.
 * @version 1.0
 * @since 7.6.2021
 */
public class DirectHit implements LevelInformation {
    //CONSTANT PARAMETER
    static final int NUM_OF_BALLS = 1;
    static final int NUM_OF_BLOCKS = 1;
    static final int PADDLE_SPEED = 10;
    static final int PADDLE_WIDTH = 100;
    static final int START_BOARD = 0;
    static final int WIDTH = 800;
    static final int HIGHT = 600;
    static final int WIDTH_AND_HIGHT = 40;
    static final int FIRST_BLOCK_X = 380;
    static final int FIRST_BLOCK_Y = 150;
    //Fields
    private final int numberOfBalls;
    private final int paddleSpeed;
    private final int paddleWidth;
    /**
     * Contractor.
     */
    public DirectHit() {
        this.numberOfBalls = NUM_OF_BALLS;
        this.paddleSpeed = PADDLE_SPEED;
        this.paddleWidth = PADDLE_WIDTH;
    }
    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(new Velocity(0, -8));
        return velocities;
    }
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }

    @Override
    public Sprite getBackground() {
        Block block = new Block(new Rectangle(new Point(START_BOARD, START_BOARD),
                WIDTH, HIGHT, false), Color.black);
        return block;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Block block = new Block(new Rectangle(new Point(FIRST_BLOCK_X, FIRST_BLOCK_Y),
                WIDTH_AND_HIGHT, WIDTH_AND_HIGHT, false), Color.red);
        blocks.add(block);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS;
    }
}
