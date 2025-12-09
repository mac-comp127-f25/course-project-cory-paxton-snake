package snake;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<SnakeSegment> segments;
    private GraphicsGroup snakeSegments;

    public enum Direction { UP, DOWN, LEFT, RIGHT }
    private Direction direction = Direction.RIGHT; // default direction

    public Snake(int startX, int startY) {
        snakeSegments = new GraphicsGroup();
        segments = new ArrayList<>();

        SnakeSegment startingSegment = new SnakeSegment(startX, startY);

        segments.add(startingSegment);
    }

    public List<SnakeSegment> getSegments() {
        return segments;
    }

    public int getLength() {
        return segments.size();
    }

    public void eatApple(Apple apple) {
        // if (snakeSegment.getX() == apple.getX() && snakeSegment.getY() == apple.getY()) {
        //     grow();
        // }
    }

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

    public void move(CanvasWindow canvas) {
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
        canvas.remove(segments.get(segments.size() - 1).getGraphics());
        segments.remove(segments.size() - 1);
        canvas.add(newHead.getGraphics());
        segments.add(0, newHead);       
    }
}
