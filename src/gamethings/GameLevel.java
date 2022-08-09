package gamethings;
import animations.AnimationRunner;
import animations.CountdownAnimation;
import animations.KeyPressStoppableAnimation;
import animations.PauseScreen;
import biuoop.GUI;
import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import biuoop.KeyboardSensor;
import gameobjects.Ball;
import gameobjects.Block;
import gameobjects.Paddle;
import geometry.Point;
import geometry.Rectangle;
import helpers.Counter;
import helpers.ScoreIndicator;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.LevelInformation;
import interfaces.Sprite;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
/**
 * @author Noa Amit <noa1amit2@gmail.com>.
 * @version 1.0
 * @since 7.6.2021
 */
public class GameLevel implements Animation {
    /**
     * the class creates a game.
     * the class declares on blocks, paddle, boarders, balls and also have the run methods that responsible
     * for the operation of the game.
     */
    //CONSTANT PARAMETERS
    static final int WIDTH_BOUND = 800;
    static final int LENGHT_BOUND = 600;
    static final int BOARD_START = 0;
    static final int BOARDER_SIZE = 40;
    static final int X_COORDINATE_BALL = 400;
    static final int Y_COORDINATE_BALL = 555;
    static final int RADIUS = 4;
    static final int X_COORDINATE_PADDLE = 350;
    static final int X_COORDINATE_BIG_PADDLE = 100;
    static final int Y_COORDINATE_PADDLE = 560;
    static final int HIGHT_PADDLE = 20;
    static final int TEXT_SIZE = 20;
    static final int TEXT_START = 550;
    static final int TEXT_END = 25;
    static final int START_COUNTDOWN = 3;
    //Fields
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final Counter counterForBlock;
    private final Counter counterForBall;
    private final Counter counterForScore;
    private boolean running;
    private final GUI gui;
    private final AnimationRunner runner;
    private final KeyboardSensor keyboard;
    private final LevelInformation levelInformation;
    private final Counter lives;
    private Paddle paddle;
    /** Contractor. creates a list of sprites and the environment in the game.
     * @param levelInformation - the current game Level
     * @param gui - the screen of the game
     * @param ar - runs the game on the screen
     * @param ks - keyboard sensor
     * @param counterForScore - the score in the current level
     * @param lives - how much lives are left
     */
    public GameLevel(LevelInformation levelInformation, GUI gui, AnimationRunner ar, KeyboardSensor ks,
                     Counter counterForScore, Counter lives) {
        this.sprites = new SpriteCollection(new ArrayList<>());
        this.environment = new GameEnvironment();
        this.counterForBlock = new Counter(0);
        this.counterForBall = new Counter(0);
        this.counterForScore = counterForScore;
        this.levelInformation = levelInformation;
        this.gui = gui;
        this.runner = ar;
        this.keyboard = ks;
        this.running = true;
        this.lives = lives;
    }
    /** the method get a collidable object and edd it to the environment.
     * @param c a collidable object.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }
    /** the method get a sprite object and edd it to the environment.
     * @param s a sprite object.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }
    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        BlockRemover blockRemover = new BlockRemover(this, counterForBlock);
        BallRemover ballRemover = new BallRemover(this, counterForBall);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(counterForScore);

        //boarders
        createBoards(BOARD_START, BOARD_START, WIDTH_BOUND - BOARDER_SIZE, BOARDER_SIZE, Color.gray.brighter());
        Block down = createBoards(BOARDER_SIZE, LENGHT_BOUND - BOARDER_SIZE + 10, WIDTH_BOUND - (2 * BOARDER_SIZE),
                BOARDER_SIZE - 10, Color.gray.brighter());
        removeSprite(down);
        down.addHitListener(ballRemover);
        createBoards(BOARD_START, BOARDER_SIZE, BOARDER_SIZE, LENGHT_BOUND - BOARDER_SIZE,
                Color.gray.brighter());
        createBoards(WIDTH_BOUND - BOARDER_SIZE, BOARD_START, BOARDER_SIZE, LENGHT_BOUND, Color.gray.brighter());

        //balls
        this.updateBallToTheGame();

        //blocks
        for (Block block : this.levelInformation.blocks()) {
            block.addToGame(this);
            this.counterForBlock.increase(1);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
            block.setG(this);
        }
        //paddle
        this.updatePaddleToTheGame();
    }

    /**
     * createBoards - the method creates the block that boarders.
     * @param x the x coordinate of the upperLeft of the rectangle shape of the block.
     * @param y the y coordinate of the upperLeft of the rectangle shape of the block.
     * @param width the width of the rectangle shape of the block.
     * @param height the height of the rectangle shape of the block.
     * @param color the color og the block.
     * @return Block - the new block that is crated.
     */
    public Block createBoards(double x, double y, int width, int height, Color color) {
        Block newBlock = new Block(new Rectangle(new Point(x, y), width, height, false), color);
        newBlock.setG(this);
        newBlock.addToGame(this);
        return newBlock;
    }
    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.runner.run(new CountdownAnimation(0.66, START_COUNTDOWN, this.sprites,
                this.levelInformation.getBackground()));
        //this.running = true;
        this.runner.run(this);
    }

    /**
     * removeCollidable. remove a collidable object from the list.
     * @param c - a collidable object that will remove from the game environment,
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidablesCollection().remove(c);
    }
    /**
     * removeCollidable. remove a sprite object from the list.
     * @param s - a sprite object that will remove from the game environment,
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.levelInformation.getBackground().drawOn(d);
        this.sprites.drawAllOn(d);
        d.setColor(Color.black);
        d.drawText(TEXT_START, TEXT_END, "Level Name: " + this.levelInformation.levelName(), TEXT_SIZE);
        d.drawText(TEXT_START - 500, TEXT_END, "Lives: " + this.lives.getValue(), TEXT_SIZE);
        this.sprites.notifyAllTimePassed();
        if (this.keyboard.isPressed("p") || this.keyboard.isPressed("P")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
        if (this.counterForBlock.getValue() == 0) {
            this.running = false;
        }
        if (this.counterForBall.getValue() == 0) {
            this.running = false;
        }
    }
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
    /** setRunning. update if the game should to stop or to continue.
     * @param running1 - if the animation of the game should to run.
     */
    public void setRunning(boolean running1) {
        this.running = running1;
    }
    /** updateBallToTheGame - create the balls in the game.
     */
    public void updateBallToTheGame() {
        int start1 = 200;
        int start2 = 600;
        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            Point center;
            Ball newBall;
            if (this.levelInformation.levelName().equals("Wide Easy") && i <= 4) {
                center = new Point(start1, Y_COORDINATE_BALL);
            } else if (this.levelInformation.levelName().equals("Wide Easy") && i > 4) {
                center = new Point(start2, Y_COORDINATE_BALL);
            } else {
                center = new Point(X_COORDINATE_BALL, Y_COORDINATE_BALL);
            }
            this.counterForBall.increase(1);
            newBall = new Ball(center, RADIUS, Color.blue.darker());
            newBall.setVelocity(this.levelInformation.initialBallVelocities().get(i));
            newBall.setEnvironment(this.environment);
            newBall.addToGame(this);
        }
    }
    /** updatePaddleToTheGame - create the paddle in the game.
     */
    public void updatePaddleToTheGame() {
        ScoreIndicator scoreIndicator = new ScoreIndicator(counterForScore);
        Rectangle forPaddle = new Rectangle(new Point(X_COORDINATE_PADDLE, Y_COORDINATE_PADDLE),
                this.levelInformation.paddleWidth(), HIGHT_PADDLE, true);
        if (this.levelInformation.levelName().equals("Wide Easy")) {
            forPaddle = new Rectangle(new Point(X_COORDINATE_BIG_PADDLE, Y_COORDINATE_PADDLE),
                    this.levelInformation.paddleWidth(), HIGHT_PADDLE, true);
        }
        paddle = new Paddle(forPaddle, Color.yellow);
        paddle.setSpeed(this.levelInformation.paddleSpeed());
        paddle.setGui(this.gui);
        paddle.setKeyboard(keyboard);
        paddle.addToGame(this);
        sprites.addSprite(scoreIndicator);
    }
    /** accessor.
     * @return the current paddle in the game.
     */
    public Paddle getPaddle() {
        return this.paddle;
    }
    /** accessor.
     * @return the number of the balls that left in the level of the ball.
     */
    public Counter getCounterForBall() {
        return counterForBall;
    }
    /** accessor.
     * @return the number of the blocks that left in the level of the ball.
     */
    public Counter getCounterForBlock() {
        return counterForBlock;
    }

}