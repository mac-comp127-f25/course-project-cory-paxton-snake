package snake;

import java.awt.Color;
import java.util.List;
import java.util.Random;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;

public class Apple {
    private GraphicsGroup appleGraphics;
    private final Ellipse apple;

    private final Color RED = new Color(255,0,0);
    
    private static final int GRID_SIZE = 19;
    
    public Apple() {
        apple = new Ellipse(0, 0, GridSquare.TILE_SIZE / 2, GridSquare.TILE_SIZE / 2);
        apple.setFillColor(Color.RED);
    }

    public void respawn() {
        Random rand = new Random();

        List<GridSquare> gridSquares = GridManager.getGridSquares();
        System.out.println(gridSquares.size());
        GridSquare randGridSquare = gridSquares.get(rand.nextInt(gridSquares.size()));

        int x = randGridSquare.getGridX() * GridSquare.TILE_SIZE;
        int y = randGridSquare.getGridY() * GridSquare.TILE_SIZE;

        randGridSquare.setHasFood(true);

        apple.setPosition(x + GridSquare.TILE_SIZE / 4, y + GridSquare.TILE_SIZE / 4);
    }

    public Ellipse getGraphics() {
        return apple;
    }

    public int getX() {
        return (int) apple.getX();
    }

    public int getY() {
        return (int) apple.getY();
    }
}