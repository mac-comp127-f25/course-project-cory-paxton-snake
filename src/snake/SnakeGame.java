package snake;

import java.awt.Color;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.events.Key;

public class SnakeGame {
    private static final int CANVAS_WIDTH = 570;
    private static final int CANVAS_HEIGHT = 570;

    /*
     start with a 19 x 19 grid for the snake
     since canvas size is 570x570 each grid square is 30px by 30px
    */

    private final CanvasWindow canvas;
    private final GridManager gridManager;

    private Snake snake;
    private Apple apple;

    public SnakeGame() {
        canvas = new CanvasWindow("SNAKE", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(Color.BLUE);

        gridManager = new GridManager(canvas);
        snake = new Snake(9, 9);

        apple = new Apple();
        canvas.add(apple.getGraphics());

        setupElements();
        setupKeyListener();

        canvas.animate(() -> {
            snake.move(canvas);
        });
    }

    public static void main(String[] args) {
        new SnakeGame();
    }

    public void setupElements() {
        gridManager.createGrid();

        for(SnakeSegment segment : snake.getSegments()) {
            canvas.add(segment.getGraphics());
        }
    }

    private void update(double dt) {

    }

    private void setupKeyListener() {
        canvas.onKeyDown(event -> {
            switch(event.getKey()) {
                case UP_ARROW:
                    snake.setDirection(Snake.Direction.UP);
                    break;
                case DOWN_ARROW:
                    snake.setDirection(Snake.Direction.DOWN);
                    break;
                case RIGHT_ARROW:
                    snake.setDirection(Snake.Direction.RIGHT);
                    break;
                case LEFT_ARROW:
                    snake.setDirection(Snake.Direction.LEFT);
                    break;
            }
        });
    }
}
