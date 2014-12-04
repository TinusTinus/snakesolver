package nl.mvdr.snake.solver;

import java.util.Collections;
import java.util.Comparator;
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
    /** Comparator for increasing score values. */
    private static final Comparator<Snake> SCORE_COMPARATOR = (left, right) -> Integer.compare(left.getScore(), right.getScore());
    
    /**
     * Maximum number of intermediate results. Choosing a higher value means better results, but also longer computation
     * time and more memory use.
     */
    private static final int MAX_INTERMEDIATE_RESULTS = 2_000;
    
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
                    // In order to keep the data set from growing exponentially,
                    // only keep the first MAX_INTERMEDIATE_RESULTS snakes with the lowest scores.
                    // Note that this means this algorithm is not guaranteed to provide a minimal score answer!
                    // The result will be a local minimum.
                    .sorted(SCORE_COMPARATOR)
                    .limit(MAX_INTERMEDIATE_RESULTS)
                    .collect(Collectors.toSet());
            
            stepsTaken += stepsUntilNextTurn;
            
            System.out.println(stepsTaken + " / " + length + " steps");
        }
        
        int stepsLeft = length - stepsTaken;

        // Take the final steps to create a snake of the desired total length, and find one with the lowest score
        return results.parallelStream()
                .map(snake -> snake.step(stepsLeft))
                .filter(optional -> optional.isPresent())
                .map(Optional::get)
                .min(SCORE_COMPARATOR)
                .orElseThrow(() -> new IllegalStateException("Failed to calculate a fitting snake."));
    }
}
