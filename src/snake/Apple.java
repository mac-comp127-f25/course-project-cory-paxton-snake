package snake;

import java.awt.Color;
import java.util.Random;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Ellipse;

public class Apple {
    private GraphicsGroup appleGraphics;
    private final Ellipse apple;

    private final Color RED = new Color(255,0,0);
    
    private static final int GRID_SIZE = 19;
    private static final int TILE_SIZE = 30;
    
    public Apple() {
        appleGraphics = new GraphicsGroup();
        apple = createApple();
        appleGraphics.add(apple);
    }

    private Ellipse createApple() {
        Random rand = new Random();
        int gridX = rand.nextInt(GRID_SIZE);
        int gridY = rand.nextInt(GRID_SIZE);

        int x = gridX * TILE_SIZE;
        int y = gridY * TILE_SIZE;

        Ellipse apple = new Ellipse(x+TILE_SIZE/4, y+TILE_SIZE/4, TILE_SIZE/2, TILE_SIZE/2);
        apple.setFillColor(RED);
        return apple;
    }

    public GraphicsGroup getGraphics() {
        return appleGraphics;
    }

    public int getX() {
        return (int) apple.getX();
    }

    public int getY() {
        return (int) apple.getY();
    }
    

}
