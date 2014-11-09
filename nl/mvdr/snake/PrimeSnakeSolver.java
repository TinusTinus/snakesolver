package nl.mvdr.snake;

import java.util.function.Supplier;

import nl.mvdr.snake.model.Snake;
import nl.mvdr.snake.solver.BruteForceSolver;
import nl.mvdr.snake.ui.SnakeVisualiser;

/** Main class. */
public class PrimeSnakeSolver {
    /**
     * Main method.
     * 
     * @param args command line arguments; these are ignored
     */
    public static void main(String[] args) {
        Supplier<Snake> solver = new BruteForceSolver(80);
        
        Snake snake = solver.get();
        
        System.out.println("Solution: " + snake.getSolution() + ", score: " + snake.getScore());
        
        SnakeVisualiser.show(snake);
    }
}
