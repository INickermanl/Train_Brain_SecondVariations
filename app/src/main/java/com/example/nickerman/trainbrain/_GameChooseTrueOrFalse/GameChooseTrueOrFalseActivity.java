package com.example.nickerman.trainbrain._GameChooseTrueOrFalse;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.*;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nickerman.trainbrain.R;
import com.example.nickerman.trainbrain._______model.QuestionModel;

import java.util.ArrayList;
import java.util.Random;
import java.util.zip.Inflater;

public class GameChooseTrueOrFalseActivity extends AppCompatActivity {

    private TextView mTimeView;
    private TextView mQuantityQuestionsView;


    private TextView mViewQuestion;
    private ImageButton mTrueButton;
    private ImageButton mFalseButton;


    private int mQuantityMistakes;
    private int mQuantitySeconds;


    private int mCurrentIndex = 0;
    private int mQuantityRightAnswer = 0;
    private int mCountMistake = 0;
    private int mI = 0;
    private int mBreakCount = 0;



    private ArrayList<QuestionModel> mQuestionModel = new ArrayList<>();

    private static final String intentResult = "Game.ChooseTrueOrFalse";


    CountDownTimer countDownTimer;
    private Toolbar toolbar;
    private ActionMenuView mItemQuantityQuestion;

        //toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.game_true_or_false, menu);
        return true;

    }
        //click back
    @Override
    public void onBackPressed(){
        /*super.onBackPressed();*/

       openQuitDialog();


    }

    private void openQuitDialog() {

        countDownTimer.cancel();

        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                GameChooseTrueOrFalseActivity.this);
        quitDialog.setTitle("Exit: you want to end the game ?");

        quitDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                mBreakCount = 1;

                finish();
            }
        });

        quitDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                addTime();
            }
        });

        quitDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_choose_true_of_false);
        //toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        mTimeView = (TextView) findViewById(R.id.quantity_second_item);
        mQuantityQuestionsView = (TextView) findViewById(R.id.quantity_questions_item);



        //get quantity time and questions in ptiv
         Intent intent = getIntent();
        mQuantityMistakes = Integer.parseInt(intent.getStringExtra("Settings.Game.Quantity.Question"));
         mQuantitySeconds = Integer.parseInt(intent.getStringExtra("Settings.Game.Quantity.Seconds"));

         addQuestionModelElement();
            //create arrayList with random value
        /*for (int i = 0; mQuantityQuestions != ; i++) {

            mQuestionModel.add(i, new QuestionModel(getRandomNumber(),getRandomNumber(),getRandomMathematicalSymbol()));

        }*/


        mViewQuestion = findViewById(R.id.view_question);
        mViewQuestion.setText(setViewQuestion());

        //sent to toolbar quantity question
        mQuantityQuestionsView.setText(" " + mQuantityRightAnswer);


        mTrueButton = (ImageButton) findViewById(R.id.button_true);
        mFalseButton =(ImageButton) findViewById(R.id.button_false);
        //mView_time = findViewById(R.id.view_time);

        addTime();

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()) {
                    case R.id.button_true:      updateTrueButton(view);
                                                countDownTimer.cancel();
                                                addTime();
                                                break;
                    case R.id.button_false :    updateFalseButton(view);
                                                countDownTimer.cancel();
                                                addTime();
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

    //method get random  value for view after equally
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
        if((mQuantityMistakes - 1) > mCountMistake){
            if (mQuestionModel.get(mCurrentIndex).getResult() == mQuestionModel.get(mCurrentIndex).getViewAnswer()
                    && R.id.button_true == view.getId()) {
                rightUserAnswer();
            }else{
                mistakesUserSorry();
            }
        }else{
            startIntentResult();
        }

    }

    //update false button
    private void updateFalseButton(View view){
        if((mQuantityMistakes - 1) > mCountMistake){
            if (mQuestionModel.get(mCurrentIndex).getResult() != mQuestionModel.get(mCurrentIndex).getViewAnswer()
                    && R.id.button_false == view.getId()) {
                rightUserAnswer();
            }else{
                mistakesUserSorry();
            }
        }else {
                        startIntentResult();
        }
    }




    private void addTime(){

        int second = mQuantitySeconds;

            countDownTimer = new CountDownTimer(second * 1000, 1000) {
                @Override
                public void onTick(long millis) {
                    //do to one second
                   // toolbar.set  //setText(" " + (int) (millis / 1000));
                   mTimeView.setText(" " + (int) (millis / 1000));

                }


                @Override
                public void onFinish() {
                    //when time is over

                    mI++;
                    mCurrentIndex++;
                    addQuestionModelElement();
                    if((mQuantityMistakes - 1) > mCountMistake){
                        mCountMistake++;
                        countDownTimer.cancel();
                        mViewQuestion.setText(setViewQuestion());
                        addTime();

                    }else {
                        if (mBreakCount == 0) {
                            startIntentResult();
                        }
                    }
                  /* if(mCurrentIndex < mQuestionModel.size() - 1){
                       mCurrentIndex++;
                       mViewQuestion.setText(setViewQuestion());
                       addTime();

                   }else if(mCurrentIndex == mQuantityQuestions - 1){
                       startIntentResult();
                   }*/
                }
            }.start();


    }

    private void startIntentResult(){
        //so step up index arrayList for do't go to result activity twice

        mBreakCount = 1;
        countDownTimer.cancel();
        //open result activity
        Intent intent  = new Intent(GameChooseTrueOrFalseActivity.this, ResultActivity.class);
        intent.putExtra(intentResult, mQuantityRightAnswer);
        startActivity(intent);
        finish();

    }

    private void addQuestionModelElement(){

        if(true){

            mQuestionModel.add(mI, new QuestionModel(getRandomNumber(),getRandomNumber(),getRandomMathematicalSymbol()));
        }
    }

    private void mistakesUserSorry(){
        //if user answer wrong just step up index
        mI++;
        mCountMistake++;
        mCurrentIndex++;
        addQuestionModelElement();
        countDownTimer.cancel();
        // update btn
        mViewQuestion.setText(setViewQuestion()); addQuestionModelElement();
        countDownTimer.cancel();
        // update btn
        mViewQuestion.setText(setViewQuestion());
    }

    private void rightUserAnswer() {
        countDownTimer.cancel();
        ++mQuantityRightAnswer;
        mI++;
        mCurrentIndex++;
        addQuestionModelElement();
        //update btn
        mViewQuestion.setText(setViewQuestion());
        mQuantityQuestionsView.setText(" " + mQuantityRightAnswer);

    }



}

