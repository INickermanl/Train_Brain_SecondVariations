package com.example.nickerman.trainbrain._GameChooseTrueOrFalse;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nickerman.trainbrain.R;
import com.example.nickerman.trainbrain._______model.QuestionModel;

import java.util.ArrayList;
import java.util.Random;
import java.util.zip.Inflater;

public class GameChooseTrueOrFalseActivity extends AppCompatActivity {

    private TextView mView_time;

    private TextView mViewQuestion;
    private ImageButton mTrueButton;
    private ImageButton mFalseButton;


    private int mQuantityQuestions;
    private int mQuantitySeconds;


    private int mCurrentIndex = 0;
    private int mQuantityRightAnswer = 0;



    private ArrayList<QuestionModel> mQuestionModel = new ArrayList<>();

    private static final String intentResult = "Game.ChooseTrueOrFalse";


    CountDownTimer countDownTimer;

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game_true_or_false, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_choose_true_of_false);

        Intent intent = getIntent();
         mQuantityQuestions = Integer.parseInt(intent.getStringExtra("Settings.Game.Quantity.Question"));
         mQuantitySeconds = Integer.parseInt(intent.getStringExtra("Settings.Game.Quantity.Seconds"));



        for (int i = 0; i < mQuantityQuestions; i++) {

            mQuestionModel.add(i, new QuestionModel(getRandomNumber(),getRandomNumber(),getRandomMathematicalSymbol()));

        }

        mViewQuestion = findViewById(R.id.view_question);
        mViewQuestion.setText(setViewQuestion());

        mTrueButton = (ImageButton) findViewById(R.id.button_true);
        mFalseButton =(ImageButton) findViewById(R.id.button_false);
        mView_time = findViewById(R.id.view_time);

        addTime(mView_time);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()) {
                    case R.id.button_true:      updateTrueButton(view);
                                                countDownTimer.cancel();
                                                addTime(mView_time);
                                                break;
                    case R.id.button_false :    updateFalseButton(view);
                                                countDownTimer.cancel();
                                                addTime(mView_time);
                                                break;
                }
            }
        };

        mTrueButton.setOnClickListener(onClickListener);
        mFalseButton.setOnClickListener(onClickListener);




    }




    //method get random number for first and second value QuestionModel.
    private int getRandomNumber(){

        Random random = new Random();
        int randomNumber = random.nextInt(9) + 1;

        return randomNumber;
    }

    //method get random symbol for QuestionModel (MathematicalSymbol).
    private String getRandomMathematicalSymbol(){


        String mathematicalSymbol;
        if(getRandomNumber() >= getRandomNumber()){
            mathematicalSymbol = "+";
        }else{
            mathematicalSymbol = "-";
        }


        return mathematicalSymbol;
    }


    //method set View in mainActivity text
    private String setViewQuestion(){

        String resultStringValue = String.valueOf(mQuestionModel.get(mCurrentIndex).getValue1()) + " " +
                                    mQuestionModel.get(mCurrentIndex).getMathimaticsSymbol() + " " +
                                    String.valueOf(mQuestionModel.get(mCurrentIndex).getValue2()) + " = " + mValueForView(getMathematicalResult());



        return resultStringValue;
    }


    //METHODS FOR VALUE RESULT.


    //method get result mathematical operator
    private int getMathematicalResult(){
        int mathematicalResult = 0;

        switch (mQuestionModel.get(mCurrentIndex).getMathimaticsSymbol()){
            case "+" : mathematicalResult = mQuestionModel.get(mCurrentIndex).getValue1() + mQuestionModel.get(mCurrentIndex).getValue2();
                            break;
            case "-" : mathematicalResult = mQuestionModel.get(mCurrentIndex).getValue1() - mQuestionModel.get(mCurrentIndex).getValue2();
                            break;
        }


        return mathematicalResult;

    }

    //method get value for view
    private int mValueForView(int ViewResult){

        int result = ViewResult;

        int randomNumber1 = getRandomNumber();
        int randomNumber2 = getRandomNumber();


        if(randomNumber1 > randomNumber2){

        }else if(randomNumber1 < randomNumber2){
            ViewResult += 2;
        }else{
            ViewResult -= 1;
        }



        mQuestionModel.set(mCurrentIndex, new QuestionModel(ViewResult, result));

        return ViewResult;

    }


    //METHOD FOR BUTTONS
        //update true button
    private void updateTrueButton(View view){
        if(mCurrentIndex < mQuantityQuestions - 1){
            if (mQuestionModel.get(mCurrentIndex).getResult() == mQuestionModel.get(mCurrentIndex).getViewAnswer()
                    && R.id.button_true == view.getId()) {
                countDownTimer.cancel();
                mQuantityRightAnswer++;
                mCurrentIndex++;
                //update btn
                mViewQuestion.setText(setViewQuestion());

            } else {
                mCurrentIndex++;
                countDownTimer.cancel();
                // update btn
                mViewQuestion.setText(setViewQuestion());
            }
        }else{ if (mQuestionModel.get(mCurrentIndex).getResult() == mQuestionModel.get(mCurrentIndex).getViewAnswer()
                && R.id.button_true == view.getId()) {
            mQuantityRightAnswer++;
            countDownTimer.cancel();

        }

            countDownTimer.cancel();
            Intent intent  = new Intent(GameChooseTrueOrFalseActivity.this, ResultActivity.class);
            intent.putExtra(intentResult, mQuantityRightAnswer);
            startActivity(intent);
        }

    }

    //update false button
    private void updateFalseButton(View view){
        if(mCurrentIndex < mQuantityQuestions - 1) {
            if (mQuestionModel.get(mCurrentIndex).getResult() != mQuestionModel.get(mCurrentIndex).getViewAnswer()
                    && R.id.button_false == view.getId()) {
                countDownTimer.cancel();
                mQuantityRightAnswer++;
                mCurrentIndex++;
                //update btn
                mViewQuestion.setText(setViewQuestion());

            } else {
                countDownTimer.cancel();
                mCurrentIndex++;
                //update btn
                mViewQuestion.setText(setViewQuestion());
            }
        }else{
            if (mQuestionModel.get(mCurrentIndex).getResult() != mQuestionModel.get(mCurrentIndex).getViewAnswer()
                    && R.id.button_false == view.getId()) {
                mQuantityRightAnswer++;
                countDownTimer.cancel();

            }


            countDownTimer.cancel();
            Intent intent  = new Intent(GameChooseTrueOrFalseActivity.this, ResultActivity.class);
            intent.putExtra(intentResult, mQuantityRightAnswer);
            startActivity(intent);
        }

    }


    public void addTime(final TextView view_time){

        int second = mQuantitySeconds;


            countDownTimer = new CountDownTimer(second * 1000, 1000) {
                @Override
                public void onTick(long millis) {
                    view_time.setText(" " + (int) (millis / 1000));

                }


                @Override
                public void onFinish() {
                   if(mCurrentIndex < mQuantityQuestions - 1){
                       mCurrentIndex++;
                       mViewQuestion.setText(setViewQuestion());
                       addTime(mView_time);

                   }else{
                       countDownTimer.cancel();
                       Intent intent  = new Intent(GameChooseTrueOrFalseActivity.this, ResultActivity.class);
                       intent.putExtra(intentResult, mQuantityRightAnswer);
                       startActivity(intent);
                   }
                }
            }.start();


    }





}

