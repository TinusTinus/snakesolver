package nl.mvdr.snake.model;

/**
 * Directions.
 * 
 * @author Martijn van de Rijdt
 */
public enum Direction {
    /** North. */
    NORTH(new Point(0, -1)),
    /** East. */
    EAST(new Point(1, 0)),
    /** South. */
    SOUTH(new Point(0, 1)),
    /** West. */
    WEST(new Point(-1, 0));
    
    /** Point defining a single step in the given direction. */
    private final Point point;
    
    public Point getPoint() {
        return point;
    }

    /**
     * Constructor.
     * 
     * @param point point defining a single step in the given direction
     */
    private Direction(Point point) {
        this.point = point;
    }
    
    /** @return the next orientation if rotated clockwise */
    public Direction turnRight() {
        Direction[] values = values();
        return values[(ordinal() + 1) % values.length];
    }

    /** @return the next orientation if rotated counter-clockwise */
    public Direction turnLeft() {
        Direction[] values = values();
        return values[(values.length + ordinal() - 1) % values.length];
    }
}
