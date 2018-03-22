package com.example.nickerman.trainbrain;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class StartActivity extends AppCompatActivity {


    private TextView mViewQuestion;
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mStartButton;

    private int mCount = 0;
    private String mTextViewSrting;


    private QuestionModel[] mQuestionModel = new QuestionModel[]{
            new QuestionModel(getRandomNumber(), getRandomNumber(), getRandomMathematicalSymbol()),
            new QuestionModel(getRandomNumber(), getRandomNumber(), getRandomMathematicalSymbol()),
            new QuestionModel(getRandomNumber(), getRandomNumber(), getRandomMathematicalSymbol()),
            new QuestionModel(getRandomNumber(), getRandomNumber(), getRandomMathematicalSymbol()),
            new QuestionModel(getRandomNumber(), getRandomNumber(), getRandomMathematicalSymbol()),
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mStartButton = (Button) findViewById(R.id.start_button);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getViewQuestion();

                mViewQuestion.setText( mTextViewSrting);

            }
        });


        mViewQuestion = (TextView) findViewById(R.id.view_question);


        mTrueButton = (Button) findViewById(R.id.button_true);


        mTrueButton = (Button) findViewById(R.id.button_false);


    }
        //получение рандомного числа для модели

    private int getRandomNumber() {

        Random random = new Random();
        int randomNumber = random.nextInt(9) + 1;

        return randomNumber;
    }

    //получение рандомного символа
    private String getRandomMathematicalSymbol() {

        String resultSymbol;

        if (getRandomNumber() > getRandomNumber()) {
            resultSymbol = "+";

        } else {
            resultSymbol = "-";
        }


        return resultSymbol;
    }

    //при помощи сивола выбор матиматической опирации

    private int getResultTwoRandomNumber(String symbol){

        int resultValue1_Value2 = 0;
        switch (symbol){

            case "+" : resultValue1_Value2 = mQuestionModel[mCount].getValue1() +  mQuestionModel[mCount].getValue2();
                        break;
            case "-" : resultValue1_Value2 =  mQuestionModel[mCount].getValue1() -  mQuestionModel[mCount].getValue2();
                break;

        }

        return resultValue1_Value2;
    }



    //запись в глобальную переменную значения всего поля view
    private void getViewQuestion(){
        mTextViewSrting = mQuestionModel[mCount].getValue1() + "  " + mQuestionModel[mCount].getMathimaticsSymbol() + "  " +
                          mQuestionModel[mCount].getValue2() +"  =  " + getResultTwoRandomNumber(mQuestionModel[mCount].getMathimaticsSymbol());


    }


}

