package snake;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;

/**
 * Represents a single tile on the grid. Each tile stores its grid coordinates,
 * whether it contains food or part of the snake, and the graphic necessary
 * for rendering to the canvas. Other classes use this to reason about
 * positioning and occupancy on the board.
 */
public class GridSquare {
    static final int TILE_SIZE = 30;

    private int gridX;  // Local grid coordinate x
    private int gridY;  // Local grid coordinate y
    private boolean hasConsumable; // If the grid square has a consumable
    private boolean hasSnake; // If the grid square has the snake

    private Rectangle gridSquare;

    /**
     * Constructs a grid tile at a given pixel position and logical grid position.
     * The tile is colored and sized based on the global TILE_SIZE.
     *
     * @param x Pixel x-coordinate
     * @param y Pixel y-coordinate
     * @param gridX Grid column
     * @param gridY Grid row
     * @param color Tile background color
     * @param canvas Canvas reference 
     */
    public GridSquare(double x, double y, int gridX, int gridY, Color color, CanvasWindow canvas) {
        gridSquare = new Rectangle(x, y, TILE_SIZE, TILE_SIZE);
        gridSquare.setFillColor(color);
        gridSquare.setFilled(true);
        gridSquare.setStroked(false);

        this.gridX = gridX;
        this.gridY = gridY;
        this.hasConsumable = false;
        this.hasSnake = false;
    }

     /**
     * @return The Rectangle graphic used for rendering the tile.
     */
    public Rectangle getGraphics() {
        return gridSquare;
    }

    /**
     * @return Tile's column index in the grid.
     */
    public int getGridX() {
        return this.gridX;
    }

    /**
     * @return Tile's row index in the grid.
     */
    public int getGridY() {
        return this.gridY;
    }

     /**
     * @return True if this tile contains a food item.
     */
    public boolean hasFood() {
        return this.hasConsumable;
    }

    /**
     * Sets whether this tile contains food. Used when apples spawn or are eaten.
     */
    public void setHasFood(boolean food) {
        this.hasConsumable = food;
    } 

    /**
     * @return True if a segment of the snake is currently on this tile.
     */
    public boolean hasSnake() {
        return this.hasSnake;
    }
}
