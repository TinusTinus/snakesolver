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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Point other = (Point) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}