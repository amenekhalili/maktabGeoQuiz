package com.example.geoquiz.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.geoquiz.R;

public class SettingActivity extends AppCompatActivity {
    public static final String COLOROFBACKGROUND = "colorofbackground";
    public static final String SIZEOFTEXTQUESTION = "sizeoftextquestion";
    private Button btn_changeQuestionSize;
    private Button btn_smallsize;
    private Button btn_mediumsize;
    private Button btn_largesize;
    private Button btn_changebackgroundcolor;
    private Button btn_lightblue;
    private Button btn_lightred;
    private Button btn_lightgreen;
    private Button btn_white;
    private int sizeoftextquestion;
    private int colorofbackground;
    LinearLayout linearLayoutchangequestion ;
    LinearLayout linearLayoutchangebackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);

        findview();
        setlistener();
        linearLayoutchangequestion.setVisibility(LinearLayout.GONE);
        linearLayoutchangebackground.setVisibility(LinearLayout.GONE);
    }




    private void setlistener() {
        btn_changeQuestionSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutchangequestion.setVisibility(LinearLayout.VISIBLE);
            }
        });

        btn_smallsize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizeoftextquestion = 14;
                setAlldata();
            }
        });


        btn_mediumsize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizeoftextquestion = 18;
                setAlldata();
            }
        });

        btn_largesize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sizeoftextquestion = 26;
                setAlldata();
            }
        });

        btn_changebackgroundcolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutchangebackground.setVisibility(LinearLayout.VISIBLE);
            }
        });

        btn_lightblue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorofbackground = 1;
                setAlldata();
            }
        });

        btn_lightgreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorofbackground = 2;
                setAlldata();
            }
        });

        btn_lightred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorofbackground = 3;
                setAlldata();
            }
        });

        btn_white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colorofbackground = 4;
                setAlldata();
            }
        });

    }

   private void  setAlldata(){
        Intent intent = new Intent();
        intent.putExtra(COLOROFBACKGROUND, colorofbackground);
        intent.putExtra(SIZEOFTEXTQUESTION, sizeoftextquestion);
        setResult(RESULT_OK , intent);
   }

    private void findview() {

        btn_changeQuestionSize = findViewById(R.id.btn_changesizequestion);
        btn_smallsize = findViewById(R.id.btn_smallsize);
        btn_mediumsize = findViewById(R.id.btn_meduimsize);
        btn_largesize = findViewById(R.id.btn_largesize);
        linearLayoutchangequestion = findViewById(R.id.changequestion_layout);
        linearLayoutchangebackground = findViewById(R.id.changebackground_layout);
        btn_lightblue = findViewById(R.id.btn_lightblue);
        btn_lightred = findViewById(R.id.btn_lightred);
        btn_lightgreen  = findViewById(R.id.btn_lightgreen);
        btn_white = findViewById(R.id.btn_white);
        btn_changebackgroundcolor = findViewById(R.id.btn_chnagebackground);

    }
}