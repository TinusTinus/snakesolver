package nl.mvdr.snake.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import nl.mvdr.snake.model.Coordinate;
import nl.mvdr.snake.model.Snake;

/** Visualisation of a Snake. */
@SuppressWarnings("serial") // no serialisation
public class SnakePanel extends JPanel {
    // Adjust to see more detail
    private static final int ZOOM = 10;
    
    /** Snake to be visualised. */
    private final Snake snake;
    
    public SnakePanel(Snake snake) {
        super();
        this.snake = snake;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        
        // Simple visual for debug purposes, start from the center:
        Graphics2D g2d = (Graphics2D) g;
        
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        int cx = getWidth() / 2;
        int cy = getHeight() / 2;
        
        if(ZOOM > 3) {
            
            //If the zoom is large enough, draw a grid to make it clearer
            cx -= (cx%ZOOM);
            cy -= (cy%ZOOM);
            
            g2d.setColor(Color.GRAY);
            for(int x = 0; x<getWidth();x+=ZOOM) {
                for(int y = 0; y<getHeight();y+=ZOOM) {
                    g2d.drawOval(x-1, y-1, 1, 1);
                }
            }
        }

        g2d.setColor(Color.BLACK);
        for (int i = 0; i < snake.getAllLocations().size() - 1; i++) {
            Coordinate c1 = snake.getAllLocations().get(i);
            Coordinate c2 = snake.getAllLocations().get(i + 1);
            g2d.setStroke(new BasicStroke(4));
            g2d.drawLine(
                    cx + (c1.getX() * ZOOM), 
                    cy + (c1.getY() * ZOOM), 
                    cx + (c2.getX() * ZOOM), 
                    cy + (c2.getY() * ZOOM));
        }

        // Show origin
        g2d.setColor(Color.RED);
        g2d.drawRect(cx - 1, cy - 1, 1, 1);
    }
}
