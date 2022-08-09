package gamethings;
import gameobjects.Paddle;
import geometry.Line;
import geometry.Point;
import interfaces.Collidable;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Noa Amit <noa1amit2@gmail.com>.
 * @version 1.0
 * @since 7.6.2021
 */
public class GameEnvironment {
    /**
     * class that holds an array with all the objects in the game that a ball can collide with.
     * the class has a method which return the closest object to the ball.
     */
    //CONSTANT PARAMETERS
    static final int FIRST_PLACE = 0;
    static final int EMPTY = 0;
    //Fields
    private final List<Collidable> collidablesCollection;

    /** Contractor. creates an arrays of collidable objects.
     */
    public GameEnvironment() {
        this.collidablesCollection = new ArrayList<>();
    }
    /**
     * add the given collidable to the environment.
     * @param c - a collidable objects.
     */
    public void addCollidable(Collidable c) {
        this.collidablesCollection.add(c);
    }
    /**
     * getClosestCollision - the function check the closest collision point of the ball and a collidable object.
     * @param trajectory - the line that represent the trajectory of the ball.
     * @return CollisionInfo - the information about the closest collidable object to the ball.
     * If this object will not collide with any of the collidables.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Point> points = new ArrayList<>();
        List<Point> pointsForBlock = new ArrayList<>();
        List<Collidable> collidables = new ArrayList<Collidable>(this.collidablesCollection);

        for (Collidable collidable : collidables) {
            if (collidable.getCollisionRectangle().intersectionPoints(trajectory) != null) {
                points.addAll(collidable.getCollisionRectangle().intersectionPoints(trajectory));
                if (collidable.intersectionPointOfCollide(trajectory) != null) {
                    pointsForBlock.addAll(collidable.intersectionPointOfCollide(trajectory));
                }
            }
        }
        if (points.size() == EMPTY) {
            return null;
        }
        Point minPoint = points.get(FIRST_PLACE);
        Point minPointForBlock = pointsForBlock.get(FIRST_PLACE);
        double minDistacne = trajectory.start().distance(points.get(FIRST_PLACE));

        for (int i = 1; i < points.size(); i++) {
            double temp = trajectory.start().distance(points.get(i));
            if (temp < minDistacne) {
                minDistacne = temp;
                minPoint = points.get(i);
                minPointForBlock = pointsForBlock.get(i);
            }
        }
        if (minPoint.getRectangle().getIsPaddle()) {
            return new CollisionInfo(minPoint, new Paddle(minPoint.getRectangle(), Color.yellow));
        }
        return new CollisionInfo(minPoint, minPointForBlock.getBlock());
    }

    /**
     * getter.
     * @return List<Collidable> - list of all the collidables.
     */
    public List<Collidable> getCollidablesCollection() {
        return this.collidablesCollection;
    }
}

