package nl.mvdr.snake.model;

import nl.mvdr.snake.util.Logging;

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
    
    /**
     * Turns in the given direction.
     * 
     * @param turnDirection turn direction
     * @return new direction
     */
    public Direction turn(TurnDirection turnDirection) {
        if (Logging.DEBUG) {
            System.out.println("Turn " + turnDirection);
        }
        
        Direction result;
        if (turnDirection == TurnDirection.LEFT) {
            result = turnLeft();
        } else if (turnDirection == TurnDirection.RIGHT) {
            result = turnRight();
        } else {
            throw new IllegalArgumentException("Unexpected direction: " + turnDirection);
        }
        return result;
    }
    
    /** @return the next orientation if rotated clockwise */
    private Direction turnRight() {
        Direction[] values = values();
        return values[(ordinal() + 1) % values.length];
    }

    /** @return the next orientation if rotated counter-clockwise */
    private Direction turnLeft() {
        Direction[] values = values();
        return values[(values.length + ordinal() - 1) % values.length];
    }
}
