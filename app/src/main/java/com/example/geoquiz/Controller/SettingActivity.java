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
    private Button btn_changeQuestionSize;
    private Button btn_smallsize;
    private Button btn_mediumsize;
    private Button btn_largesize;
    private int smallsize = 14;
    private int mediumsize = 18;
    private int largesize = 26;
    LinearLayout linearLayoutchangequestion ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);

        findview();
        setlistener();
        linearLayoutchangequestion.setVisibility(LinearLayout.GONE);
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
                intent.putExtra(SMALL_SIZE, smallsize);
                setResult(RESULT_OK, intent);
            }
        });


        btn_mediumsize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(MEDIUM_SIZE, mediumsize);
                setResult(RESULT_OK , intent);
            }
        });

        btn_largesize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(LARGE_SIZE, largesize);
                setResult(RESULT_OK , intent);
            }
        });
    }

    private void findview() {
        btn_changeQuestionSize = findViewById(R.id.btn_changesizequestion);
        btn_smallsize = findViewById(R.id.btn_smallsize);
        btn_mediumsize = findViewById(R.id.btn_mediumsize);
        btn_largesize = findViewById(R.id.btn_largsize);
        linearLayoutchangequestion = findViewById(R.id.changequestion_layout);
    }
}