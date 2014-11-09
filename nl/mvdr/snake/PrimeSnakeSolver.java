package nl.mvdr.snake;

import nl.mvdr.snake.model.Snake;
import nl.mvdr.snake.solver.PrimeSnakeFactory;
import nl.mvdr.snake.ui.SnakeVisualiser;

/** Main class. */
public class PrimeSnakeSolver {
    /**
     * Main method.
     * 
     * @param args command line arguments; these are ignored
     */
    public static void main(String[] args) {
        PrimeSnakeFactory factory = new PrimeSnakeFactory();

        Snake snake = factory.create("LRRLRRLRLRRLRRLRLLRLLRRLL", 100);

        SnakeVisualiser.show(snake);
    }
}
