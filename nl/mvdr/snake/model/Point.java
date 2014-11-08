package nl.mvdr.snake.model;

/** A pair of coordinates. */
public class Point {
    /** Horizontal coordinate. */
    private final int x;
    /** Vertical coordinate. */
    private final int y;

    /**
     * Constructor.
     * 
     * @param x horizontal coordinate
     * @param y vertical coordinate
     */
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
    
    /**
     * Moves a single step in the given direction.
     * 
     * @param direction direction to move
     * @return translated point
     */
    public Point move(Direction direction) {
        return new Point (x + direction.getPoint().getX(), 
                y + direction.getPoint().getY());
    }
}