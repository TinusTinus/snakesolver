package nl.mvdr.snake.model;

import java.util.HashSet;
import java.util.Set;

import nl.mvdr.snake.util.Logging;

/** Store the state of the snake. */
public class Snake {
    /** Our current heading. */
    private final Direction currentHeading;

    /** Our current location. */
    private final Point currentLocation;

    /** All the previously visited locations. */
    private final Set<Point> allLocations;

    /** Constructor. */
    public Snake() {
        super();
        
        currentHeading = Direction.NORTH;
        currentLocation =  new Point(0, 0);
        allLocations = new HashSet<>(1);
        
        // Add initial position:
        allLocations.add(currentLocation);

        if (Logging.DEBUG) {
            System.out.println(currentLocation + " <- start");
        }
    }

    /**
     * Constructor.
     * 
     * @param currentHeading current heading
     * @param currentLocation current location
     * @param allLocations all locations
     */
    private Snake(Direction currentHeading, Point currentLocation, Set<Point> allLocations) {
        super();
        this.currentHeading = currentHeading;
        this.currentLocation = currentLocation;
        this.allLocations = allLocations;
    }

    /**
     * Take length steps in the current direction.
     * 
     * @param length
     *            number of steps to be taken
     * @param snake after taking the steps
     */
    public Snake step(int length) {
        if (Logging.DEBUG) {
            System.out.println("Take steps: " + length);
        }
        
        Snake result = this;

        for (int i = 0; i < length; i++) {
            result = result.step();
        }
        
        return result;
    }

    /**
     * Takes a single step in the current direction.
     * 
     * @param length
     *            number of steps to be taken
     */
    private Snake step() {
        Point nextLocation = currentLocation.move(currentHeading);
        if (Logging.DEBUG) {
            System.out.println(nextLocation);
        }

        checkCrossing(nextLocation);
        
        Set<Point> nextLocations = new HashSet<>(allLocations);
        nextLocations.add(currentLocation);
        
        return new Snake(currentHeading, nextLocation, nextLocations);
    }

    /**
     * Check if the given location overlaps with another part of the snake.
     * 
     * @param location location to check
     */
    private void checkCrossing(Point location) {
        if (allLocations.contains(location)) {
            if (Logging.DEBUG) {
                System.out.println("Oh no, a crossing!");
                System.out.println("This is the path: ");
                System.out.println(allLocations);
            }
            throw new IllegalStateException("Crossing detected at: " + location + " after "
                    + allLocations.size() + " steps");
        }
    }

    /**
     * Turn the snake left or right.
     * 
     * @param turnDirection direction to turn
     */
    public Snake turn(TurnDirection turnDirection) {
        Direction nextHeading = currentHeading.turn(turnDirection);
        return new Snake(nextHeading, currentLocation, allLocations);
    }
    
    /** @return size of the snake's bounding square */
    public int computeScore() {
        int xmin = 0, ymin = 0, xmax = 0, ymax = 0;
        for (Point coordinate : allLocations) {
            xmax = Math.max(xmax, coordinate.getX());
            xmin = Math.min(xmin, coordinate.getX());
            ymax = Math.max(ymax, coordinate.getY());
            ymin = Math.min(ymin, coordinate.getY());
        }
        return Math.max(xmax - xmin, ymax - ymin);
    }
    
    public Set<Point> getAllLocations() {
        return allLocations;
    }
}