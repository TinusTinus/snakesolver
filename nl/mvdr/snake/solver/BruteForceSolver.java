package nl.mvdr.snake.solver;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nl.mvdr.snake.model.Snake;
import nl.mvdr.snake.model.TurnDirection;
import nl.mvdr.snake.util.Primes;

/**
 * Creates a prime snake using brute force methods.
 * 
 * @author Martijn van de Rijdt
 */
public class BruteForceSolver implements Supplier<Snake> {
    /** Intended length of the snake. */
    private final int length;
    
    /**
     * Constructor.
     * 
     * @param length intended length for the snake
     */
    public BruteForceSolver(int length) {
        super();
        
        if (length < 2) {
            throw new IllegalArgumentException("Length must be at least 2, was " + length);
        }
        
        this.length = length;
    }
    
    /** {@inheritDoc} */
    @Override
    public Snake get() {
        // solutions are symmetrical in the first turn; just pick a direction
        Snake startSnake = new Snake()
            .step().get()
            .step().get()
            .turn(TurnDirection.LEFT);
        Set<Snake> results = Collections.singleton(startSnake);
        
        List<Integer> primeGaps = Primes.sieveGaps(length);
        
        int stepsTaken = 2;
        for (int i = 1; i < primeGaps.size(); i++) {

            int stepsUntilNextTurn = primeGaps.get(i);
            
            results = results.parallelStream()
                    .map(snake -> snake.step(stepsUntilNextTurn))
                    .filter(optional -> optional.isPresent())
                    .map(Optional::get)
                    .flatMap(snake -> Stream.of(snake.turn(TurnDirection.LEFT), snake.turn(TurnDirection.RIGHT)))
                    .collect(Collectors.toSet());
            
            System.out.println("Intermediate results: " + results.size());
            
            stepsTaken += stepsUntilNextTurn;
        }
        
        int stepsLeft = length - stepsTaken;

        // Take the final steps to create a snake of the desired total length, and find one with the lowest score
        return results.parallelStream()
                .map(snake -> snake.step(stepsLeft))
                .filter(optional -> optional.isPresent())
                .map(Optional::get)
                .min((left, right) -> Integer.compare(left.getScore(), right.getScore()))
                .get();
    }
}
