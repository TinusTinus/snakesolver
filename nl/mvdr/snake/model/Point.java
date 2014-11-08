package nl.mvdr.snake.model;

/** A pair of coordinates. */
public class Point {
    /** X coordinate. */
    private final int x;
    /** Y coordinate. */
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Point)) {
            return false;
        }
        Point other = (Point) obj;
        return other.x == x && other.y == y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}