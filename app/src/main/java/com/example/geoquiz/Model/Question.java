package com.example.geoquiz.Model;

import androidx.core.app.ComponentActivity;

public class Question {
    private int mQuestiontextResId;
    private boolean mIsAnswerTrue;

    public Question(int mQuestiontextResId, boolean mIsAnswerTrue) {
        this.mQuestiontextResId = mQuestiontextResId;
        this.mIsAnswerTrue = mIsAnswerTrue;
    }

    public Question() {
    }

    public int getmQuestiontextResId() {
        return mQuestiontextResId;
    }

    public boolean ismIsAnswerTrue() {
        return mIsAnswerTrue;
    }

    public void setmQuestiontextResId(int mQuestiontextResId) {
        this.mQuestiontextResId = mQuestiontextResId;
    }

    public void setmIsAnswerTrue(boolean mIsAnswerTrue) {
        this.mIsAnswerTrue = mIsAnswerTrue;
    }
}
