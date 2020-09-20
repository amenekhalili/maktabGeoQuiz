package com.example.geoquiz.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.geoquiz.R;

public class SettingActivity extends AppCompatActivity {
    public static final String SMALL_SIZE = "small_size";
    public static final String MEDIUM_SIZE = "medium_size";
    public static final String LARGE_SIZE = "large_size";
    public static final String LIGHT_GREEN = "light_green";
    public static final String LIGHT_BLUE = "light_blue";
    public static final String WHITE = "white";
    public static final String LIGHT_RED = "light_red";
    public static final int lightblue = 1;
    public static final int lightgreen = 2;
    public static final int lightred = 3;
    public static final int White = 4;
    private Button btn_changeQuestionSize;
    private Button btn_smallsize;
    private Button btn_mediumsize;
    private Button btn_largesize;
    private Button btn_changebackgroundcolor;
    private Button btn_lightblue;
    private Button btn_lightred;
    private Button btn_lightgreen;
    private Button btn_white;



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
                Intent intent = new Intent();
                intent.putExtra(SMALL_SIZE, 14);
                setResult(RESULT_OK, intent);
            }
        });


        btn_mediumsize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(MEDIUM_SIZE,18);
                setResult(RESULT_OK , intent);
            }
        });

        btn_largesize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(LARGE_SIZE, 26);
                setResult(RESULT_OK , intent);
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
              Intent intent = new Intent();
              intent.putExtra(LIGHT_BLUE, lightblue) ;
              setResult(RESULT_OK , intent);

          }
      });

      btn_lightgreen.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent();
              intent.putExtra(LIGHT_GREEN , lightgreen);
              setResult(RESULT_OK , intent);
          }
      });

      btn_lightred.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent();
              intent.putExtra(LIGHT_RED, lightred);
              setResult(RESULT_OK , intent);
          }
      });

      btn_white.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent();
              intent.putExtra(WHITE, White);
              setResult(RESULT_OK , intent);
          }
      });

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