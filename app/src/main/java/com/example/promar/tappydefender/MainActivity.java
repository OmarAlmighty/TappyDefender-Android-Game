package com.example.promar.tappydefender;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button startBtn = (Button) findViewById(R.id.startBtn);
        startBtn.setOnClickListener(this);
        View decorView = getWindow().getDecorView();

        // Prepare to load fastest time
        SharedPreferences prefs;
        prefs = getSharedPreferences("HiScores", Context.MODE_PRIVATE);

        // Get a reference to the TextView in our layout
        final TextView textView = (TextView) findViewById(R.id.highScoreTxt);

        // Load fastest time if not available our high score = 1000000
        long fast = prefs.getLong("fastestTime", 1000000);
        textView.setText("Fastest Time:" + fast);

    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(this, GameActivity.class));
        finish();
    }

    // If the player hits the back button, quit the app
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return false;
    }
}
