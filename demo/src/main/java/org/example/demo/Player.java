package org.example.demo;

public class Player {
    private int x;
    private int y;
    private int health;
    private int coins;
    private int energy;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        health = 100;
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void collectItem(ItemType type) {

        switch (type) {

            case COIN:
                coins++;
                break;

            case HEALTH:
                health = Math.min(100, health + 20);
                break;

            case ENERGY:
                energy = Math.min(100, energy + 15);
                break;

            case DAMAGE:
                health = Math.max(0, health - 20);
                break;
        }
    }
    public void takeDamage(int damage) {
        health = Math.max(0, health - damage);
    }
    public void moveTo(int x , int y){
        this.x = x;
        this.y = y;
    }
}

