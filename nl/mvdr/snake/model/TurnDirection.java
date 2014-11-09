package nl.mvdr.snake.model;

/**
 * Turn directions.
 * 
 * @author Martijn van de Rijdt
 */
public enum TurnDirection {
    /** Clockwise. */
    LEFT('L'),
    /** Counter-clockwise. */
    RIGHT('R');
    
    /** Character representing this direction in a solution string. */
    private final char character;
    
    /**
     * Constructor.
     * 
     * @param character character
     */
    private TurnDirection(char character) {
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }
}
