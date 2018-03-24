package com.example.nickerman.trainbrain.___WelcomeScreen;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.nickerman.trainbrain.R;
import com.example.nickerman.trainbrain.__selectGame.SelectGame;

public class WelcomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_screen_activity);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeScreen.this, SelectGame.class);
                startActivity(intent);
                finish();
            }
        },2*1000);


    }
}
