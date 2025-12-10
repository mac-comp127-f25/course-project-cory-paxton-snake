package snake;

import java.awt.Color;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.TextAlignment;


/**
 * Main class for the Snake game. Manages the game loop, 
 * input handling, UI screens, and interactions between the snake, 
 * apple, and grid. 
 */
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

    // Tracks elapsed time between automatic snake movements.
    private double moveTimer = 0;

    // Controls game speed: lower values = faster snake.
    private double updateInterval = 0.2;

    private boolean snakeGrowsNextMove = false;
    private boolean gameOver = false;

    /**
     * Initializes the game window, creates starting objects,
     * and displays the introduction screen by calling setupIntroduction(). 
     * The actual game starts when the user clicks anywhere on the window.
     */
    public SnakeGame() {
        canvas = new CanvasWindow("SNAKE", CANVAS_WIDTH, CANVAS_HEIGHT);
        canvas.setBackground(Color.BLACK);

        gridManager = new GridManager(canvas);
        snake = new Snake(9, 9);
        apple = new Apple();

        setupIntroduction();

        canvas.onClick(event -> {
            canvas.removeAll();
            setupElements();
            setupKeyListener();

            canvas.animate(dt -> {
                update(dt);
            });
        });
    }

    public static void main(String[] args) {
        new SnakeGame();
    }

    /**
     * Draws the game's introduction screen, including title,
     * instructions, and credits. This method only handles UI
     * and does not affect the game state.
     */
    private void setupIntroduction() {
        GraphicsText gameTitle = new GraphicsText("SNAKE 🐍");
        gameTitle.setAlignment(TextAlignment.CENTER);
        gameTitle.setFillColor(Color.WHITE);
        gameTitle.setFontSize(50);
        gameTitle.setCenter(canvas.getWidth() / 2, canvas.getHeight() * 0.08);
        canvas.add(gameTitle);

        GraphicsText gameCredits = new GraphicsText("Cory Li, Paxton Boyd");
        gameCredits.setAlignment(TextAlignment.CENTER);
        gameCredits.setFillColor(Color.WHITE);
        gameCredits.setFontSize(25);
        gameCredits.setCenter(canvas.getWidth() / 2, canvas.getHeight() * 0.15);
        canvas.add(gameCredits);

        GraphicsText gameInstructionsTitle = new GraphicsText("HOW TO PLAY");
        gameInstructionsTitle.setAlignment(TextAlignment.LEFT);
        gameInstructionsTitle.setFillColor(Color.WHITE);
        gameInstructionsTitle.setFontSize(20);
        gameInstructionsTitle.setFontStyle(FontStyle.BOLD);
        gameInstructionsTitle.setPosition(canvas.getWidth() * 0.05, canvas.getHeight() * 0.225);
        canvas.add(gameInstructionsTitle);

        GraphicsText gameInstructions = new GraphicsText("• Use the arrow keys to control the snake\n• Avoid running into the walls or into yourself\n• Eat the apples to grow");
        gameInstructions.setAlignment(TextAlignment.LEFT);
        gameInstructions.setFillColor(Color.WHITE);
        gameInstructions.setFontSize(18);
        gameInstructions.setPosition(canvas.getWidth() * 0.05, canvas.getHeight() * 0.275);
        canvas.add(gameInstructions);

        GraphicsText luck = new GraphicsText("Good luck!");
        luck.setAlignment(TextAlignment.CENTER);
        luck.setFillColor(Color.WHITE);
        luck.setFontSize(40);
        luck.setPosition(canvas.getWidth() * 0.5, canvas.getHeight() * 0.85);
        canvas.add(luck);

        GraphicsText begin = new GraphicsText("Click anywhere to begin");
        begin.setAlignment(TextAlignment.CENTER);
        begin.setFillColor(Color.GRAY);
        begin.setFontSize(15);
        begin.setPosition(canvas.getWidth() * 0.5, canvas.getHeight() * 0.95);
        canvas.add(begin);
    }

    /**
     * Draws the game grid, loads the snake graphics, and spawns
     * the first apple. Called once when the game starts.
     */
    private void setupElements() {
        gridManager.createGrid();

        for(SnakeSegment segment : snake.getSegments()) {
            canvas.add(segment.getGraphics());
        }

        apple.respawn(snake);
        canvas.add(apple.getGraphics());
    }


     /**
     * Called during every animation frame.
     * Handles timing for snake movement and freezes the game
     * immediately after a game-over event.
     *
     * @param dt Time elapsed since last frame
     */
    private void update(double dt) {
        if(gameOver) {
            return;
        }

        moveTimer += dt;
        if(moveTimer > updateInterval) {
            moveSnake();
            moveTimer = 0;
        }
    }


    /**
     * Sets up directional input. The snake's direction can change at any time,
     * but movement only actually happens during timed updates.
     */
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

     /**
     * Moves the snake forward one step based on its current direction.
     * Also checks for collisions and sets the grow flag for the next move
     * if the snake has eaten an apple. 
     */
    private void moveSnake() {
        snake.move(snakeGrowsNextMove, canvas);

        snakeGrowsNextMove = false;

        collidedWithWall();
        collidedWithSnake();
        collidedWithFood();

        if(snake.getSegments().size() == gridManager.getGridSquares().size()) {
            gameWin();
        }
    }

    /**
     * Checks whether the snake's head has moved outside the bounds of the grid.
     * If so, triggers a game-over sequence.
     */
    private void collidedWithWall() {
        int headX = snake.getSegments().get(0).getX();
        int headY = snake.getSegments().get(0).getY();

        if(headX < 0 || headX >= 19 || 
            headY < 0 || headY >= 19) {
                gameOver();
            }
    }

    /**
     * Checks whether the snake has run into its own body.
     * The Snake class provides collidedWithSelf(), so this simply
     * relays the result and ends the game if true.
     */
    private void collidedWithSnake() {
        if(snake.collidedWithSelf()) {
            gameOver();
        }
    }

    /**
     * Checks whether the snake's head occupies the same grid square as the apple.
     * If so, marks the snake to grow on the next movement step and respawns the apple.
     */
    private void collidedWithFood() {
        int headX = snake.getSegments().get(0).getX();
        int headY = snake.getSegments().get(0).getY();

        if(headX == apple.getX() && headY == apple.getY()) {
            snakeGrowsNextMove = true;
            apple.respawn(snake);
        }
    }

     /**
     * Ends the game and displays the "Game Over" screen.
     * Shows the player's score and waits for a click to restart.
     */
    private void gameOver() {
        gameOver = true;

        canvas.removeAll();

        GraphicsText text = new GraphicsText("GAME OVER!\nSCORE: " + snake.getSegments().size());
        text.setFillColor(Color.RED);
        text.setFilled(true);
        text.setAlignment(TextAlignment.CENTER);
        text.setFontSize(72);
        text.setCenter(canvas.getWidth() / 2, canvas.getHeight() / 2);
        canvas.add(text);

        GraphicsText playAgain = new GraphicsText("Click anywhere to play again");
        playAgain.setFillColor(Color.WHITE);
        playAgain.setFilled(true);
        playAgain.setFontSize(20);
        playAgain.setCenter(canvas.getWidth() / 2, canvas.getHeight() * 0.95);
        canvas.add(playAgain);

        canvas.onClick(event -> {
            canvas.closeWindow();
            main(null);
        });
    }

     /**
     * Called when the player successfully fills the entire board.
     * Displays a win message and allows the player to restart.
     */
    private void gameWin() {
        gameOver = true;

        canvas.removeAll();

        GraphicsText text = new GraphicsText("YOU WIN!\nSCORE: " + snake.getSegments().size());
        text.setFillColor(Color.YELLOW);
        text.setFilled(true);
        text.setAlignment(TextAlignment.CENTER);
        text.setFontSize(72);
        text.setCenter(canvas.getWidth() / 2, canvas.getHeight() / 2);
        canvas.add(text);

        GraphicsText playAgain = new GraphicsText("Click anywhere to play again");
        playAgain.setFillColor(Color.WHITE);
        playAgain.setFilled(true);
        playAgain.setFontSize(20);
        playAgain.setCenter(canvas.getWidth() / 2, canvas.getHeight() * 0.95);
        canvas.add(playAgain);

        canvas.onClick(event -> {
            canvas.closeWindow();
            main(null);
        });
    }
}
