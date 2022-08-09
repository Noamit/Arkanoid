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
import java.util.Random;
/**
 * @author Noa Amit <noa1amit2@gmail.com>.
 * @version 1.0
 * @since 7.6.2021
 */
public class Green3 implements LevelInformation {
    //CONSTANT PARAMETER
    static final int NUM_OF_BALLS = 2;
    static final int NUM_OF_BLOCKS = 40;
    static final int PADDLE_SPEED = 10;
    static final int PADDLE_WIDTH = 100;
    static final int START_BOARD = 0;
    static final int WIDTH = 800;
    static final int HIGHT = 600;
    static final int BETWEEN_BLOCKS = 50;
    static final int WIDTH_BLOCK = 50;
    static final int HIGHT_BLOCK = 25;
    //Fields
    private final int numberOfBalls;
    private final int paddleSpeed;
    private final int paddleWidth;
    /**
     * Contractor.
     */
    public Green3() {
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
        velocities.add(new Velocity(7, -5));
        velocities.add(new Velocity(-4, -4));
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
        return "Green 3";
    }
    @Override
    public Sprite getBackground() {
        Block block = new Block(new Rectangle(new Point(START_BOARD, START_BOARD),
                WIDTH, HIGHT, false),
                Color.GREEN.darker().darker());
        return block;
    }
    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int start = 260;
        int current = 10;
        int firstHight = 150;
        for (int i = 1; i <= 5; i++) {
            Color recent = createColor();
            for (int k = current; k > 0; k--) {
                Block block = new Block(new Rectangle(new Point(start, firstHight),
                        WIDTH_BLOCK, HIGHT_BLOCK, false), recent);
                blocks.add(block);
                start = start + BETWEEN_BLOCKS;
            }
            start = 260 + (BETWEEN_BLOCKS * i);
            current--;
            firstHight = firstHight + 25;
        }
        return blocks;
    }
    @Override
    public int numberOfBlocksToRemove() {
        return NUM_OF_BLOCKS;
    }
    /**
     * createColor - create a random color to the blocks in the row.
     * @return  color - the color of the block
     */
    public Color createColor() {
        Random rand = new Random();
        float c1 = rand.nextFloat();
        float c2 = rand.nextFloat();
        float c3 = rand.nextFloat();
        return new Color(c1, c2, c3).darker();
    }
}
