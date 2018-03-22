package com.example.nickerman.trainbrain;

/**
 * Created by AdminVN on 22.03.2018.
 */

public class QuestionModel {

    private int mValue1;
    private int mValue2;
    private boolean mUserAnswer;
    private String mMathematicalSymbol;


    public QuestionModel(int value1, int value2, String mathematicalSymbol){

        mValue1 = value1;
        mValue2 = value2;
        mMathematicalSymbol = mathematicalSymbol;

    }


    public int getValue1() {
        return mValue1;
    }

    public void setValue1(int value1) {
        mValue1 = value1;
    }

    public int getValue2() {
        return mValue2;
    }

    public void setValue2(int value2) {
        mValue2 = value2;
    }

    public boolean isUserAnswer() {
        return mUserAnswer;
    }

    public void setUserAnswer(boolean userAnswer) {
        mUserAnswer = userAnswer;
    }

    public String getMathimaticsSymbol() {
        return mMathematicalSymbol;
    }

    public void setMathimaticsSymbol(String mathimaticsSymbol) {
        mMathematicalSymbol = mathimaticsSymbol;
    }
}
