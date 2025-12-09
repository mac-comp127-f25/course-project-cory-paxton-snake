package snake;

import edu.macalester.graphics.Rectangle;
import java.awt.Color;


/**
 * Creates each segment of the snake
 */
public class SnakeSegment {
    private Rectangle snakeSegment;
    private int gridX;
    private int gridY;

    public SnakeSegment(int gridX, int gridY) {
        this.gridX = gridX;
        this.gridY = gridY;

        snakeSegment = new Rectangle(gridX, gridY, GridSquare.TILE_SIZE, GridSquare.TILE_SIZE);
        snakeSegment.setFillColor(Color.GREEN);
        snakeSegment.setFilled(true);
        snakeSegment.setStroked(false);
    }

    public Rectangle getGraphics() {
        return snakeSegment;
    }

    public int getX() {
        return gridX;
    }

    public int getY() {
        return gridY;
    }

    public void moveSnakeSegment(int newX, int newY) {
        this.gridX = newX;
        this.gridY = newY;
        snakeSegment.setPosition(newX * GridSquare.TILE_SIZE, newY * GridSquare.TILE_SIZE);
    }
}


