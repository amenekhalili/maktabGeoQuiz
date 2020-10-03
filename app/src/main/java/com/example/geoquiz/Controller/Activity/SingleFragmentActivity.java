package com.example.geoquiz.Controller.Activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.geoquiz.Controller.Fragment.GeoQuiz_Fragment;
import com.example.geoquiz.R;

public abstract class SingleFragmentActivity extends AppCompatActivity {
    public abstract Fragment CreateFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.geoquiz);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.container_QeoQuiz_fragment);

        if(fragment == null){

            fragmentManager.beginTransaction()
                    .add(R.id.container_QeoQuiz_fragment ,CreateFragment() )
                    .commit();
        }



    }

}
