package nl.mvdr.snake.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import nl.mvdr.snake.model.Snake;

/** Visualisation of a Snake. */
@SuppressWarnings("serial") // no serialisation
public class SnakePanel extends JPanel {
    /** Adjust to see more detail. */
    private static final int ZOOM = 10;

    /** Snake to be visualised. */
    private final Snake snake;

    /**
     * Constructor.
     * 
     * @param snake
     *            snake to be visualised
     */
    public SnakePanel(Snake snake) {
        super();
        this.snake = snake;
    }

    /** {@inheritDoc} */
    @Override
    protected void paintComponent(Graphics g) {

        // Simple visual for debug purposes, start from the center:
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        int halfWidth = getWidth() / 2;
        int halfHeight = getHeight() / 2;
        
        int cx = halfWidth - halfWidth % ZOOM;
        int cy = halfHeight - halfHeight % ZOOM;

        if (ZOOM > 3) {
            // Draw a grid to make it clearer
            g2d.setColor(Color.GRAY);
            for (int x = 0; x < getWidth(); x += ZOOM) {
                for (int y = 0; y < getHeight(); y += ZOOM) {
                    g2d.drawOval(x - 1, y - 1, 1, 1);
                }
            }
        }

        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(4));
        snake.getAllLocations().forEach(point -> 
            g2d.fillRect(cx + point.getX() * ZOOM - ZOOM / 2, cy + point.getY() * ZOOM - ZOOM / 2, ZOOM, ZOOM));
        
        // Show origin
        g2d.setColor(Color.RED);
        g2d.fillRect(cx - ZOOM / 2, cy - ZOOM / 2, ZOOM, ZOOM);
    }
}
