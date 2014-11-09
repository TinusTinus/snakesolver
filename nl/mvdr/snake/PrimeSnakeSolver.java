package nl.mvdr.snake;

import java.util.function.Supplier;

import nl.mvdr.snake.model.Snake;
import nl.mvdr.snake.solver.LimitedBruteForceSolver;
import nl.mvdr.snake.solver.PrimeSnakeFactory;
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
        Supplier<Snake> solver = new LimitedBruteForceSolver(SNAKE_SIZE);
        
        Snake snake = solver.get();
        
        System.out.println("Solution: " + snake.getSolution() + ", score: " + snake.getScore());
        
        // TODO remove the following
        // validate the result
        snake = new PrimeSnakeFactory().create(snake.getSolution(), SNAKE_SIZE);
        
        SnakeVisualiser.show(snake);
    }
}
