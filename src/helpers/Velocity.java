package helpers;
import geometry.Point;
/**
 * @author Noa Amit <noa1amit2@gmail.com>.
 * @version 1.0
 * @since 7.6.2021
 */
public class Velocity {
    /**
     * the class creats a velocity.
     * there is also a method that creates a new ball from his from its previous position according to its speed.
     * there is an another methos that create a velocity from angel and speed.
     */
    //Fields
    private double dx;
    private double dy;

    /** Contractor.
     * @param dx - the dx parameter of the velocity.
     * @param dy - the dy coordinate of the velocity.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /** accessor.
     * @return the dx parameter of the velocity.
     */
    public double getDx() {
        return this.dx;
    }
    /** accessor.
     * @return the dy parameter of the velocity.
     */
    public double getDy() {
        return this.dy;
    }
    /** set method.
     * change the dx of the velocity.
     * @param newDx - the new dx.
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }
    /** set method.
     * change the dy of the velocity.
     * @param newDy - the new dy.
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }
    /** applyToPoint - Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     * @param p - The point which will be moved to a new point according to the dx,dy parameters.
     * @return a new point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
    /** fromAngleAndSpeed - calculate the dx,dy parameters from angel and apeed.
     * @param angle - The angel parameter of the velocity
     * @param speed - The speed parameter of the velocity
     * @return a new velocity according to the speed and angel.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double angleR = Math.toRadians(angle);
        double dx = speed * Math.sin(angleR);
        // because the coordinates of the y increase as the y decreases
        double dy = speed * -Math.cos(angleR);
        return new Velocity(dx, dy);
    }
}