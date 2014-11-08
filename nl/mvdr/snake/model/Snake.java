package nl.mvdr.snake.model;

import java.util.ArrayList;
import java.util.List;

import nl.mvdr.snake.util.Logging;

/** Store the state of the snake. */
public class Snake {
    /** Our current heading (pointer into DIRECTIONS array), start going north. */
    private Direction currentHeading = Direction.NORTH;

    /** Our current location. */
    private Point currentLocation = new Point(0, 0);

    /** All the previously visited locations. */
    private List<Point> allLocations = new ArrayList<Point>();

    public List<Point> getAllLocations() {
        return allLocations;
    }

    /** Constructor. */
    public Snake() {
        // Add initial position:
        allLocations.add(currentLocation);

        if (Logging.DEBUG) {
            System.out.println(currentLocation + " <- start");
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
        currentLocation = currentLocation.move(currentHeading);
        if (Logging.DEBUG) {
            System.out.println(currentLocation);
        }

        checkCrossing();
        
        allLocations.add(currentLocation);
    }

    /**
     * Check if the snake's current location overlaps with another part of the snake. Note that this method iterates
     * over all of the snake's locations.
     */
    private void checkCrossing() {
        if (allLocations.contains(currentLocation)) {
            if (Logging.DEBUG) {
                System.out.println("Oh no, a crossing!");
                System.out.println("This is the path: ");
                System.out.println(allLocations);
            }
            throw new IllegalStateException("Crossing detected at: " + currentLocation + " after "
                    + allLocations.size() + " steps");
        }
    }

    /**
     * Turn the snake [L]eft or [R]ight
     * 
     * @param direction L or R
     */
    public void turn(char direction) {
        if (Logging.DEBUG) {
            System.out.println("Turn " + direction);
        }
        if (direction == 'L') {
            currentHeading = currentHeading.turnLeft();
        } else if (direction == 'R') {
            currentHeading = currentHeading.turnRight();
        } else {
            throw new IllegalArgumentException("Expected L or R, was " + direction);
        }
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