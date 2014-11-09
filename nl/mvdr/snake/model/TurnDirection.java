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
    
    /**
     * Gives the turn direction corresponding to the given character.
     * 
     * @param character L or R
     * @return turn direction
     */
    public static TurnDirection fromChar(char character) {
        TurnDirection result = null;
        
        for (TurnDirection turnDirection: values()) {
            if (turnDirection.character == character) {
                result = turnDirection;
            }
        }
        
        if (result == null) {
            throw new IllegalArgumentException("Expected L or R, was " + character);
        }
        
        return result;
    }

    public char getCharacter() {
        return character;
    }
}
