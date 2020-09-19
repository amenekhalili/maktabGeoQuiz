package com.example.geoquiz.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.geoquiz.R;

public class CheatActivity extends AppCompatActivity {

    public static final String EXTRA_IS_CHEAT = "com.example.geoquizischeat.ischeat";
    public static final String EXTRA_MCURRENTINDEX = "com.example.geoquizischeat.currentindex";
    private Button mbtn_showcheat;
    private TextView mtxtview_Answer;
    private boolean mIsanswertrue;
    int [] mcurrentindex = {10,10,10,10,10,10};
     int mcurrent = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        mIsanswertrue = getIntent().getBooleanExtra(GeoQuiz_Activity.EXTRA_QUESTION_A_NSWER, false);
        mcurrentindex[mcurrent] = getIntent().getIntExtra(GeoQuiz_Activity.EXTRA_MCURRENTINDEX,10);

        findView();
        setlistener();

    }

    private void setlistener() {
        mbtn_showcheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsanswertrue)
                    mtxtview_Answer.setText(R.string.str_true);
                else
                    mtxtview_Answer.setText(R.string.str_false);


                setshowmAnswerResult(true , mcurrentindex[mcurrent]);

            }
        });
    }

    private void setshowmAnswerResult(boolean ischeat , int mcurrentindex) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_IS_CHEAT, ischeat);
        intent.putExtra(EXTRA_MCURRENTINDEX , mcurrentindex);
        setResult(RESULT_OK, intent);
    }

    private void findView() {
        mbtn_showcheat = findViewById(R.id.show_cheat);
        mtxtview_Answer = findViewById(R.id.answer_txtview);
    }
}