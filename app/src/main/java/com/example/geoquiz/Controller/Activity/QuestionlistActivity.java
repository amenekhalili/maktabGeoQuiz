package com.example.geoquiz.Controller.Activity;

import androidx.fragment.app.Fragment;

import com.example.geoquiz.Controller.Fragment.QuestionlistFragment;

public class QuestionlistActivity extends SingleFragmentActivity {


    @Override
    public Fragment CreateFragment() {
        return new QuestionlistFragment();
    }
}