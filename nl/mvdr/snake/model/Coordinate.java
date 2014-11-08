package nl.mvdr.snake.model;

public class Coordinate {

	private final int x;
    private final int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Coordinate)) {
			return false;
		}
		Coordinate other = (Coordinate) obj;
		return other.x == x && other.y == y;
	}
	
	   public int getX() {
	        return x;
	    }

	    public int getY() {
	        return y;
	    }
}