package com.example.nickerman.trainbrain.__selectGame;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.nickerman.trainbrain.R;
import com.example.nickerman.trainbrain._GameChooseTrueOrFalse.GameChooseTrueOrFalseActivity;
import com.example.nickerman.trainbrain._GameChooseTrueOrFalse.SettingsGameChooseTrueOrFalse;

public class SelectGame extends AppCompatActivity {

    private ImageButton mTrainBrain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_game_activity);


        mTrainBrain = findViewById(R.id.train_brain);
        mTrainBrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(SelectGame.this, SettingsGameChooseTrueOrFalse.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onBackPressed(){
        /*super.onBackPressed();*/

        openQuitDialog();


    }

    private void openQuitDialog() {



        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                SelectGame.this);
        quitDialog.setTitle("Exit: are you sure ?");

        quitDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

                finish();
            }
        });

        quitDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

            }
        });

        quitDialog.show();
    }

}
