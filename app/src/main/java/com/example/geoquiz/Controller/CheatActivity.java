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
    private Button mbtn_showcheat;
private TextView mtxtview_Answer;
private boolean mIsanswertrue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

         mIsanswertrue = getIntent().getBooleanExtra(GeoQuiz_Activity.EXTRA_QUESTION_A_NSWER,false);

        findView();
        setlistener();

    }

    private void setlistener() {
        mbtn_showcheat.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(mIsanswertrue)
                  mtxtview_Answer.setText(R.string.str_true);
              else
                  mtxtview_Answer.setText(R.string.str_false);


       setshowmAnswerResult(true);

          }
      });
    }

    private void setshowmAnswerResult(boolean ischeat) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_IS_CHEAT,ischeat);
        setResult(RESULT_OK,intent);
    }

    private void findView() {
        mbtn_showcheat = findViewById(R.id.show_cheat);
        mtxtview_Answer = findViewById(R.id.answer_txtview);
    }
}