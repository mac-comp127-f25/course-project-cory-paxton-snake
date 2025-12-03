package snake;

import java.awt.Color;
import java.util.List;
import java.util.Random;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Ellipse;

public class Apple {
    private GraphicsGroup appleGraphics;
    private final Ellipse apple;

    private final Color RED = new Color(255,0,0);
    
    private static final int GRID_SIZE = 19;
    
    public Apple() {
        appleGraphics = new GraphicsGroup();
        apple = createApple();
        appleGraphics.add(apple);
    }

    private Ellipse createApple() {
        Random rand = new Random();
        int gridX = rand.nextInt(GRID_SIZE);
        int gridY = rand.nextInt(GRID_SIZE);

        List<GridSquare> gridSquares = GridManager.getGridSquares();
        GridSquare randGridSquare = gridSquares.get(rand.nextInt(gridSquares.size() - 1));

        int x = randGridSquare.getGridX() * GridSquare.TILE_SIZE;
        int y = randGridSquare.getGridY() * GridSquare.TILE_SIZE;

        //randGridSquare.setHasFood(true);

        Ellipse apple = new Ellipse(x + GridSquare.TILE_SIZE / 4, y + GridSquare.TILE_SIZE / 4, GridSquare.TILE_SIZE / 2, GridSquare.TILE_SIZE / 2);
        apple.setFillColor(RED);
        return apple;
    }

    public GraphicsGroup getGraphics() {
        return appleGraphics;
    }

}
