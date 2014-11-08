package nl.mvdr.snake;

import javax.swing.JFrame;

import nl.mvdr.snake.model.Snake;
import nl.mvdr.snake.solver.Solver;
import nl.mvdr.snake.ui.SnakePanel;

/** Main class. */
public class PrimeSnakeSolver {
    /**
     * Main method.
     * 
     * @param args command line arguments; these are ignored
     */
    public static void main(String[] args) {
        Solver solver = new Solver();

        Snake snake = solver.solve("LRRLRRLRLRRLRRLRLLRLLRRLL", 100);

        // TODO length 5000: solver.solve("?", 5000);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.add(new SnakePanel(snake));
        frame.setVisible(true);
    }
}
