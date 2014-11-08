package nl.mvdr.snake.solver;

import java.util.ArrayList;
import java.util.List;

import nl.mvdr.snake.model.Coordinate;
import nl.mvdr.snake.model.Snake;

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
            snake.step(stepsUntilNextTurn);
            snake.turn(input[i]);

            stepsTaken += stepsUntilNextTurn;
        }

        // Take the final steps to create a snake of the desired total length:
        snake.step(snakeLength - stepsTaken);

        // Calculate the final bounding square:
        int xmin = 0, ymin = 0, xmax = 0, ymax = 0;
        for (Coordinate coordinate : snake.getAllLocations()) {
            xmax = Math.max(xmax, coordinate.getX());
            xmin = Math.min(xmin, coordinate.getX());
            ymax = Math.max(ymax, coordinate.getY());
            ymin = Math.min(ymin, coordinate.getY());
        }

        // Print the snake:
        System.out.println("Smallest bounding square/score: " + Math.max(xmax - xmin, ymax - ymin));
        
        return snake;
    }

    /**
     * Sieve all the primes up to a certain number and return all the gaps.
     * 
     * @return gaps
     */
    private List<Integer> sieveGaps(int N) {

        // Sieve of Eratosthenes
        boolean[] isPrime = new boolean[N + 1];
        for (int i = 2; i <= N; i++) {
            isPrime[i] = true;
        }
        for (int i = 2; i * i <= N; i++) {
            if (isPrime[i]) {
                for (int j = i; i * j <= N; j++) {
                    isPrime[i * j] = false;
                }
            }
        }

        // Return the gaps:
        List<Integer> gaps = new ArrayList<Integer>();
        int lastPrime = 0;
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) {
                gaps.add(i - lastPrime);
                lastPrime = i;
            }
        }
        return gaps;
    }

}
