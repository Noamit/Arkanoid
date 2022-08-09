package gamethings;
import geometry.Point;
import interfaces.Collidable;
/**
 * @author Noa Amit <noa1amit2@gmail.com>.
 * @version 1.0
 * @since 7.6.2021
 */
public class CollisionInfo {
    /**
     * class that holds the information of the closest object that the ball is about to collide with.
     * the information include the point and the object.
     */
    //Fields
    private final Point pCollision;
    private final Collidable objCollision;

    /** Contractor.
     * @param pCollision - the collision point of the ball and the object.
     * @param objCollision - the object that the ball collide with.
     */
    public CollisionInfo(Point pCollision, Collidable objCollision) {
        this.pCollision = pCollision;
        this.objCollision = objCollision;
    }
    /**
     * the point at which the collision occurs.
     * @return the collision point of the ball and the object.
     */
    public Point collisionPoint() {
        return this.pCollision;
    }
    /**
     * the point at which the collision occurs.
     * @return collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return this.objCollision;
    }
}
