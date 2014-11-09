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
public class LimitedBruteForceSolver implements Supplier<Snake> {
    /** Maximum number of intermediate results. */
    private static final int MAX_INTERMEDIATE_RESULTS = 200;
    
    /** Intended length of the snake. */
    private final int length;
    
    /**
     * Constructor.
     * 
     * @param length intended length for the snake
     */
    public LimitedBruteForceSolver(int length) {
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
            
            if (MAX_INTERMEDIATE_RESULTS < results.size()) {
                // In order to keep the data set under control, ~ halve the set by removing the ones with an above average score.
                // Note that this means this algorithm is not GUARANTEED to provide a minimal score answer.
                
                double averageScore = results.parallelStream()
                        .mapToInt(Snake::getScore)
                        .average()
                        .getAsDouble();
                results = results.parallelStream()
                        .filter(snake -> snake.getScore() <= averageScore)
                        .collect(Collectors.toSet());
            }
            
            stepsTaken += stepsUntilNextTurn;
            
            System.out.println("Intermediate results after " + stepsTaken + " steps: " + results.size());
        }
        
        int stepsLeft = length - stepsTaken;

        // Take the final steps to create a snake of the desired total length, and find one with the lowest score
        return results.parallelStream()
                .map(snake -> snake.step(stepsLeft))
                .filter(optional -> optional.isPresent())
                .map(Optional::get)
                .min((left, right) -> Integer.compare(left.getScore(), right.getScore()))
                .orElseThrow(() -> new IllegalStateException("Failed to calculate a fitting snake."));
    }
}
