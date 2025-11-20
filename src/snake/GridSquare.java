package snake;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;

/**
 * Creates each individual grid square
 */
public class GridSquare {
    private static final int width = 30;
    private static final int height = 30;

    private int gridX;  // Local grid coordinate x
    private int gridY;  // Local grid coordinate y

    private Rectangle gridSquare;

    public GridSquare(double x, double y, int gridX, int gridY, Color color, CanvasWindow canvas) {
        gridSquare = new Rectangle(x, y, width, height);
        gridSquare.setFillColor(color);
        gridSquare.setFilled(true);
        gridSquare.setStroked(false);

        this.gridX = gridX;
        this.gridY = gridY;
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
}
