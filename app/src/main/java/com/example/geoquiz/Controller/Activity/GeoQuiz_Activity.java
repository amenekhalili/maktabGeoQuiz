package com.example.geoquiz.Controller.Activity;


import androidx.fragment.app.Fragment;

import com.example.geoquiz.Controller.Fragment.GeoQuiz_Fragment;

public class GeoQuiz_Activity extends SingleFragmentActivity {


    @Override
    public Fragment CreateFragment() {
        return new GeoQuiz_Fragment();
    }
}