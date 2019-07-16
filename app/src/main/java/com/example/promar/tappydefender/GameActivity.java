package com.example.promar.tappydefender;

import android.app.Activity;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.KeyEvent;

//this class represents part of the model
public class GameActivity extends Activity {

    TDView gameView;

    // This is where the "Play" button from HomeActivity sends us
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get a display object to access screen details
        Display display = getWindowManager().getDefaultDisplay();
        //Load the resolution into a point object
        Point size = new Point();
        display.getSize(size);

        // Create an instance of our Tappy Defender View (TDView)
        // Also passing in "this" which is the Context of our app and screen resolution
        gameView = new TDView(this, size.x, size.y);

        // Make our gameView the view for the Activity
        setContentView(gameView);
    }

    // If the Activity is paused make sure to pause our thread
    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    // If the Activity is resumed make sure to resume our thread
    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return false;
    }
}
