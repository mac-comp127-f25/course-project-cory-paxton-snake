package snake;

import edu.macalester.graphics.Rectangle;
import java.awt.Color;

/**
 * Represents a single segment of the snake's body. Each segment knows its
 * grid coordinates and maintains a Rectangle graphic for rendering. The
 * Snake class manages segment order and movement; this class only tracks
 * and updates an individual segment's position.
 */
public class SnakeSegment {
    private Rectangle snakeSegment;
    private int gridX;
    private int gridY;

    /**
     * Creates a snake segment at the given grid coordinates.
     *
     * @param gridX GridX column position on grid
     * @param gridY GridY row position on grid
     */
    public SnakeSegment(int gridX, int gridY) {
        this.gridX = gridX;
        this.gridY = gridY;

        snakeSegment = new Rectangle(gridX * GridSquare.TILE_SIZE, gridY * GridSquare.TILE_SIZE, GridSquare.TILE_SIZE, GridSquare.TILE_SIZE);
        snakeSegment.setFillColor(Color.GREEN);
        snakeSegment.setFilled(true);
        snakeSegment.setStroked(false);
    }

    /**
     * @return The Rectangle graphic representing this segment.
     */
    public Rectangle getGraphics() {
        return snakeSegment;
    }

    /**
     * @return The segment's grid column.
     */
    public int getX() {
        return gridX;
    }

    /**
     * @return The segment's grid row.
     */
    public int getY() {
        return gridY;
    }

    /**
     * Updates the segment's grid coordinates and moves its graphic to the
     * corresponding pixel position. This is called when the snake moves.
     *
     * @param newX New grid column
     * @param newY New grid row
     */
    public void moveSnakeSegment(int newX, int newY) {
        this.gridX = newX;
        this.gridY = newY;
        snakeSegment.setPosition(newX * GridSquare.TILE_SIZE, newY * GridSquare.TILE_SIZE);
    }
}


