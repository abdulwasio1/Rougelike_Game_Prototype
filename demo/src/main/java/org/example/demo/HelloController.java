package org.example.demo;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class HelloController {

    private static final int WIDTH = 600;
    private static final int HEIGHT = WIDTH;
    private static final int ROWS = 10;
    private static final int COLUMNS = ROWS;
    private static final int SQUARE_SIZE = WIDTH / COLUMNS;

    private static final int RIGHT = 0;
    private static final int LEFT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;

    @FXML
    private Canvas canvas;

    private GraphicsContext gc;

    private Image playerImage;
    private Image backgroundImage;
    private Image blockImage;

    private GameWorld gameWorld;

    private double zoom = 1.5;

    private int cameraX;
    private int cameraY;

    private Player player;

    private int currentDirection = -1;

    @FXML
    private Label healthLabel;

    @FXML
    private Label energyLabel;

    @FXML
    private Label coinsLabel;

    @FXML
    public void initialize() {

        player = new Player(0, 0);

        gc = canvas.getGraphicsContext2D();

        backgroundImage = new Image(
                getClass().getResourceAsStream(
                        "/org/example/demo/img/background.jpg"
                )
        );

        playerImage = new Image(
                getClass().getResourceAsStream(
                        "/org/example/demo/img/character.png"
                )
        );

        blockImage = new Image(
                getClass().getResourceAsStream(
                        "/org/example/demo/img/stone.png"
                )
        );

        gameWorld = new GameWorld(player, blockImage);

        gameWorld.getCurrentBlock();
        gameWorld.generateItems();

        updateHUD();

        run(gc);
    }


    public void setupInput(Scene scene) {

        scene.setOnKeyPressed(event -> {

            KeyCode code = event.getCode();

            if (code == KeyCode.RIGHT) {
                currentDirection = RIGHT;

            } else if (code == KeyCode.LEFT) {
                currentDirection = LEFT;

            } else if (code == KeyCode.UP) {
                currentDirection = UP;

            } else if (code == KeyCode.DOWN) {
                currentDirection = DOWN;
            }

            run(gc);
        });
    }


    public void run(GraphicsContext gc) {

        switch (currentDirection) {

            case UP -> moveUp();

            case DOWN -> moveDown();

            case LEFT -> moveLeft();

            case RIGHT -> moveRight();
        }

        checkItemsCollected();

        updateCamera();

        gc.save();

        gc.scale(1.5, 1.5);

        gc.translate(-cameraX, -cameraY);

        drawBackground(gc);
        drawItems(gc);
        drawBlock(gc);
        drawPlayer(gc);

        gc.restore();
    }

    private void drawBlock(GraphicsContext gc) {

        for (Block block : gameWorld.getBlocks()) {

            gc.drawImage(
                    blockImage,
                    block.x * SQUARE_SIZE,
                    block.y * SQUARE_SIZE,
                    SQUARE_SIZE - 5,
                    SQUARE_SIZE - 5
            );
        }
    }

    public void updateCamera() {

        double playerWorldX =
                player.getX() * SQUARE_SIZE + SQUARE_SIZE / 2.0;

        double playerWorldY =
                player.getY() * SQUARE_SIZE + SQUARE_SIZE / 2.0;

        double desiredCameraX =
                playerWorldX - WIDTH / (zoom * 2);

        double desiredCameraY =
                playerWorldY - HEIGHT / (zoom * 2);

        double maxCameraX = WIDTH - WIDTH / zoom;
        double maxCameraY = HEIGHT - HEIGHT / zoom;

        cameraX = (int) Math.max(
                0,
                Math.min(desiredCameraX, maxCameraX)
        );

        cameraY = (int) Math.max(
                0,
                Math.min(desiredCameraY, maxCameraY)
        );
    }

    public void drawBackground(GraphicsContext gc) {

        gc.drawImage(
                backgroundImage,
                0,
                0,
                WIDTH,
                HEIGHT
        );
    }

    public void drawPlayer(GraphicsContext gc) {

        gc.drawImage(
                playerImage,
                player.getX() * SQUARE_SIZE,
                player.getY() * SQUARE_SIZE,
                SQUARE_SIZE + 5,
                SQUARE_SIZE + 5
        );
    }

    void moveRight() {

        playerImage = new Image(
                getClass().getResourceAsStream(
                        "/org/example/demo/img/character_right.png"
                )
        );

        int x = player.getX() + 1;
        int y = player.getY();

        if (!gameWorld.isBlocked(x, y) && x < COLUMNS) {
            player.moveTo(x, y);
        }
    }

    void moveLeft() {

        playerImage = new Image(
                getClass().getResourceAsStream(
                        "/org/example/demo/img/character_left.png"
                )
        );

        int x = player.getX() - 1;
        int y = player.getY();

        if (!gameWorld.isBlocked(x, y) && x >= 0) {
            player.moveTo(x, y);
        }
    }


    void moveUp() {

        playerImage = new Image(
                getClass().getResourceAsStream(
                        "/org/example/demo/img/character_up.png"
                )
        );

        int x = player.getX();
        int y = player.getY() - 1;

        if (!gameWorld.isBlocked(x, y) && y >= 0) {
            player.moveTo(x, y);
        }
    }


    void moveDown() {

        playerImage = new Image(
                getClass().getResourceAsStream(
                        "/org/example/demo/img/character.png"
                )
        );

        int x = player.getX();
        int y = player.getY() + 1;

        if (!gameWorld.isBlocked(x, y) && y < ROWS) {
            player.moveTo(x, y);
        }
    }

    void drawItems(GraphicsContext gc) {

        for (GameItem item : gameWorld.getItems()) {

            gc.drawImage(
                    item.img,
                    item.x + 6,
                    item.y + 5,
                    SQUARE_SIZE - 15,
                    SQUARE_SIZE - 15
            );
        }
    }

    private void collectItem(GameItem item) {

        player.collectItem(item.type);

        updateHUD();
    }

    private void updateHUD() {

        healthLabel.setText("Health: " + player.getHealth());
        energyLabel.setText("Energy: " + player.getEnergy());
        coinsLabel.setText("Coins: " + player.getCoins());
    }

    private void checkItemsCollected() {

        for (GameItem item : gameWorld.checkItemsCollected()) {

            collectItem(item);
        }
    }
}
