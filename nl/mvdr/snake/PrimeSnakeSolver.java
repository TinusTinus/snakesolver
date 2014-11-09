package nl.mvdr.snake;

import java.util.function.Supplier;

import nl.mvdr.snake.model.Snake;
import nl.mvdr.snake.solver.LimitedBruteForceSolver;
import nl.mvdr.snake.ui.SnakeVisualiser;

/** Main class. */
public class PrimeSnakeSolver {
    /** Snake size. */
    private static final int SNAKE_SIZE = 5_000;
    
    /**
     * Main method.
     * 
     * @param args command line arguments; these are ignored
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        
        Supplier<Snake> solver = new LimitedBruteForceSolver(SNAKE_SIZE);
        
        Snake snake = solver.get();
        
        System.out.println("Solution: " + snake.getSolution());
        System.out.println("Score: " + snake.getScore());
        System.out.println("Time taken: " + Long.valueOf(System.currentTimeMillis() - startTime) + " ms.");
        
        SnakeVisualiser.show(snake);
    }
}
