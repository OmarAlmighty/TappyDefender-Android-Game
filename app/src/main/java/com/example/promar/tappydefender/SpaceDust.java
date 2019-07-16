package com.example.promar.tappydefender;

import java.util.Random;

public class SpaceDust {
    private int x, y, speed;
    //Detect dust leaving the screen
    private int maxX, minX,
            maxY, minY;

    public SpaceDust(int screenX, int screenY) {
        maxX = screenX;
        maxY = screenY;
        minX = minY = 0;

        //Set speed between 0 and 9
        Random generator = new Random();
        speed = generator.nextInt(10);

        //Set starting coordinates
        x = generator.nextInt(maxX);
        y = generator.nextInt(maxY);
    }

    public void update(int playerSpeed) {
        //Speed up when the player does
        x -= playerSpeed;
        x -= speed;

        //Respawn space dust
        if (x < 0) {
            x = maxX;
            Random generator = new Random();
            y = generator.nextInt(maxY);
            speed = generator.nextInt(15);
        }
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
}
