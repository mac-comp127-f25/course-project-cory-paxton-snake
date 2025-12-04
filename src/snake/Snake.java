package snake;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.Rectangle;
import java.awt.Color;

public class Snake {
    private GraphicsGroup snakeGraphics;
    private Rectangle snake;
    private int length;

    public Snake(int startX, int startY) {
        snakeGraphics = new GraphicsGroup();
        snake = new Rectangle(startX, startY, 30, 30);
        snake.setFillColor(java.awt.Color.GREEN);
        snake.setFilled(true);
        snake.setStroked(false);
        snakeGraphics.add(snake);
        this.length = 1;
    }


    public GraphicsGroup getGraphics() {
        return snakeGraphics;
    }

    public int getLength() {
        return length;
    }

    public void eatApple(Apple apple) {
        if (snake.getX() == apple.getX() && snake.getY() == apple.getY()) {
            grow();
        }
    }

    public void grow() {
        length++;
    }

}
