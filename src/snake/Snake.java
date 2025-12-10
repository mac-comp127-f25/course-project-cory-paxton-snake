package snake;

import edu.macalester.graphics.CanvasWindow;

import java.util.ArrayList;
import java.util.List;

public class Snake {
    private List<SnakeSegment> segments;

    public enum Direction { UP, DOWN, LEFT, RIGHT }
    private Direction direction = Direction.RIGHT; // default direction

    public Snake(int startX, int startY) {
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

        if(!grow) {
            canvas.remove(segments.get(segments.size() - 1).getGraphics());
            segments.remove(segments.size() - 1);
        } 
    }

    public boolean collidedWithSelf() {
        int headX = segments.get(0).getX();
        int headY = segments.get(0).getY();

        for(int i = 3; i < segments.size(); i++) {
            if(segments.get(i).getX() == headX && segments.get(i).getY() == headY) {
                return true;
            }
        }

        return false;
    }
}
