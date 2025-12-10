package snake;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import edu.macalester.graphics.Ellipse;


/**
 * Represents the apple in the game.
 * Handles its graphics, current location, and logic for respawning
 * in a valid location that does not intersect with the snake.
 */
public class Apple {
    private final Ellipse apple;

    // Coordinates stored in grid units not pixels
    private int appleX;
    private int appleY;
    
    /**
     *  Creates the apple graphic and sets its initial properties 
     */
    public Apple() {
        apple = new Ellipse(0, 0, GridSquare.TILE_SIZE / 2, GridSquare.TILE_SIZE / 2);
        apple.setFillColor(Color.RED);
    }
    /** Handles respawning the apple in a valid location on the canvas
     *  that does not intersect with the snake
     * @param snake The current snake object to avoid when respawning
     */
    public void respawn(Snake snake) {
        Random rand = new Random();

        List<GridSquare> gridSquares = GridManager.getGridSquares();
        GridSquare randGridSquare;

        // Repeatedly choose grid squares until one is found
        // that does not coincide with a snake segment.
        while (true) {
            randGridSquare = gridSquares.get(rand.nextInt(gridSquares.size()));

            int candidateX = randGridSquare.getGridX();
            int candidateY = randGridSquare.getGridY();

            boolean collidesWithSnake = false;
            for (SnakeSegment segment : snake.getSegments()) {
                if (segment.getX() == candidateX && segment.getY() == candidateY) {
                    collidesWithSnake = true;
                    break;
                }
            }

            if (!collidesWithSnake) {
                break;
            }
        }

        this.appleX = randGridSquare.getGridX();
        this.appleY = randGridSquare.getGridY();

        int x = randGridSquare.getGridX() * GridSquare.TILE_SIZE;
        int y = randGridSquare.getGridY() * GridSquare.TILE_SIZE;


        randGridSquare.setHasFood(true);

        apple.setPosition(x + GridSquare.TILE_SIZE / 4, y + GridSquare.TILE_SIZE / 4);
    }

    /**
     * Returns the graphical components of the apple.
     */
    public Ellipse getGraphics() {
        return apple;
    }


    /**
     * Returns Apple’s X position in grid units.
     */
    public int getX() {
        return appleX;
    }

    /**
     * Returns Apple’s Y position in grid units.
     */
    public int getY() {
        return appleY;
    }
}