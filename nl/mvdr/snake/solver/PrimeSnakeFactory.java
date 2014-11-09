package nl.mvdr.snake.solver;

import java.util.List;

import nl.mvdr.snake.model.Snake;
import nl.mvdr.snake.model.TurnDirection;
import nl.mvdr.snake.util.Primes;

/** Factory class for creating a snake based on a solution. */
public class PrimeSnakeFactory {
    /**
     * Applies the given solution to create a snake.
     * 
     * @param solution solution
     * @param snakeLength number of steps to be taken
     * @return solved snake
     */
    public Snake create(String solution, int snakeLength) {

        Snake snake = new Snake();
        
        char[] input = solution.toCharArray();
        List<Integer> primeGaps = Primes.sieveGaps(snakeLength);

        // Validate length:
        if (input.length != primeGaps.size()) {
            throw new IllegalArgumentException("Size of steps doesn't match, " + primeGaps.size()
                    + " steps expected but " + input.length + " received (" + solution + ")");
        }

        // Apply input solution to the snake:
        int stepsTaken = 0;
        for (int i = 0; i < input.length; i++) {

            int stepsUntilNextTurn = primeGaps.get(i);
            snake = snake.step(stepsUntilNextTurn)
                    .orElseThrow(() -> new IllegalArgumentException("Crossing detected."));
            snake = snake.turn(TurnDirection.fromChar(input[i]));

            stepsTaken += stepsUntilNextTurn;
        }

        // Take the final steps to create a snake of the desired total length:
        snake = snake.step(snakeLength - stepsTaken)
                .orElseThrow(() -> new IllegalArgumentException("Crossing detected."));

        // Print the snake:
        System.out.println("Smallest bounding square/score: " + snake.computeScore());
        
        return snake;
    }
}
