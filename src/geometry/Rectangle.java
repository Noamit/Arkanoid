package geometry;
import java.util.ArrayList;
import java.util.List;
/**
 * @author Noa Amit <noa1amit2@gmail.com>.
 * @version 1.0
 * @since 7.6.2021
 */
public class Rectangle {
    /**
     * the class creates a rectangle.
     * all the collidable objects in the game have a shape of rectangle.
     * in this class we check the intersection points of every rectangle with the trajectory of the ball.
     */
    //Fields
    private final Point upperLeft;
    private final double width;
    private final double height;
    private Line leftSide;
    private Line rightSide;
    private Line upperLine;
    private Line downLine;
    private final boolean isPaddle;
    /**
     * Contractor.
     * @param upperLeft the upper-left point of the rectangle.
     * @param width the width of the rectangle.
     * @param height the height of the rectangle.
     * @param isPaddle boolean parameter that check if this rectangle shape is own to a paddle.
     */
    public Rectangle(Point upperLeft, double width, double height, boolean isPaddle) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.isPaddle = isPaddle;
        Point upperRight = new Point(this.upperLeft.getX() + this.width, upperLeft.getY());
        Point lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point lowerRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);

        this.setLeftSide(new Line(this.upperLeft, lowerLeft));
        this.setRightSide(new Line(upperRight, lowerRight));
        this.setUpperLine(new Line(this.upperLeft, upperRight));
        this.setDownLine(new Line(lowerLeft, lowerRight));
    }
    /**
     * intersectionPoints - check the intersection points of the rectangle and a line.
     * @param line - the line that we check its intersection points with the rectangle.
     * @return a (possibly empty) List of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {

        List<Point> intersectionPoints = new ArrayList<>();
        if (line.intersectionWith(this.leftSide) != null) {
            Point inter = line.intersectionWith(this.leftSide);
            inter.setRectangle(this);
            intersectionPoints.add(inter);
        }
        if (line.intersectionWith(this.rightSide) != null) {
            Point inter = line.intersectionWith(this.rightSide);
            inter.setRectangle(this);
            intersectionPoints.add(inter);
        }
        if (line.intersectionWith(this.downLine) != null) {
            Point inter = line.intersectionWith(this.downLine);
            inter.setRectangle(this);
            intersectionPoints.add(inter);
        }
        if (line.intersectionWith(this.upperLine) != null) {
            Point inter = line.intersectionWith(this.upperLine);
            inter.setRectangle(this);
            intersectionPoints.add(inter);
        }
        return intersectionPoints;
    }
    /** accessor.
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }
    /** accessor.
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }
    /** accessor.
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /** setLeftSide - set method. will update the rectangle's left side.
     * @param l - the rectangle's left side.
     */
    public void setLeftSide(Line l) {
        this.leftSide = l;
    }
    /** setRightSide - set method. will update the rectangle's right side.
     * @param l - the rectangle's right side.
     */
    public void setRightSide(Line l) {
        this.rightSide = l;
    }
    /** setUpperLine - set method. will update the rectangle's upper line.
     * @param l - the rectangle's upper line.
     */
    public void setUpperLine(Line l) {
        this.upperLine = l;
    }
    /** setDownLine - set method. will update the rectangle's down line.
     * @param l - the rectangle's down line.
     */
    public void setDownLine(Line l) {
        this.downLine = l;
    }
    /** accessor.
     * @return if this rectangle own to the paddle.
     */
    public boolean getIsPaddle() {
        return isPaddle;
    }
    /** accessor.
     * @return the line of the rectangle's left side.
     */
    public Line getLeftSide() {
        return this.leftSide;
    }
    /** accessor.
     * @return the line of the rectangle's right side.
     */
    public Line getRightSide() {
        return this.rightSide;
    }
    /** accessor.
     * @return the line of the rectangle's upper line.
     */
    public Line getUpperLine() {
        return this.upperLine;
    }
    /** accessor.
     * @return the line of the rectangle's down line.
     */
    public Line getDownLine() {
        return this.downLine;
    }

}
