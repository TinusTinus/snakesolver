package nl.mvdr.snake.model;

import java.util.HashSet;
import java.util.Optional;
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
    
    /** Solution. */
    private final String solution;
    
    /** Minimum x coordinate. */
    private final int xmin;
    /** Maximum x coordinate. */
    private final int xmax;
    /** Minimum y coordinate. */
    private final int ymin;
    /** Maximum y coordinate. */
    private final int ymax;
    
    /** Size of the snake's bounding square. */
    private final int score;

    /** Constructor. Creates a snake consisting of a single 'pixel' at (0, 0), with direction NORTH. */
    public Snake() {
        super();
        
        currentHeading = Direction.NORTH;
        currentLocation =  new Point(0, 0);
        allLocations = new HashSet<>(1);
        solution = "";
        
        // Add initial position:
        allLocations.add(currentLocation);
        
        xmin = 0;
        xmax = 0;
        ymin = 0;
        ymax = 0;
        score = 0;

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
     * @param solution solution
     * @param xmin minimum x coordinate
     * @param xmax maxumum x coordinate
     * @param ymin minimum y coordinate
     * @param ymax maximum y coordinate
     */
    private Snake(Direction currentHeading, Point currentLocation, Set<Point> allLocations, String solution, int xmin, int xmax, int ymin, int ymax) {
        super();
        
        this.currentHeading = currentHeading;
        this.currentLocation = currentLocation;
        this.allLocations = allLocations;
        this.solution = solution;
        
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
        this.score = Math.max(xmax - xmin, ymax - ymin);
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
            int nextXmin = Math.min(xmin, nextLocation.getX());
            int nextXmax = Math.max(xmax, nextLocation.getX());
            int nextYmin = Math.min(ymin, nextLocation.getY());
            int nextYmax = Math.max(ymax, nextLocation.getY());
            
            result = Optional.of(new Snake(currentHeading, nextLocation, nextLocations, solution, nextXmin, nextXmax, nextYmin, nextYmax));
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
        String nextSolution = solution + turnDirection.getCharacter();
        return new Snake(nextHeading, currentLocation, allLocations, nextSolution, xmin, xmax, ymin, ymax);
    }
    
    /** @return size of the snake's bounding square */
    public int getScore() {
        return score;
    }
    
    public Set<Point> getAllLocations() {
        return allLocations;
    }

    public String getSolution() {
        return solution;
    }
}