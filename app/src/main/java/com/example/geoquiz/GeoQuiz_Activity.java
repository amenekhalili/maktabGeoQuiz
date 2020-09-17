package com.example.geoquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GeoQuiz_Activity<str> extends AppCompatActivity {
         public Button btn_true ;
         public Button btn_false;
         public ImageButton btn_next;
         public ImageButton btn_pre;
         public ImageButton btn_doublenext;
         public ImageButton btn_doublepre;
         public int mcurrentindex = 0 ;
         public TextView mtextviewQuestion;
         public int score = 0;
         public Button btn_score ;
         public String scorestr;
         public String str;
         public ImageButton  btnreset;
         LinearLayout linearLayout;
         Question [] mquestion = {
                 new Question(R.string.question_australia,false),
                 new Question(R.string.question_oceans,true),
                 new Question(R.string.question_mideast,false),
                 new Question(R.string.question_africa,true),
                 new Question(R.string.question_americas,false),
                 new Question(R.string.question_asia,false)
    };
    boolean [] isAnswer = {false,false,false,false,false,false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.geoquiz);

        findview();
        setListener();
        setquestion();
        setScore("امتیاز : 0");
    }




    private void setVisibility(){
        boolean flag = false;
        for (int i = 0; i <isAnswer.length ; i++) {
            if(isAnswer[i] == false){
                flag = true;
            }
        }
        if(flag == false){


           linearLayout.setVisibility(LinearLayout.GONE);
           btnreset.setVisibility(findViewById(R.id.btn_reset).VISIBLE);
        }

    }


    private void setScore(String s) {
        btn_score.setText(s);
    }

    private void setquestion() {
           btnreset.setVisibility(findViewById(R.id.btn_reset).INVISIBLE);
        setVisibility();
        int questionId = mquestion[mcurrentindex].getmQuestiontextResId();

        if(isAnswer[mcurrentindex] == false){
            EnableButton();
        }else if(isAnswer[mcurrentindex] == true) {
            disableButton();
        }
        mtextviewQuestion.setText(questionId);

    }

    private void disableButton() {
        btn_true.setEnabled(false);
        btn_false.setEnabled(false);
    }

    private void EnableButton() {
        btn_true.setEnabled(true);
        btn_false.setEnabled(true);
    }

    private void disableButton(Button btn_false, boolean b, Button btn_true) {
        btn_false.setEnabled(b);
        btn_true.setEnabled(b);
    }


    private void setListener() {


        btn_true.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             checkAnswer(true);
             setScore(str);
         }
        });

        btn_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           checkAnswer(false);
                setScore(str);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mcurrentindex = (mcurrentindex + 1 ) % mquestion.length;
                setquestion();
            }
        });

        btn_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             mcurrentindex = (mcurrentindex - 1 + mquestion.length) % mquestion.length;
                setquestion();
            }
        });



        btn_doublenext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mcurrentindex = 5;
                setquestion();
            }
        });

        btn_doublepre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mcurrentindex = 0 ;
                setquestion();
            }
        });


        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               linearLayout.setVisibility(LinearLayout.VISIBLE);

                for (int i = 0; i <isAnswer.length ; i++) {
                    isAnswer[i] = false;
                }
                setquestion();
                score = 0;
                setScore("امتیاز : 0");
            }
        });

    }

    private void findview() {
        btn_true = findViewById(R.id.btn_true);
        btn_false = findViewById(R.id.btn_false);
        btn_next = findViewById(R.id.btn_next);
        btn_pre = findViewById(R.id.btn_pre);
        btn_doublenext = findViewById(R.id.btn_doublenext);
        btn_doublepre = findViewById(R.id.btn_doublepre);
        mtextviewQuestion = findViewById(R.id.txt_questiom);
        btn_score = findViewById(R.id.btn_score);
        btnreset = findViewById(R.id.btn_reset);
       linearLayout = (LinearLayout)this.findViewById(R.id.hideUI);
    }


    private void checkAnswer(boolean userpressed) {

        final LinearLayout layouttrue = new LinearLayout(this);
        final TextView textViewtrue = new TextView(this);
        final Toast toasttrue = new Toast(getApplicationContext());
        final ImageView imageViewtrue = new ImageView(this);

        final LinearLayout layoutfalse = new LinearLayout(this);
        final TextView textViewfalse = new TextView(this);
        final Toast toastfalse= new Toast(getApplicationContext());
        final ImageView imageViewfalse = new ImageView(this);

        if(mquestion[mcurrentindex].ismIsAnswerTrue() == userpressed){
            score ++ ;
            scorestr = String.valueOf(score);
            str = "امتیاز: " + scorestr;
            textViewtrue.setText(R.string.true_message);
            textViewtrue.setTextColor(Color.GREEN);
            toasttrue.setDuration(Toast.LENGTH_SHORT);
            imageViewtrue.setImageResource(R.drawable.ic_true);
            layouttrue.removeAllViews();
            layouttrue.addView(textViewtrue);
            layouttrue.addView(imageViewtrue);
            toasttrue.setView(layouttrue);
            toasttrue.show();
            isAnswer[mcurrentindex] = true;
             disableButton();
        }else{
            scorestr = String.valueOf(score);
            str = "امتیاز: " + scorestr;
            textViewfalse.setText(R.string.false_message);
            textViewfalse.setTextColor(Color.RED);
            toastfalse.setDuration(Toast.LENGTH_SHORT);
            imageViewfalse.setImageResource(R.drawable.ic_false);
            layoutfalse.removeAllViews();
            layoutfalse.addView(textViewfalse);
            layoutfalse.addView(imageViewfalse);
            toastfalse.setView(layoutfalse);
            toastfalse.setGravity(Gravity.CENTER,0,-400);
            toastfalse.show();
            isAnswer[mcurrentindex] = true;
            disableButton();
        }


    }




}