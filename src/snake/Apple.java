package snake;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import edu.macalester.graphics.Ellipse;

public class Apple {
    private final Ellipse apple;

    private int appleX;
    private int appleY;
    
    public Apple() {
        apple = new Ellipse(0, 0, GridSquare.TILE_SIZE / 2, GridSquare.TILE_SIZE / 2);
        apple.setFillColor(Color.RED);
    }

    public void respawn() {
        Random rand = new Random();

        List<GridSquare> gridSquares = GridManager.getGridSquares();
        GridSquare randGridSquare = gridSquares.get(rand.nextInt(gridSquares.size()));

        this.appleX = randGridSquare.getGridX();
        this.appleY = randGridSquare.getGridY();

        int x = randGridSquare.getGridX() * GridSquare.TILE_SIZE;
        int y = randGridSquare.getGridY() * GridSquare.TILE_SIZE;

        randGridSquare.setHasFood(true);

        apple.setPosition(x + GridSquare.TILE_SIZE / 4, y + GridSquare.TILE_SIZE / 4);
    }

    public Ellipse getGraphics() {
        return apple;
    }

    public int getX() {
        return appleX;
    }

    public int getY() {
        return appleY;
    }
}