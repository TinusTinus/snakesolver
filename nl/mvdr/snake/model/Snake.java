package nl.mvdr.snake.model;

import java.util.ArrayList;
import java.util.List;

import nl.mvdr.snake.util.Logging;

/** Store the state of the snake. */
public class Snake {
    /** Our current heading (pointer into DIRECTIONS array), start going north. */
    private Direction currentHeading = Direction.NORTH;

    /** All the previously visited locations. */
    private List<Point> allLocations = new ArrayList<Point>();

    public List<Point> getAllLocations() {
        return allLocations;
    }

    /** Constructor. */
    public Snake() {
        // Add initial position:
        allLocations.add(new Point(0, 0));

        if (Logging.DEBUG) {
            System.out.println(allLocations.get(0) + " <- start");
        }
    }

    /**
     * Take length steps in the current direction.
     * 
     * @param length
     *            number of steps to be taken
     */
    public void step(int length) {
        if (Logging.DEBUG) {
            System.out.println("Take steps: " + length);
        }

        for (int i = 0; i < length; i++) {
            step();
        }
    }

    /**
     * Takes a single step in the current direction.
     * 
     * @param length
     *            number of steps to be taken
     */
    private void step() {
        Point nextLocation = getCurrentLocation().move(currentHeading);
        if (Logging.DEBUG) {
            System.out.println(nextLocation);
        }

        checkCrossing(nextLocation);

        allLocations.add(nextLocation);
    }

    /** @return the snake head's current location */
    private Point getCurrentLocation() {
        return allLocations.get(allLocations.size() - 1);
    }

    /**
     * Check if the given location overlaps with an existing part of the snake. Note that this method iterates over all
     * of the snake's locations.
     */
    private void checkCrossing(Point location) {
        if (allLocations.contains(location)) {
            if (Logging.DEBUG) {
                System.out.println("Oh no, a crossing!");
                System.out.println("This is the path: ");
                System.out.println(allLocations);
            }
            throw new IllegalStateException("Crossing detected at: " + location + " after " + allLocations.size()
                    + " steps");
        }
    }

    /**
     * Turn the snake left or right.
     * 
     * @param turnDirection
     *            direction to turn
     */
    public void turn(TurnDirection turnDirection) {
        currentHeading = currentHeading.turn(turnDirection);
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
}