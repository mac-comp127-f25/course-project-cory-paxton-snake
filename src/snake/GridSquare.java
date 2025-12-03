package snake;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;

/**
 * Creates each individual grid square
 */
public class GridSquare {
    static final int TILE_SIZE = 30;

    private int gridX;  // Local grid coordinate x
    private int gridY;  // Local grid coordinate y
    private boolean hasConsumable; // If the grid square has a consumable

    private Rectangle gridSquare;

    public GridSquare(double x, double y, int gridX, int gridY, Color color, CanvasWindow canvas) {
        gridSquare = new Rectangle(x, y, TILE_SIZE, TILE_SIZE);
        gridSquare.setFillColor(color);
        gridSquare.setFilled(true);
        gridSquare.setStroked(false);

        this.gridX = gridX;
        this.gridY = gridY;
        this.hasConsumable = false;
    }

    public Rectangle getGraphics() {
        return gridSquare;
    }

    public int getGridX() {
        return this.gridX;
    }

    public int getGridY() {
        return this.gridY;
    }

    public boolean hasFood() {
        return this.hasConsumable;
    }

    public void setHasFood(boolean food) {
        this.hasConsumable = food;
    } 
}
