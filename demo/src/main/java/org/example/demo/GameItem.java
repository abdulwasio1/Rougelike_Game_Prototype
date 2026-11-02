package org.example.demo;

import javafx.scene.image.Image;

public class GameItem {

    ItemType type;
    int x;
    int y;
    Image img;

    public GameItem(ItemType type, int x, int y, Image img) {

        this.type = type;
        this.x = x;
        this.y = y;
        this.img = img;
    }
}
