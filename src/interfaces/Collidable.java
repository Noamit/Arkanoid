
package interfaces;
import gameobjects.Ball;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import helpers.Velocity;
/**
 * @author Noa Amit <noa1amit2@gmail.com>.
 * @version 1.0
 * @since 7.6.2021
 */
public interface Collidable {
    /**
     * @return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();
    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param collisionPoint the collision point of the ball with the object.
     * @param currentVelocity the current velocity of the ball before thr collision.
     * @param hitter the ball that makes the hit
     * @return new velocity expected after the hit (based on the force the object inflicted on us).
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * intersectionPointOfCollide - check the intersection points of the collidable object and a line.
     * @param line - the line that we check its intersection points with the collidable object.
     * @return a (possibly empty) List of intersection points with the specified line.
     */
    java.util.List<Point> intersectionPointOfCollide(Line line);
}
