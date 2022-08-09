package geometry;
import gameobjects.Block;
/**
 * @author Noa Amit <noa1amit2@gmail.com>.
 * @version 1.0
 * @since 7.6.2021
 */
public class Point {
    /**
     * the class creats a point.
     * there is also a method that checks if two points are equal
     */
    //Fields
    private final double x;
    private final double y;
    private Rectangle rectangle;
    private Block block;

    /** Contractor.
     * @param x - the x coordinate of the center.
     * @param y - the y coordinate of the center.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /** distance - calculate the distance between two points.
     * @param other - The point from which to check distance.
     * @return the distance of this point to the other point
     */
    public double distance(Point other) {
        double sumForDistance = ((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y));
        return Math.sqrt(sumForDistance);
    }
    /** equals - checks if two point are the same.
     * @param other - The point which compared to this point.
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        double epsilon = Math.pow(10, -2);
        return (Math.abs(this.x - other.x) < epsilon) && Math.abs(this.y - other.y) < epsilon;
    }
    /** accessor.
     * @return the x value of this point.
     */
    public double getX() {
        return this.x;
    }
    /** accessor.
     * @return the y value of this point.
     */
    public double getY() {
        return this.y;
    }
    /**
     * setRectangle - set method. will update the rectangle that the point on it.
     * @param r the Rectangle that the point on it.
     */
    public void setRectangle(Rectangle r) {
        this.rectangle = r;
    }
    /**
     * setBlock - set method. will update the rectangle that the point on it.
     * @param b the Block that the point on it.
     */
    public void setBlock(Block b) {
        this.block = b;
    }
    /** accessor.
     * @return the Rectangle that the point on it.
     */
    public Rectangle getRectangle() {
        return this.rectangle;
    }
    /** accessor.
     * @return the Block that the point on it.
     */
    public Block getBlock() {
        return this.block;
    }
}
