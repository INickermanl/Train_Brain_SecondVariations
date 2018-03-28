package com.example.nickerman.trainbrain._GameChooseTrueOrFalse;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nickerman.trainbrain.R;
import com.example.nickerman.trainbrain.__selectGame.SelectGame;

public class SettingsGameChooseTrueOrFalse extends AppCompatActivity {


    private Button mStartButton;
    private EditText mPossibleErrors;
    private EditText mQuantitySecond;

    private static final String mIntentQuantityQuestions = "Settings.Game.Quantity.Question";
    private static final String mIntentQuantitySeconds = "Settings.Game.Quantity.Seconds";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_choose_game_true_of_false_activity);


        mPossibleErrors = (EditText) findViewById(R.id.quantity_questions);
        mQuantitySecond = (EditText) findViewById(R.id.quantity_second);


        mStartButton = findViewById(R.id.start_button);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPossibleErrors.getText().toString().equals("") || mQuantitySecond.getText().toString().equals("")) {
                    if (mPossibleErrors.getText().toString().equals("")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsGameChooseTrueOrFalse.this);
                        builder.setTitle("Your did't enter quantity possible errors")
                                .setMessage("Please enter quantity possible errors")
                                .setIcon(R.drawable.ic_massage_all_false)
                                .setCancelable(false)
                                .setNegativeButton("OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    } else {

                        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsGameChooseTrueOrFalse.this);
                        builder.setTitle("Your did't enter quantity seconds")
                                .setMessage("Please enter quantity seconds")
                                .setIcon(R.drawable.ic_massage_all_false)
                                .setCancelable(false)
                                .setNegativeButton("OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });
                        AlertDialog alert = builder.create();
                        alert.show();
                    }
                } else {

                    String QuantityQuestions = mPossibleErrors.getText().toString();
                    String QuantitySeconds = mQuantitySecond.getText().toString();


                    Intent intent = new Intent(SettingsGameChooseTrueOrFalse.this, GameChooseTrueOrFalseActivity.class);
                    intent.putExtra(mIntentQuantityQuestions, QuantityQuestions);
                    intent.putExtra(mIntentQuantitySeconds, QuantitySeconds);
                    startActivity(intent);


                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();

    }
}
