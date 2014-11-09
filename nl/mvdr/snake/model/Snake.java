package nl.mvdr.snake.model;

import java.util.HashSet;
import java.util.Optional;
import java.util.OptionalInt;
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
    
    /** Cached score value for this snake. */
    private OptionalInt score;

    /** Constructor. */
    public Snake() {
        super();
        
        currentHeading = Direction.NORTH;
        currentLocation =  new Point(0, 0);
        allLocations = new HashSet<>(1);
        
        // Add initial position:
        allLocations.add(currentLocation);
        
        score = OptionalInt.empty();

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
        
        this.score = OptionalInt.empty();
    }

    /**
     * Take length steps in the current direction.
     * 
     * @param length
     *            number of steps to be taken
     * @param snake after taking the steps, if legal
     */
    public Optional<Snake> step(int length) {
        if (Logging.DEBUG) {
            System.out.println("Take steps: " + length);
        }
        
        Optional<Snake> result = Optional.of(this);

        for (int i = 0; i < length; i++) {
            result = result.flatMap(Snake::step);
        }
        
        return result;
    }

    /**
     * Takes a single step in the current direction.
     * 
     * @param length
     *            number of steps to be taken
     * @return snake after taking the step, if legal
     */
    public Optional<Snake> step() {
        Point nextLocation = currentLocation.move(currentHeading);
        if (Logging.DEBUG) {
            System.out.println(nextLocation);
        }

        Optional<Snake> result;
        if (allLocations.contains(nextLocation)) {
            result = Optional.empty();
        } else {
            Set<Point> nextLocations = new HashSet<>(allLocations);
            nextLocations.add(currentLocation);
            result = Optional.of(new Snake(currentHeading, nextLocation, nextLocations));
        }
        
        return result;
    }
    
    /**
     * Turn the snake left or right.
     * 
     * @param turnDirection direction to turn
     * @return snake with adjusted direction
     */
    public Snake turn(TurnDirection turnDirection) {
        Direction nextHeading = currentHeading.turn(turnDirection);
        return new Snake(nextHeading, currentLocation, allLocations);
    }
    
    /** @return size of the snake's bounding square */
    public int getScore() {
        score = OptionalInt.of(score.orElse(computeScore()));
        return score.getAsInt();
    }
    
    /** @return size of the snake's bounding square */
    private int computeScore() {
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