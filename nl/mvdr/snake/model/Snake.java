package nl.mvdr.snake.model;

import java.util.ArrayList;
import java.util.List;

import nl.mvdr.snake.util.Logging;

/** Store the state of the snake. */
public class Snake {
    private final int LEFT = -1;
    private final int RIGHT = 1;

    private Point[] DIRECTIONS = new Point[] {
            new Point(0, -1), // North
            new Point(1, 0), // East
            new Point(0, 1), // South
            new Point(-1, 0) // West
    };

    /** Our current heading (pointer into DIRECTIONS array), start going north. */
    private int currentHeading = 0;

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

            // New location:
            currentLocation = new Point(currentLocation.getX() + DIRECTIONS[currentHeading].getX(),
                    currentLocation.getY() + DIRECTIONS[currentHeading].getY());

            if (Logging.DEBUG) {
                System.out.println(currentLocation);
            }

            // Check if there is a crossing (slow method, going through a list)
            if (allLocations.contains(currentLocation)) {
                if (Logging.DEBUG) {
                    System.out.println("Oh no, a crossing!");
                    System.out.println("This is the path: ");
                    System.out.println(allLocations);
                }
                throw new IllegalArgumentException("Crossing detected at: " + currentLocation + " after "
                        + allLocations.size() + " steps");
            }
            allLocations.add(currentLocation);
        }
    }

    /**
     * Turn the snake [L]eft or [R]ight
     * 
     * @param L
     *            or R
     */
    public void turn(char direction) {
        if (Logging.DEBUG) {
            System.out.println("Turn " + direction);
        }
        if (direction == 'L') {
            currentHeading = (4 + (currentHeading + LEFT)) % 4;
        } else {
            currentHeading = (currentHeading + RIGHT) % 4;
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