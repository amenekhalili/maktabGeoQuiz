package com.example.geoquiz.Model;

import androidx.core.app.ComponentActivity;

import java.util.UUID;

public class Question {
    private int mQuestiontextResId;
    private boolean mIsAnswerTrue;
    private UUID mUUID;

    public UUID getUUID() {
        return mUUID;
    }

    public Question(int mQuestiontextResId, boolean mIsAnswerTrue) {
        this.mQuestiontextResId = mQuestiontextResId;
        this.mIsAnswerTrue = mIsAnswerTrue;
        mUUID = UUID.randomUUID();
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
