package snake;

import edu.macalester.graphics.CanvasWindow;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents the snake in the game. Handles movement, growth,
 * direction changes, rendering, and self-collision detection.
 */
public class Snake {
    // Ordered list of snake segments, where index 0 is always the head.
    private List<SnakeSegment> segments;


    /**
     * Possible movement directions for the snake.
     */
    public enum Direction { UP, DOWN, LEFT, RIGHT }

    // Current direction of movement; starts moving right by default.
    private Direction direction = Direction.RIGHT; // default direction

    /**
     * Creates a new snake with a single starting segment
     * located at the provided grid coordinates.
     */
    public Snake(int startX, int startY) {
        segments = new ArrayList<>();

        SnakeSegment startingSegment = new SnakeSegment(startX, startY);

        segments.add(startingSegment);
    }

    /**
     * @return A list of all snake segments in order from head(0) to tail.
     */
    public List<SnakeSegment> getSegments() {
        return segments;
    }

    /**
     * @return The number of snake segments.
     */
    public int getLength() {
        return segments.size();
    }

    /**
     * Updates the snake's movement direction. Prevents reversal
     * into the opposite direction, which would cause instant self-collision.
     */
    public void setDirection(Direction newDirection) {
        // Prevents snake from moving back on itself
        if(direction == Direction.UP && newDirection == Direction.DOWN ||
            direction == Direction.DOWN && newDirection == Direction.UP ||
            direction == Direction.LEFT && newDirection == Direction.RIGHT ||
            direction == Direction.RIGHT && newDirection == Direction.LEFT
        ) {
            return;
        }
        direction = newDirection;
    }

    /**
     * Moves the snake one grid space in its current direction.
     * Adds a new head segment and removes the tail unless the snake grows.
     *
     * @param grow   Whether the snake should increase in length this move.
     * @param canvas The canvas used for adding/removing graphics.
     */
    public void move(boolean grow, CanvasWindow canvas) {
        SnakeSegment head = segments.get(0);
        int newX = head.getX();
        int newY = head.getY();

        switch(direction) {
            case UP:
                newY--;
                break;
            case DOWN:
                newY++;
                break;
            case LEFT:
                newX--;
                break;
            case RIGHT:
                newX++;
                break;
        }

        SnakeSegment newHead = new SnakeSegment(newX, newY);

        canvas.add(newHead.getGraphics());
        segments.add(0, newHead); 

        // Remove tail if not growing
        if(!grow) {
            canvas.remove(segments.get(segments.size() - 1).getGraphics());
            segments.remove(segments.size() - 1);
        } 
    }

    /**
     * Checks whether the snake's head overlaps any other segment,
     * which indicates self-collision and should end the game.
     *
     * @return true if the snake collides with itself.
     */
    public boolean collidedWithSelf() {
        int headX = segments.get(0).getX();
        int headY = segments.get(0).getY();

        for(int i = 1; i < segments.size(); i++) {
            if(segments.get(i).getX() == headX && segments.get(i).getY() == headY) {
                return true;
            }
        }

        return false;
    }
}
