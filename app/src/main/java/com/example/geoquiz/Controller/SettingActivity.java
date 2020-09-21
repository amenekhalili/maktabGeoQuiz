package com.example.geoquiz.Controller;

import androidx.annotation.NonNull;
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
    public static final String COLOE_BACKGROUND = "coloeBackground";
    public static final String SIZE_QUESTION = "sizeQuestion";
    public static final String FLAG_SIZE = "flag_size";
    public static final String FLAG_COLOR = "flag_color";
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
    private boolean flagsize = false;
    private boolean flagcolor = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);

        if(savedInstanceState != null){
            sizeoftextquestion = savedInstanceState.getInt(SIZE_QUESTION , 16);
            colorofbackground = savedInstanceState.getInt(COLOE_BACKGROUND , 4);
            flagsize = savedInstanceState.getBoolean(FLAG_SIZE , false);
            flagcolor = savedInstanceState.getBoolean(FLAG_COLOR , false);
        }

        findview();
        setlistener();
        linearLayoutchangequestion.setVisibility(LinearLayout.GONE);
        linearLayoutchangebackground.setVisibility(LinearLayout.GONE);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(flagsize == true){
            linearLayoutchangequestion.setVisibility(LinearLayout.VISIBLE);
            setAlldata();
        }
        if(flagcolor == true){
            linearLayoutchangebackground.setVisibility(LinearLayout.VISIBLE);
            setAlldata();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SIZE_QUESTION, sizeoftextquestion);
        outState.putInt(COLOE_BACKGROUND, colorofbackground);
        outState.putBoolean(FLAG_SIZE, flagsize);
        outState.putBoolean(FLAG_COLOR, flagcolor);
    }

    private void setlistener() {
        btn_changeQuestionSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutchangequestion.setVisibility(LinearLayout.VISIBLE);
                flagsize = true;
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
                flagcolor = true;
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