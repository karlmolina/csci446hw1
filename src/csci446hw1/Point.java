
package csci446hw1;

/**
 * Point Class to store two integers x and y
 * Methods exist to get the points up, down, left, and right of this point
 * @author Karl Molina, Jordan Palmer
 */
public class Point {

    private final int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public Point right() {
        return new Point(x + 1, y);
    }

    public Point up() {
        return new Point(x, y - 1);
    }

    public Point left() {
        return new Point(x - 1, y);
    }

    public Point down() {
        return new Point(x, y + 1);
    }

    /**
     * Gets an array of the points surrounding this point
     * @return 
     */
    public Point[] surrounding() {
        return new Point[]{right(), up(), left(), down()};
    }
    
    @Override
    public String toString() {
        return x + ", " + y;
    }
}
