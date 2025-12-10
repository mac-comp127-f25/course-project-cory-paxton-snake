package snake;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;

public class GridManager {
    private final Color LIGHT_ORANGE = Color.decode("#ffb38a");
    private final Color DARK_ORANGE = Color.decode("#ff9248");
    private Color color;

    private CanvasWindow canvas;
    private GraphicsGroup grid;
    private static List<GridSquare> gridSquares;

    public GridManager(CanvasWindow canvas) {
        gridSquares = new ArrayList<>();
        this.canvas = canvas;
    }

    /**
     * Creates the grid that the apples and snake will be on
     */
    public void createGrid() {
        grid = new GraphicsGroup();

        int gridWidth = 19;
        int gridHeight = 19;
        int totalGridSquares = gridWidth * gridHeight;

        double x = 0;
        double y = 0;

        int gridX = 0;
        int gridY = 0;

        for(int i = 0; i < totalGridSquares; i++) {
            if(isEven(i)) {
                color = DARK_ORANGE;
            } else {
                color = LIGHT_ORANGE;
            }

            GridSquare gridSquare = new GridSquare(x, y, gridX, gridY, color, canvas);

            gridSquares.add(gridSquare);
            grid.add(gridSquare.getGraphics());

            x += gridSquare.getGraphics().getWidth();
            gridX += 1;

            if(x >= canvas.getWidth()) {
                x = 0;
                gridX = 0;
                y += gridSquare.getGraphics().getHeight();
                gridY += 1;
            }
        }

        canvas.add(grid);
    }

    public static List<GridSquare> getGridSquares() {
        return gridSquares;
    }

    private boolean isEven(int x) {
        if(x % 2 == 0) {
            return true;
        } else {
            return false;
        }
    }
}
