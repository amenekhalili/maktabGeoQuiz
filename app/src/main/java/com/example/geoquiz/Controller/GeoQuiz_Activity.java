package com.example.geoquiz.Controller;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.geoquiz.Model.Question;
import com.example.geoquiz.R;

import static com.example.geoquiz.Controller.SettingActivity.COLOROFBACKGROUND;
import static com.example.geoquiz.Controller.SettingActivity.SIZEOFTEXTQUESTION;

public class GeoQuiz_Activity extends AppCompatActivity {
    private static final String CURRENTINDEX = "currentindex";
    private static final String IS_ANSWER = "isAnswer";
    private static final String SCORE = "score";
    public static final String EXTRA_QUESTION_A_NSWER = " com.example.geoquiz.Question_Answer";
    private static final int REQUEST_CODE_CHEAT = 0;
    public static final String EXTRA_MCURRENTINDEX = "com.example.geoquiz.mcurrentindex";
    public static final String M_IS_CHEATER = "mIsCheater";
    public static final String INDEXCHEATER = "indexcheater";
    public static final int REQUEST_CODE_SETTING = 1;
    private int sizeoftextQuestion;
    private int colorofbackground;
    private Button btn_true;
    private Button btn_false;
    private ImageButton btn_next;
    private Button btn_cheat;
    private ImageButton btn_pre;
    private ImageButton btn_doublenext;
    private ImageButton btn_doublepre;
    private int mcurrentindex = 0;
    private TextView mtextviewQuestion;
    private int score = 0;
    private Button btn_score;
    private String scorestr;
    private String str;
    private ImageButton btnreset;
    private boolean mIscheater;
    private int indexcheater;
    private Button btn_setting;
    LinearLayout linearLayout;
    LinearLayout linearLayoutpre;
    LinearLayout linearLayoutnext;
    LinearLayout linearLayoutmtxtquestion;
    FrameLayout frameLayoutmtxtquestion;

    Question[] mquestion = {
            new Question(R.string.question_australia, false),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, true),
            new Question(R.string.question_americas, false),
            new Question(R.string.question_asia, false)
    };
    boolean[] isAnswer = {false, false, false, false, false, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.geoquiz);
        if (savedInstanceState != null) {
            mcurrentindex = savedInstanceState.getInt(CURRENTINDEX, 0);
            score = savedInstanceState.getInt(SCORE, 0);
            isAnswer = savedInstanceState.getBooleanArray(IS_ANSWER);
            indexcheater = savedInstanceState.getInt(INDEXCHEATER, 10);
            mIscheater = savedInstanceState.getBoolean(M_IS_CHEATER, false);
            sizeoftextQuestion = savedInstanceState.getInt(SIZEOFTEXTQUESTION, 16);
            colorofbackground = savedInstanceState.getInt(COLOROFBACKGROUND, 0);

        }


        findview();
        setListener();
        setquestion();
        makestr();
        setScore(str);
        setVisibility();
    }


    @Override
    protected void onResume() {
        super.onResume();
        setSizeQuestiionText();
        setColorofbackground();
    }

    private void setColorofbackground() {

        if(linearLayoutmtxtquestion != null){
            if (colorofbackground == 3) {
                linearLayoutmtxtquestion.setBackgroundColor(Color.RED);
            }
            if (colorofbackground == 2) {
                linearLayoutmtxtquestion.setBackgroundColor(Color.GREEN);
            }
            if (colorofbackground == 1) {
                linearLayoutmtxtquestion.setBackgroundColor(Color.BLUE);
            }
            if (colorofbackground == 4) {
                linearLayoutmtxtquestion.setBackgroundColor(Color.WHITE);
            }
        }
        if(frameLayoutmtxtquestion != null){
            if (colorofbackground == 3) {
                frameLayoutmtxtquestion.setBackgroundColor(Color.RED);
            }
            if (colorofbackground == 2) {
               frameLayoutmtxtquestion.setBackgroundColor(Color.GREEN);
            }
            if (colorofbackground == 1) {
               frameLayoutmtxtquestion.setBackgroundColor(Color.BLUE);
            }
            if (colorofbackground == 4) {
               frameLayoutmtxtquestion.setBackgroundColor(Color.WHITE);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK || data == null)
            return;

        if (requestCode == REQUEST_CODE_CHEAT) {
            mIscheater = data.getBooleanExtra(CheatActivity.EXTRA_IS_CHEAT, false);
            indexcheater = data.getIntExtra(CheatActivity.EXTRA_MCURRENTINDEX, 10);
        }


        if (requestCode == REQUEST_CODE_SETTING) {
            sizeoftextQuestion = data.getIntExtra(SIZEOFTEXTQUESTION, 16);
            colorofbackground = data.getIntExtra(COLOROFBACKGROUND, 0);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENTINDEX, mcurrentindex);
        outState.putInt(SCORE, score);
        outState.putBooleanArray(IS_ANSWER, isAnswer);
        outState.putBoolean(M_IS_CHEATER, mIscheater);
        outState.putInt(INDEXCHEATER, indexcheater);
        outState.putInt(SIZEOFTEXTQUESTION, sizeoftextQuestion);
        outState.putInt(COLOROFBACKGROUND, colorofbackground);


    }

    private void setVisibility() {
        boolean flag = false;
        for (int i = 0; i < isAnswer.length; i++) {
            if (isAnswer[i] == false) {
                flag = true;
            }
        }
        if (flag == false) {
            linearLayout.setVisibility(LinearLayout.GONE);
            linearLayoutnext.setVisibility(LinearLayout.GONE);
            linearLayoutpre.setVisibility(LinearLayout.GONE);
            btnreset.setVisibility(findViewById(R.id.btn_reset).VISIBLE);
        }

    }


    private void setScore(String s) {
        btn_score.setText(s);
    }

    private void setquestion() {
        btnreset.setVisibility(findViewById(R.id.btn_reset).INVISIBLE);

        int questionId = mquestion[mcurrentindex].getmQuestiontextResId();

        if (isAnswer[mcurrentindex] == false) {
            EnableButton();
        } else if (isAnswer[mcurrentindex] == true) {
            disableButton();
        }

        mtextviewQuestion.setText(questionId);

    }

    private void setSizeQuestiionText() {
        if (sizeoftextQuestion == 14) {
            mtextviewQuestion.setTextSize(14);
        }
        if (sizeoftextQuestion == 18) {
            mtextviewQuestion.setTextSize(18);
        }
        if (sizeoftextQuestion == 26) {
            mtextviewQuestion.setTextSize(26);
        }
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
                mcurrentindex = (mcurrentindex + 1) % mquestion.length;
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
                mcurrentindex = 0;
                setquestion();
            }
        });


        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                linearLayout.setVisibility(LinearLayout.VISIBLE);
                linearLayoutnext.setVisibility(LinearLayout.VISIBLE);
                linearLayoutpre.setVisibility(LinearLayout.VISIBLE);
                for (int i = 0; i < isAnswer.length; i++) {
                    isAnswer[i] = false;
                }
                setquestion();
                score = 0;
                makestr();
                setScore(str);
            }
        });


        btn_cheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GeoQuiz_Activity.this, CheatActivity.class);
                intent.putExtra(EXTRA_QUESTION_A_NSWER, mquestion[mcurrentindex].ismIsAnswerTrue());
                intent.putExtra(EXTRA_MCURRENTINDEX, mcurrentindex);
                startActivityForResult(intent, REQUEST_CODE_CHEAT);
            }
        });


        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GeoQuiz_Activity.this, SettingActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SETTING);


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
        linearLayout = (LinearLayout) this.findViewById(R.id.hideUI);
        linearLayoutpre = (LinearLayout) this.findViewById(R.id.pre_layout);
        linearLayoutnext = (LinearLayout) this.findViewById(R.id.next_layout);
        linearLayoutmtxtquestion = findViewById(R.id.layout_txtquestion);
        btn_cheat = findViewById(R.id.cheat_btn);
        btn_setting = findViewById(R.id.btn_setting);
        frameLayoutmtxtquestion = findViewById(R.id.frame_txtquestion);
    }


    private void checkAnswer(boolean userpressed) {

        final LinearLayout layouttrue = new LinearLayout(this);
        final TextView textViewtrue = new TextView(this);
        final Toast toasttrue = new Toast(getApplicationContext());
        final ImageView imageViewtrue = new ImageView(this);

        final LinearLayout layoutfalse = new LinearLayout(this);
        final TextView textViewfalse = new TextView(this);
        final Toast toastfalse = new Toast(getApplicationContext());
        final ImageView imageViewfalse = new ImageView(this);

        if (mIscheater && indexcheater == mcurrentindex) {
            Toast.makeText(this, R.string.judgment, Toast.LENGTH_SHORT).show();
            isAnswer[mcurrentindex] = true;
            disableButton();
            setVisibility();
            if (mquestion[mcurrentindex].ismIsAnswerTrue() == userpressed) {
                score++;
                makestr();
            } else {
                makestr();
            }


        } else {
            if (mquestion[mcurrentindex].ismIsAnswerTrue() == userpressed) {
                score++;
                makestr();
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
                setVisibility();
            } else if (mquestion[mcurrentindex].ismIsAnswerTrue() != userpressed) {
                makestr();
                textViewfalse.setText(R.string.false_message);
                textViewfalse.setTextColor(Color.RED);
                toastfalse.setDuration(Toast.LENGTH_SHORT);
                imageViewfalse.setImageResource(R.drawable.ic_false);
                layoutfalse.removeAllViews();
                layoutfalse.addView(textViewfalse);
                layoutfalse.addView(imageViewfalse);
                toastfalse.setView(layoutfalse);
                toastfalse.setGravity(Gravity.CENTER, 0, -400);
                toastfalse.show();
                isAnswer[mcurrentindex] = true;
                disableButton();
                setVisibility();
            }
        }


    }

    private void makestr() {
        scorestr = String.valueOf(score);
        str = "امتیاز: " + scorestr;
    }


}