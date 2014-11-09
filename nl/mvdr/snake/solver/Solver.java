package nl.mvdr.snake.solver;

import java.util.ArrayList;
import java.util.List;

import nl.mvdr.snake.model.Snake;
import nl.mvdr.snake.model.TurnDirection;

/** Main class. */
public class Solver {
    /**
     * Applies the given solution to solve a snake.
     * 
     * @param solution solution
     * @param snakeLength number of steps to be taken
     * @return solved snake
     */
    public Snake solve(String solution, int snakeLength) {

        Snake snake = new Snake();
        
        char[] input = solution.toCharArray();
        List<Integer> primeGaps = sieveGaps(snakeLength);

        // Validate length:
        if (input.length != primeGaps.size()) {
            throw new IllegalArgumentException("Size of steps doesn't match, " + primeGaps.size()
                    + " steps expected but " + input.length + " received (" + solution + ")");
        }

        // Apply input solution to the snake:
        int stepsTaken = 0;
        for (int i = 0; i < input.length; i++) {

            int stepsUntilNextTurn = primeGaps.get(i);
            snake = snake.step(stepsUntilNextTurn);
            snake = snake.turn(TurnDirection.fromChar(input[i]));

            stepsTaken += stepsUntilNextTurn;
        }

        // Take the final steps to create a snake of the desired total length:
        snake = snake.step(snakeLength - stepsTaken);

        // Print the snake:
        System.out.println("Smallest bounding square/score: " + snake.computeScore());
        
        return snake;
    }

    /**
     * Sieve all the primes up to the given number and return all the gaps.
     * 
     * @param n upper limit
     * @return gaps
     */
    private List<Integer> sieveGaps(int n) {

        // Sieve of Eratosthenes
        boolean[] isPrime = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i; i * j <= n; j++) {
                    isPrime[i * j] = false;
                }
            }
        }

        // Return the gaps:
        List<Integer> gaps = new ArrayList<Integer>();
        int lastPrime = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                gaps.add(i - lastPrime);
                lastPrime = i;
            }
        }
        return gaps;
    }

}
