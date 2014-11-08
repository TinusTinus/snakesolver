package nl.mvdr.snake;

import java.util.ArrayList;
import java.util.List;

/** Store the state of the snake. */
class Snake {
    /** Indicates whether debug logging is enabled. */
    private final boolean debug;

    private final int LEFT = -1;
	private final int RIGHT = 1;
	
	private Coordinate[] DIRECTIONS = new Coordinate[] {
			new Coordinate(0, -1), // North 
			new Coordinate(1, 0),  // East
			new Coordinate(0, 1),  // South
			new Coordinate(-1, 0)  // West
	};

	// Our current heading (pointer into DIRECTIONS array), start going north
	private int currentHeading = 0;
	
	// Our current location:
	private Coordinate currentLocation = new Coordinate(0, 0);
	
	// All the previously visited locations:
	List<Coordinate> allLocations = new ArrayList<Coordinate>();

	public Snake(boolean debug) {
	    this.debug = debug;
	    
        //Add initial position:
		allLocations.add(currentLocation);
		
		if(debug) {
			System.out.println(currentLocation + " <- start");
		}
	}

	/**
	 * Take N steps in the current direction
	 */
	void step(int length) {
		if(debug) {
			System.out.println("Take steps: " + length);
		}
		
		for (int i = 0; i < length; i++) {
			
			// New location:
			currentLocation = new Coordinate(
					currentLocation.x + DIRECTIONS[currentHeading].x,
					currentLocation.y + DIRECTIONS[currentHeading].y);
			
			if(debug) {
				System.out.println(currentLocation);
			}
			
			// Check if there is a crossing (slow method, going through a list)
			if (allLocations.contains(currentLocation)) {
				if(debug) {
					System.out.println("Oh no, a crossing!");
					System.out.println("This is the path: ");
					System.out.println(allLocations);
				}
				throw new IllegalArgumentException("Crossing detected at: "
						+ currentLocation + " after "
						+ allLocations.size() + " steps");
			}
			allLocations.add(currentLocation);
		}
	}

	/**
	 * Turn the snake [L]eft or [R]ight
	 * 
	 * @param L or R
	 */
	void turn(char direction) {
		if(debug) {
			System.out.println("Turn " + direction);
		}
		if (direction == 'L') {
			currentHeading = (4 + (currentHeading + LEFT)) % 4;
		} else {
			currentHeading = (currentHeading + RIGHT) % 4;
		}
	}
}