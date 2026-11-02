package org.example.demo;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameWorld {

    private static final int ROWS = 10;
    private static final int COLUMNS = ROWS;
    private static final int WIDTH = 600;
    private static final int SQUARE_SIZE = WIDTH / COLUMNS;

    private final Player player;
    private final Image blockImage;

    private final ArrayList<GameItem> items = new ArrayList<>();
    private final ArrayList<Block> blocks = new ArrayList<>();

    public GameWorld(Player player, Image blockImage) {
        this.player = player;
        this.blockImage = blockImage;
    }

    public void getCurrentBlock() {

        int numOfBlock = (int) (Math.random() * 10) + 1;

        for (int i = 0; i < numOfBlock; i++) {

            int x;
            int y;
            boolean occupied;

            do {
                x = (int) (Math.random() * COLUMNS);
                y = (int) (Math.random() * ROWS);

                occupied = false;

                if (x == player.getX() &&
                        y == player.getY()) {

                    occupied = true;
                }

                for (Block block : blocks) {

                    if (block.x == x && block.y == y) {
                        occupied = true;
                        break;
                    }
                }

            } while (occupied);

            blocks.add(new Block(blockImage, x, y));
        }
    }

    public void generateItems() {

        for (ItemType type : ItemType.values()) {

            int randomNum = (int) (Math.random() * 4) + 1;

            for (int i = 0; i < randomNum; i++) {
                items.add(generateItem(type));
            }
        }
    }

    public GameItem generateItem(ItemType type) {

        int x;
        int y;
        boolean occupied;

        Image image;

        switch (type) {

            case COIN:
                image = new Image(
                        getClass().getResourceAsStream(
                                "/org/example/demo/img/coin.png"
                        )
                );
                break;

            case HEALTH:
                image = new Image(
                        getClass().getResourceAsStream(
                                "/org/example/demo/img/heart.png"
                        )
                );
                break;

            case ENERGY:
                image = new Image(
                        getClass().getResourceAsStream(
                                "/org/example/demo/img/energy.png"
                        )
                );
                break;

            case DAMAGE:
                image = new Image(
                        getClass().getResourceAsStream(
                                "/org/example/demo/img/damage.png"
                        )
                );
                break;

            default:
                throw new IllegalArgumentException("Unknown item type");
        }

        do {

            x = (int) (Math.random() * COLUMNS) * SQUARE_SIZE;
            y = (int) (Math.random() * ROWS) * SQUARE_SIZE;

            occupied = false;

            for (GameItem item : items) {

                if (item.x == x && item.y == y) {
                    occupied = true;
                    break;
                }
            }

        } while (occupied);

        return new GameItem(type, x, y, image);
    }

    public boolean isBlocked(int x, int y) {

        for (Block block : blocks) {

            if (block.x == x && block.y == y) {
                return true;
            }
        }

        return false;
    }

    public List<GameItem> checkItemsCollected() {

        ArrayList<GameItem> collectedItems = new ArrayList<>();

        Iterator<GameItem> iterator = items.iterator();

        while (iterator.hasNext()) {

            GameItem item = iterator.next();

            double playerX = player.getX() * SQUARE_SIZE;
            double playerY = player.getY() * SQUARE_SIZE;

            if (playerX == item.x && playerY == item.y) {

                collectedItems.add(item);
                iterator.remove();
            }
        }

        return collectedItems;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public ArrayList<GameItem> getItems() {
        return items;
    }
}
