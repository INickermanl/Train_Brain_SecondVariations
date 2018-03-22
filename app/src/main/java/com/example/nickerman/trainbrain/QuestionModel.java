package com.example.nickerman.trainbrain;

/**
 * Created by AdminVN on 22.03.2018.
 */

public class QuestionModel {

    private int mValue1;
    private int mValue2;
    private boolean mUserAnswer;
    private String mMathimaticsSymbol;


    private QuestionModel(int value1, int value2, String mathematicalSymbol){

        mValue1 = value1;
        mValue2 = value2;
        mMathimaticsSymbol = mathematicalSymbol;

    }

}
