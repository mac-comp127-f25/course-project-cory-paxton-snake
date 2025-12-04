package snake;

import java.awt.Color;
import edu.macalester.graphics.CanvasWindow;

public class SnakeGame {
    private static final int CANVAS_WIDTH = 570;
    private static final int CANVAS_HEIGHT = 570;

    /*
     start with a 19 x 19 grid for the snake
     since canvas size is 570x570 each grid square is 30px by 30px
    */

    private final CanvasWindow canvas;
    private final GridManager gridManager;

    public SnakeGame() {
        canvas = new CanvasWindow("SNAKE", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(Color.BLUE);

        gridManager = new GridManager(canvas);
        gridManager.createGrid();

        Apple apple = new Apple();
        canvas.add(apple.getGraphics());
    }

    public static void main(String[] args) {
        new SnakeGame();
    }

    
}
