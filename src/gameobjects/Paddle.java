package gameobjects;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import gamethings.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import helpers.Velocity;
import interfaces.Collidable;
import interfaces.Sprite;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Noa Amit <noa1amit2@gmail.com>.
 * @version 1.0
 * @since 7.6.2021
 */
public class Paddle implements Sprite, Collidable {
    /**
     * the class creates a paddle.
     * the paddle is the player in the game. the paddle is kind of a special block that that should keep
     * the ball up.
     * there are functions that move the ball according to the keyboard that the player press on.
     */
    //CONSTANT PARAMETER
    static final int MOVE = 10;
    static final int PART = 20;
    static final int BOARDER_LEFT = 40;
    static final int BOARDER_RIGHT = 760;
    static final int ANGEL_FIRST_PART = 300;
    static final int ANGEL_SEC_PART = 330;
    static final int ANGEL_FOURTH_PART = 30;
    static final int ANGEL_FIFTH_PART = 60;
    static final int ZREO = 0;
    //Fields
    private int speed;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddle;
    private final Color color;
    private GUI gui;
    /** Contractor.
     * @param paddle - the rectangle shape of the block.
     * @param color - the color of the block.
     */
    public Paddle(Rectangle paddle, Color color) {
        this.paddle = paddle;
        this.color = color;
        this.speed = MOVE;
    }
    /** moveOneStep - move the paddle to the left after the player press.
     */
    public void moveLeft() {
        double epsilon = Math.pow(10, -2);
        if (this.paddle.getUpperLeft().getX() - BOARDER_LEFT < this.speed
                && this.paddle.getUpperLeft().getX() - BOARDER_LEFT > ZREO) {
            double newX = this.paddle.getUpperLeft().getX() - BOARDER_LEFT;
            Point newUpperLeft = new Point(paddle.getUpperLeft().getX() - newX, paddle.getUpperLeft().getY());
            this.paddle = new Rectangle(newUpperLeft, paddle.getWidth(), paddle.getHeight(), true);
            return;
        }
        if (this.paddle.getUpperLeft().getX() - BOARDER_LEFT < epsilon) {
            return;
        }
        Point newUpperLeft = new Point(paddle.getUpperLeft().getX() - this.speed, paddle.getUpperLeft().getY());
        this.paddle = new Rectangle(newUpperLeft, paddle.getWidth(), paddle.getHeight(), true);
    }
    /** moveOneStep - move the paddle to the right after the player press.
     */
    public void moveRight() {
        double epsilon = Math.pow(10, -2);
        double upperRightX = this.paddle.getUpperLeft().getX() + this.paddle.getWidth();
        if (BOARDER_RIGHT - upperRightX < this.speed && BOARDER_RIGHT - upperRightX > ZREO) {
            double newX = BOARDER_RIGHT - upperRightX;
            Point newUpperLeft = new Point(paddle.getUpperLeft().getX() + newX, paddle.getUpperLeft().getY());
            this.paddle = new Rectangle(newUpperLeft, paddle.getWidth(), paddle.getHeight(), true);
            return;
        }
        if (BOARDER_RIGHT - upperRightX < epsilon) {
            return;
        }
        Point newUpperLeft = new Point(paddle.getUpperLeft().getX() + this.speed, paddle.getUpperLeft().getY());
        this.paddle = new Rectangle(newUpperLeft, paddle.getWidth(), paddle.getHeight(), true);
    }
    @Override
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
            return;
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
    }
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddle;
    }
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();
        double speedBall = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
        Velocity v;

        double epsilon = Math.pow(10, -2);

        double x1 = collisionPoint.getX() - epsilon;
        double x2 = collisionPoint.getX() + epsilon;

        double y1 = collisionPoint.getY() - epsilon;
        double y2 = collisionPoint.getY() + epsilon;

        Line interPoint1 = new Line(x1, collisionPoint.getY(), x2, collisionPoint.getY());
        Line interPoint2 = new Line(collisionPoint.getX(), y1, collisionPoint.getX(), y2);

        double partOne = this.paddle.getUpperLeft().getX() + PART;
        double partTwo = this.paddle.getUpperLeft().getX() + (PART * 2);
        double partThree = this.paddle.getUpperLeft().getX() + (PART * 3);
        double partFour = this.paddle.getUpperLeft().getX() + (PART * 4);
        double partFive = this.paddle.getUpperLeft().getX() + (PART * 5);

        if (interPoint1.isIntersecting(collisionPoint.getRectangle().getRightSide())
                || interPoint1.isIntersecting(collisionPoint.getRectangle().getLeftSide())) {
            dx = -dx;
        }
        if (interPoint2.isIntersecting(collisionPoint.getRectangle().getUpperLine())
                || interPoint2.isIntersecting(collisionPoint.getRectangle().getDownLine())) {
            if (collisionPoint.getX() > this.paddle.getUpperLeft().getX()
                    && collisionPoint.getX() < partOne) {
                v = Velocity.fromAngleAndSpeed(ANGEL_FIRST_PART, speedBall);
                return v;
            }
            if (collisionPoint.getX() > partOne && collisionPoint.getX() < partTwo) {
                v = Velocity.fromAngleAndSpeed(ANGEL_SEC_PART, speedBall);
                return v;
            }
            if (collisionPoint.getX() > partThree && collisionPoint.getX() < partFour) {
                v = Velocity.fromAngleAndSpeed(ANGEL_FOURTH_PART, speedBall);
                return v;
            }
            if (collisionPoint.getX() > partFour && collisionPoint.getX() < partFive) {
                v = Velocity.fromAngleAndSpeed(ANGEL_FIFTH_PART, speedBall);
                return v;
            }
            dy = -dy;
        }

        return new Velocity(dx, dy);
    }
    /**
     * Add this paddle to the game.
     * @param g the game that the paddle is add to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
    /** setGui - set method. will update the relevant gui.
     * @param g - the relevant gui.
     */
    public void setGui(GUI g) {
        this.gui = g;
    }
    /** setGui - set method. will update the gui.
     * @param key - the relevant keyboard sensor to the game.
     */
    public void setKeyboard(KeyboardSensor key) {
        this.keyboard = key;
    }
   @Override
    public java.util.List<Point> intersectionPointOfCollide(Line line) {
        List<Point> intersectionPoints = new ArrayList<>();
        if (line.intersectionWith(this.paddle.getLeftSide()) != null) {
            Point inter = line.intersectionWith(this.paddle.getLeftSide());
            intersectionPoints.add(inter);
        }
        if (line.intersectionWith(this.paddle.getRightSide()) != null) {
            Point inter = line.intersectionWith(this.paddle.getRightSide());
            intersectionPoints.add(inter);
        }
        if (line.intersectionWith(this.paddle.getDownLine()) != null) {
            Point inter = line.intersectionWith(this.paddle.getDownLine());
            intersectionPoints.add(inter);
        }
        if (line.intersectionWith(this.paddle.getUpperLine()) != null) {
            Point inter = line.intersectionWith(this.paddle.getUpperLine());
            intersectionPoints.add(inter);
        }
        return intersectionPoints;
    }
    /** setCenter - set method. will update the speed of the ball.
     * @param speed1 the update speed for the paddle.
     */
    public void setSpeed(int speed1) {
        this.speed = speed1;
    }
}