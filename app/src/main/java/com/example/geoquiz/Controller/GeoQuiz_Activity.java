package com.example.geoquiz.Controller;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.geoquiz.R;

public class GeoQuiz_Activity extends AppCompatActivity {
    public static final String EXTRA_QUESTION_A_NSWER = " com.example.geoquiz.Question_Answer";
    public static final String EXTRA_MCURRENTINDEX = "com.example.geoquiz.mcurrentindex";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.geoquiz);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.container_QeoQuiz_fragment);

        if(fragment == null){
            GeoQuiz_Fragment geoQuiz_fragment = new GeoQuiz_Fragment();
            fragmentManager.beginTransaction()
                    .add(R.id.container_QeoQuiz_fragment ,geoQuiz_fragment )
                    .commit();
        }



    }




}