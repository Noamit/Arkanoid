package geometry;
/**
 * @author Noa Amit <noa1amit2@gmail.com>.
 * @version 1.0
 * @since 7.6.2021
 */
public class Line {
    /**
     * the class creates a line from two points.
     * there are few methods that checks if teo lines are intersecting and where.
     * there is also a method that checks if two lines are equal and the length of the line.
     */
    //CONSTANT PARAMETER
    static final int ZERO = 0;
    //Fields
    private final Point start;
    private final Point end;
    private final Point realStart;
    private final Point realEnd;
    private final double incline;
    private final double b;
    private final boolean noIncline;

    /** Contractor.
     * @param start - the start point of the line.
     * @param end - the end point of the line.
     */
    public Line(Point start, Point end) {
        if (start.getY() > end.getY()) {
            this.end = start;
            this.start = end;
        } else if (start.getY() < end.getY()) {
            this.end = end;
            this.start = start;
        } else {
            if (start.getX() >= end.getX()) {
                this.end = start;
                this.start = end;
            } else {
                this.end = end;
                this.start = start;
            }
        }
        this.realEnd = end;
        this.realStart = start;
        noIncline = this.start.getX() == this.end.getX();
        this.incline = calculateIncline();
        this.b = calculateB();
    }
    /** Contractor.
     * @param x1 - the x coordinate start point of the line.
     * @param y1 - the y coordinate start point of the line.
     * @param x2 - the x coordinate end point of the line.
     * @param y2 - the y coordinate end point of the line.
     */

    public Line(double x1, double y1, double x2, double y2) {
        if (y2 > y1) {
            this.start = new Point(x1, y1);
            this.end = new Point(x2, y2);
        } else if (y2 < y1) {
            this.start = new Point(x2, y2);
            this.end = new Point(x1, y1);
        } else {
            if (x1 >= x2) {
                this.start = new Point(x2, y2);
                this.end = new Point(x1, y1);
            } else {
                this.start = new Point(x1, y1);
                this.end = new Point(x2, y2);
            }
        }
        this.realStart = new Point(x1, y1);
        this.realEnd = new Point(x2, y2);
        noIncline = this.start.getX() == this.end.getX();
        this.incline = calculateIncline();
        this.b = calculateB();
    }
    /** accessor.
     * @return the lenght of the line.
     */
    public double length() {
        return start.distance(end);
    }
    /** accessor.
     * @return the middle point of the line.
     */
    public Point middle() {
        double midX = (start.getX() + end.getX()) / 2;
        double midY = (start.getY() + end.getY()) / 2;
        return new Point(midX, midY);
    }
    /** accessor.
     * @return the start point of the line
     */
    public Point start() {
        return this.realStart;
    }
    /** accessor.
     * @return the end point of the line
     */
    public Point end() {
        return this.realEnd;
    }
    /** accessor.
     * @return the incline of the line
     */
    public double incline() {
        return this.incline;
    }
    /** accessor.
     * @return true if there is no incline to the line and false otherwise.
     */
    public boolean noIncline() {
        return this.noIncline;
    }
    /** accessor.
     * @return the b value in the equation of a line
     */
    public double b() {
        return this.b;
    }
    /** ccalculateIncline - calculate the inclines of the.
     * @return the inclines of the.
     */
    public double calculateIncline() {
        if (this.start.getX() == this.end.getX()) {
            return 0;
        }
        return ((this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX()));
    }
    /** calculateB - calculate the 'b' in y=mx+b.
     * @return the 'b' in y=mx+b.
     */
    public double calculateB() {
        if (noIncline) {
            return this.start.getX();
        }
        if (this.incline == ZERO) {
            return this.start.getY();
        }
        return (this.start.getY() - (this.incline * this.start.getX()));
    }
    /** isIntersecting-check if to lines are intersecting.
     * @param other - the other line which is compared to this line.
     * @return true if intersecting and false otherwise.
     */
    public boolean isIntersecting(Line other) {
        //there is no incline for both lines
        if (this.noIncline == other.noIncline && this.noIncline) {
            return checkEqualIncline(other.start, other.end, other.b);
        }
        //there is an incline for both lines(same incline)
        if (this.incline == other.incline && !this.noIncline && !other.noIncline) {
            return checkEqualIncline(other.start, other.end, other.b);
        }
        double interX;
        double interY;
        // if ONLY one line has no incline - check if the point is on the not infinite lines
        if (this.noIncline || other.noIncline) {
            if (this.noIncline) {
                interX = this.start.getX();
                interY = (other.incline * interX) + other.b;
                return (checkPointIsExist(other, interX)) && (checkPointIsExistNoIncline(this, interY));
            } else {
                interX = other.start.getX();
                interY = (this.incline * interX) + this.b;
                return (checkPointIsExist(this, interX)) && (checkPointIsExistNoIncline(other, interY));
            }
        }
        interX = (other.b - this.b) / (this.incline - other.incline);
        return (checkPointIsExist(this, interX)) && (checkPointIsExist(other, interX));
    }
    /** checkEqualIncline-check if to lines with the same incline or without incline are intersecting.
     * @param otherStart - the start point of the other line.
     * @param otherEnd - the end point of the other line.
     * @param bOther - the 'b' in y=mx+b of the other line.
     * @return true if intersecting and false otherwise.
     */
    public boolean checkEqualIncline(Point otherStart, Point otherEnd, double bOther) {
        if (this.b != bOther) {
            return false;
        }
        //if the lines are points
        if ((otherEnd.equals(otherStart) && this.end.equals(this.start))) {
            return otherEnd.equals(this.end);
        }
        //if one of the lines is a point
        if ((otherEnd.equals(otherStart) && !this.end.equals(this.start))) {
            return (otherEnd.getY() >= this.start.getY()) && (otherEnd.getY() <= this.end.getY());
        }
        if ((!otherEnd.equals(otherStart) && this.end.equals(this.start))) {
            return (this.start.getY() >= otherStart.getY()) && (this.end.getY() <= otherEnd.getY());
        }
        if ((otherStart.equals(this.end))) {
            return true;
        } else if (otherEnd.equals(this.start)) {
            return true;
        }
        return false;
    }

    /** checkPointIsExist-checks if point is on an equation.
     * @param l1 - The line on which to check if the intersection point exists on it.
     * @param interX - the X coordinate of the intersection point between two lines.
     * @return true if exist and false otherwise.
     */
    public boolean checkPointIsExist(Line l1, double interX) {
        double minX = Math.min(l1.start.getX(), l1.end.getX());
        double maxX = Math.max(l1.start.getX(), l1.end.getX());
        double minY = Math.min(l1.start.getY(), l1.end.getY());
        double maxY = Math.max(l1.start.getY(), l1.end.getY());
        double interY = (l1.incline * interX) + l1.b;
        return ((interX >= minX && interX <= maxX) && (interY >= minY && interY <= maxY));
    }

    /** checkPointIsExist-checks if point is on an equation with no incline.
     * @param l1 - The line on which to check if the intersection point exists on it.
     * @param interY - the Y coordinate of the intersection point between two lines.
     * @return true if exist and false otherwise.
     */
    public boolean checkPointIsExistNoIncline(Line l1, double interY) {
        double minY = Math.min(l1.start.getY(), l1.end.getY());
        double maxY = Math.max(l1.start.getY(), l1.end.getY());
        return (interY >= minY && interY <= maxY);
    }

    /** intersectionWith - calculate the intersection point between two lines.
     * @param other - The line on which to check if the intersection point exists on it.
     * @return the intersection point if the lines intersect and null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (isIntersecting(other)) {
            //two lines has no incline.
            if (this.noIncline && other.noIncline) {
                return intersectionIfNoIncline(other);
            }
            //two lines has the same incline.
            if ((this.incline == other.incline) && (!this.noIncline && !other.noIncline)) {
                return intersectionIfSameIncline(other);
            }
            double interX;
            double interY;
            // if ONLY one line has no incline - check if the point is on the not infinite lines
            if (this.noIncline || other.noIncline) {
                if (this.noIncline) {
                    interX = this.start.getX();
                    interY = (other.incline * interX) + other.b;
                    return new Point(interX, interY);
                } else {
                    interX = other.start.getX();
                    interY = (this.incline * interX) + this.b;
                    return new Point(interX, interY);
                }
            }
            interX = (other.b - this.b) / (this.incline - other.incline);
            interY = (this.incline * interX) + this.b;
            return new Point(interX, interY);
        }
        return null;
    }
    /** intersectionIfSameIncline - calculate the intersection point between two lines with the same incline.
     * @param other - The line on which to check if the intersection point exists on it.
     * @return the intersection point if the lines intersect and null otherwise.
     */
    public Point intersectionIfSameIncline(Line other) {
        double minThisX = Math.min(this.start.getX(), this.end.getX());
        double maxThisX = Math.max(this.start.getX(), this.end.getX());
        double minOtherX = Math.min(other.start.getX(), other.end.getX());
        double maxOtherX = Math.max(other.start.getX(), other.end.getX());

        if (minOtherX == maxThisX) {
            double interY = (this.incline * minOtherX) + this.b;
            return new Point(minOtherX, interY);
        } else if (minThisX == maxOtherX) {
            double interY = (this.incline * minThisX) + this.b;
            return new Point(minThisX, interY);
        }
        return null;
    }

    /** intersectionIfNoIncline - calculate the intersection point between two lines with the no incline.
     * @param other - The line on which to check if the intersection point exists on it.
     * @return the intersection point if the lines intersect and null otherwise.
     */
    public Point intersectionIfNoIncline(Line other) {

        if (this.start.equals(other.end)) {
            return this.start;
        } else if (other.start.equals(this.end)) {
            return other.start;
        }
        if ((other.end.equals(other.start) && !this.end.equals(this.start))) {
            return other.end;
        }
        if ((!other.end.equals(other.start) && this.end.equals(this.start))) {
            return this.start;
        }
        double minThisY = Math.min(this.start.getY(), this.end.getY());
        double maxThisY = Math.max(this.start.getY(), this.end.getY());
        double minOtherY = Math.min(other.start.getY(), other.end.getY());
        double maxOtherY = Math.max(other.start.getY(), other.end.getY());

        double interX = this.start.getX();
        if (minOtherY == maxThisY) {
            return new Point(interX, maxThisY);
        } else if (minThisY == maxOtherY) {
            return new Point(interX, minThisY);
        }
        return null;
    }

    /** equals - the method checks if two lines are equal.
     * @param other - The line that we check if is equal to this line.
     * @return true if equal and false otherwise.
     */
    public boolean equals(Line other) {
        return ((this.start.equals(other.start)) && (this.end.equals(other.end)))
                || ((this.start.equals(other.end)) && (this.end.equals(other.start)));
    }

    /**
     * closestIntersectionToStartOfLine check the closest intersection point of the line and thr rectangle.
     * @param rect - the rectangle that is checked.
     * @return the closest intersection point of the line and thr rectangle. the function return null if there is no
     * intersection point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        if (rect.intersectionPoints(this).size() == 0) {
            return null;
        }
        Point minPoint = rect.intersectionPoints(this).get(0);
        double minDistacne = this.realStart.distance(rect.intersectionPoints(this).get(0));

        for (int i = 1; i < rect.intersectionPoints(this).size(); i++) {
            double temp = this.realStart.distance(rect.intersectionPoints(this).get(i));
            if (temp < minDistacne) {
                minDistacne = temp;
                minPoint = rect.intersectionPoints(this).get(i);
                minPoint.setRectangle(rect);
            }
        }
        return minPoint;
    }
}