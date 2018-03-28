package com.example.nickerman.trainbrain._GameChooseTrueOrFalse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.nickerman.trainbrain.R;
import com.example.nickerman.trainbrain.__selectGame.SelectGame;

import java.time.Instant;

public class ResultActivity extends AppCompatActivity {

    private TextView resultView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intentResult = getIntent();

        int voise = intentResult.getIntExtra("Game.ChooseTrueOrFalse", 15);


        resultView = (TextView) findViewById(R.id.resultView);
        resultView.setText(String.valueOf(voise));


    }

    @Override
    public void onBackPressed() {

        finish();


    }
}
