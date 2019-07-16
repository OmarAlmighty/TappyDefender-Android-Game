package com.example.promar.tappydefender;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

//this class represents part of the model
public class PlayerShip {
    private int shieldStrength;
    private Bitmap bitmap;
    private int x, y, speed;
    private boolean boosting;
    private final int GRAVITY = -12;
    // Stop ship leaving the screen
    private int minY, maxY;
    //Limit the bounds of the ship's speed
    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 20;
    // A hit box for collision detection
    private Rect hitBox;

    public PlayerShip(Context context, int screenX, int screenY) {
        x = y = 50;
        speed = 1;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ship);
        boosting = false;
        minY = 0;
        maxY = screenY - bitmap.getHeight();
        //Initialize hitbox
        hitBox = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
        shieldStrength = 50;
    }

    public void update() {
        //Are we boosting
        if (boosting)
            speed += 2;
        else
            speed -= 5;

        //Constrain top speed
        if (speed > MAX_SPEED)
            speed = MAX_SPEED;

        //Never stop completely
        if (speed < MIN_SPEED)
            speed = MIN_SPEED;

        //Move the ship up and down
        y -= speed + GRAVITY;

        //Don't let ship stray off screen
        if (y < minY)
            y = minY;
        if (y > maxY)
            y = maxY;

        //Refresh hitbox location
        hitBox.left = x;
        hitBox.top = y;
        hitBox.right = x + bitmap.getWidth();
        hitBox.bottom = y + bitmap.getHeight();
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setBoosting() {
        boosting = true;
    }

    public void stopBoosting() {
        boosting = false;
    }

    public Rect getHitbox() {
        return hitBox;
    }

    public int getShieldStrength() {
        return shieldStrength;
    }

    public void reduceShieldStrength() {
        shieldStrength--;
    }
}
