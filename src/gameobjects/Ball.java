package gameobjects;
import biuoop.DrawSurface;
import gamethings.GameLevel;
import gamethings.GameEnvironment;
import geometry.Line;
import geometry.Point;
import helpers.Velocity;
import interfaces.Sprite;
/**
 * @author Noa Amit <noa1amit2@gmail.com>.
 * @version 1.0
 * @since 7.6.2021
 */
public class Ball implements Sprite {
    /**
     * the class creates a ball.
     * there is also a methods that move the ball by his feature.
     */
    //CONSTANT PARAMETERS
    static final int ONE = 1;
    static final double FOR_VELOCITY = 1;
    //Fields
    private Point center;
    private final int r;
    private final java.awt.Color color;
    private Velocity velocity;
    private GameEnvironment environment;

    /** Contractor.
     * @param center - the center point of the ball.
     * @param r - the radius of the ball.
     * @param color - the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
        this.velocity = new Velocity(ONE, ONE);
    }
    /** accessor.
     * @return the x coordinate of the ball's center.
     */
    public int getX() {
        return (int) this.center.getX();
    }
    /** accessor.
     * @return the y coordinate of the ball's center.
     */
    public int getY() {
        return (int) this.center.getY();
    }
    /** accessor.
     * @return the radius of the ball.
     */
    public int getSize() {
        return this.r;
    }
    /** accessor.
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }
    /** accessor.
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }
    /** drawOn - the method draw a ball.
     * @param surface - The object through which to draw on the board.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), r);
    }
    /** setVelocity - set method. will change the velocity of the ball.
     * @param v - the velocity of the ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }
    /** setVelocity - set method. will change the velocity of the ball.
     * @param dx - the dx of velocity of the ball.
     * @param dy - the dy of velocity of the ball.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }
    /** setCenter - set method. will change the velocity of the ball.
     * @param x - the x coordinate of the ball's center.
     * @param y - the y coordinate of the ball's center.
     */
    public void setCenter(double x, double y) {
        this.center = new Point(x, y);
    }
    /** moveOneStep - move the ball to a new point center according to the ball's velocity.
     */
    public void moveOneStep() {
        Point endTrajectory = velocity.applyToPoint(this.center);
        Line trajectory = new Line(this.center, endTrajectory);
        if (this.environment.getClosestCollision(trajectory) == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            Point intersection = this.environment.getClosestCollision(trajectory).collisionPoint();
            Point almostIntersection = new Point(intersection.getX() - velocity.getDx(),
                    intersection.getY() - velocity.getDy());
            Velocity newVelocity = environment.getClosestCollision(trajectory).collisionObject().
                    hit(this, intersection, this.velocity);
            this.setVelocity(newVelocity);
            this.center = almostIntersection;
        }
    }
    /** setCenter - set method. will update the environment of the ball.
     * @param e - the environment of the ball.
     */
    public void setEnvironment(GameEnvironment e) {
        this.environment = e;
    }
    /** accessor.
     * @return the environment of the ball.
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }
    @Override
    public void timePassed() {
        moveOneStep();
    }
    /**
     * Add this paddle to the game.
     * @param g the game that the ball is add to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

}