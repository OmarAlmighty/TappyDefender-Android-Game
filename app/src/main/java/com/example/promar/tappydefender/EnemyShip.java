package com.example.promar.tappydefender;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

public class EnemyShip {
    private Bitmap bitmap;
    private int x, y;
    private int speed = 1;
    //Detect enemies leaving the screen
    private int minX, maxX;
    //Spawn enemies within screen bounds
    private int minY, maxY;
    // A hit box for collision detection
    private Rect hitBox;

    public EnemyShip(Context context, int screenX, int screenY) {
        Random generator = new Random();
        int whichBitmap = generator.nextInt(3);
        switch (whichBitmap) {
            case 0:
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemyship);
                break;
            case 1:
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemyship2);
            case 2:
                bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemyship3);

        }
        scaleBimap(screenX);
        minY = minX = 0;
        maxX = screenX;
        maxY = screenY;
        speed = generator.nextInt(6) + 10;
        x = screenX;
        y = generator.nextInt(maxY) - bitmap.getHeight();
        //Initialize hitbox
        hitBox = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }

    private void scaleBimap(int x) {
        if (x < 1000) {
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 3,
                    bitmap.getHeight() / 3, false);
        } else if (x < 1200) {
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 2,
                    bitmap.getHeight() / 2, false);

        }
    }

    public void update(int playerSpeed) {
        //Move to the left
        x -= playerSpeed;
        x -= speed;

        //Respawn when off the screen
        if (x < (minX - bitmap.getWidth())) {
            Random generator = new Random();
            speed = generator.nextInt(10) + 10;
            x = maxX;
            y = generator.nextInt(maxY) - bitmap.getHeight();
        }

        //Refresh hitbox location
        hitBox.left = x;
        hitBox.top = y;
        hitBox.right = x + bitmap.getWidth();
        hitBox.bottom = y + bitmap.getHeight();
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getMinX() {
        return minX;
    }

    public void setMinX(int minX) {
        this.minX = minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMinY() {
        return minY;
    }

    public void setMinY(int minY) {
        this.minY = minY;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Rect getHitbox() {
        return hitBox;
    }
}
