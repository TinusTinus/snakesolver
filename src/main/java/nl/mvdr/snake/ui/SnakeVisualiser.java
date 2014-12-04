package nl.mvdr.snake.ui;

import javax.swing.JFrame;

import nl.mvdr.snake.model.Snake;

/**
 * Visualises a Snake in a JFrame.
 * 
 * @author Martijn van de Rijdt
 */
public class SnakeVisualiser {
    /**
     * Shows the given Snake in a JFrame.
     * 
     * @param snake snake to be shown
     */
    public static void show(Snake snake) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.add(new SnakePanel(snake));
        frame.setVisible(true);
    }
}
