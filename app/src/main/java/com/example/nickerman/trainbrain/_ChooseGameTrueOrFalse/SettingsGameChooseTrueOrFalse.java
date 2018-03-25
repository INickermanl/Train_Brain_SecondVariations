package com.example.nickerman.trainbrain._ChooseGameTrueOrFalse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nickerman.trainbrain.R;

public class SettingsGameChooseTrueOrFalse extends AppCompatActivity {


    private Button mStartButton;
    private EditText mQuantityQuestions;
    private EditText mQuantitySecond;

    private static final String mIntentQuantityQuestions = "Settings.Game.Quantity.Question";
    private static final String mIntentQuantitySeconds = "Settings.Game.Quantity.Seconds";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_choose_game_true_of_false_activity);


        mQuantityQuestions = (EditText) findViewById(R.id.quantity_questions);
        mQuantitySecond = (EditText) findViewById(R.id.quantity_second);



        mStartButton = findViewById(R.id.start_button);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String QuantityQuestions = mQuantityQuestions.getText().toString();
                String QuantitySeconds = mQuantitySecond.getText().toString();



                Intent intent = new Intent(SettingsGameChooseTrueOrFalse.this, GameChooseTrueOrFalseActivity.class);
                intent.putExtra(mIntentQuantityQuestions,QuantityQuestions);
                intent.putExtra(mIntentQuantitySeconds,QuantitySeconds);
                startActivity(intent);
            }
        });

    }
}
