
package org.example.demo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class gameRender {

    private final int width;
    private final int height;
    private final int squareSize;

    public gameRender(int width, int height, int squareSize) {
        this.width = width;
        this.height = height;
        this.squareSize = squareSize;
    }

    public void render(
            GraphicsContext gc,
            GameWorld gameWorld,
            Player player,
            Camera camera,
            Image backgroundImage,
            Image playerImage,
            Image blockImage
    ) {

        gc.save();

        gc.scale(1.5, 1.5);

        gc.translate(
                -camera.getCameraX(),
                -camera.getCameraY()
        );

        drawBackground(gc, backgroundImage);

        drawItems(gc, gameWorld);

        drawBlock(gc, gameWorld, blockImage);

        drawPlayer(gc, player, playerImage);

        gc.restore();
    }

    private void drawBackground(
            GraphicsContext gc,
            Image backgroundImage
    ) {

        gc.drawImage(
                backgroundImage,
                0,
                0,
                width,
                height
        );
    }

    private void drawBlock(
            GraphicsContext gc,
            GameWorld gameWorld,
            Image blockImage
    ) {

        for (Block block : gameWorld.getBlocks()) {

            gc.drawImage(
                    blockImage,
                    block.x * squareSize,
                    block.y * squareSize,
                    squareSize - 5,
                    squareSize - 5
            );
        }
    }

    private void drawPlayer(
            GraphicsContext gc,
            Player player,
            Image playerImage
    ) {

        gc.drawImage(
                playerImage,
                player.getX() * squareSize,
                player.getY() * squareSize,
                squareSize + 5,
                squareSize + 5
        );
    }

    private void drawItems(
            GraphicsContext gc,
            GameWorld gameWorld
    ) {

        for (GameItem item : gameWorld.getItems()) {

            gc.drawImage(
                    item.img,
                    item.x + 6,
                    item.y + 5,
                    squareSize - 15,
                    squareSize - 15
            );
        }
    }
}
