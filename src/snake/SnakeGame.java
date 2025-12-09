package snake;

import java.awt.Color;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.TextAlignment;
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

    private double moveTimer = 0;
    private double updateInterval = 0.2;

    private boolean snakeGrowsNextMove = false;
    private boolean gameOver = false;

    public SnakeGame() {
        canvas = new CanvasWindow("SNAKE", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(Color.BLUE);

        gridManager = new GridManager(canvas);
        snake = new Snake(9, 9);

        setupElements();

        apple = new Apple();
        apple.respawn();
        canvas.add(apple.getGraphics());

        setupKeyListener();

        canvas.animate(dt -> {
            update(dt);
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
        if(gameOver) {
            return; // todo: add game over logic
        }

        moveTimer += dt;
        if(moveTimer > updateInterval) {
            moveSnake();
            moveTimer = 0;
            // updateInterval *= 0.95;
        }
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

    private void moveSnake() {
        snake.move(snakeGrowsNextMove, canvas);

        snakeGrowsNextMove = false;

        // wall collision
        collidedWithWall();
        // self collision
        collidedWithSnake();
        // food collision
    }

    private void collidedWithWall() {
        int headX = snake.getSegments().get(0).getX();
        int headY = snake.getSegments().get(0).getY();

        if(headX < 0 || headX >= 19 || 
            headY < 0 || headY >= 19) {
                gameOver();
            }
    }

    private void collidedWithSnake() {
        if(snake.collidedWithSelf()) {
            gameOver();
        }
    }

    private void gameOver() {
        gameOver = true;

        GraphicsText text = new GraphicsText("GAME OVER!\nSCORE - " + snake.getSegments().size());
        text.setCenter(canvas.getWidth() / 2, canvas.getHeight() / 2);
        text.setFillColor(Color.RED);
        text.setFilled(true);
        text.setAlignment(TextAlignment.CENTER);
        text.setFontSize(72);
        canvas.add(text);
    }
}
