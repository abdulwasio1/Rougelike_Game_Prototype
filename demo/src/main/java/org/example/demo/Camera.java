package org.example.demo;

public class Camera {

    private final int width;
    private final int height;
    private final double zoom;

    private int cameraX;
    private int cameraY;

    public Camera(int width, int height, double v, double zoom) {
        this.width = width;
        this.height = height;
        this.zoom = zoom;
    }

    public void update(Player player) {

        double playerWorldX =
                player.getX() * ((double) width / 10) + ((double) width / 10) / 2.0;

        double playerWorldY =
                player.getY() * ((double) height / 10) + ((double) height / 10) / 2.0;

        double desiredCameraX =
                playerWorldX - width / (zoom * 2);

        double desiredCameraY =
                playerWorldY - height / (zoom * 2);

        double maxCameraX = width - width / zoom;
        double maxCameraY = height - height / zoom;

        cameraX = (int) Math.max(0,
                Math.min(desiredCameraX, maxCameraX));

        cameraY = (int) Math.max(0,
                Math.min(desiredCameraY, maxCameraY));
    }

    public int getCameraX() {
        return cameraX;
    }

    public int getCameraY() {
        return cameraY;
    }
}