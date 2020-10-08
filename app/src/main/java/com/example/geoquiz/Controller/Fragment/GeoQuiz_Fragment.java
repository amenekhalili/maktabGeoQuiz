package com.example.geoquiz.Controller.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.geoquiz.Controller.Activity.SettingActivity;
import com.example.geoquiz.Model.Question;
import com.example.geoquiz.R;
import com.example.geoquiz.Repository.QuestionRepository;

import java.util.UUID;

import static androidx.core.content.ContextCompat.getColor;
import static com.example.geoquiz.Controller.Activity.SettingActivity.COLOROFBACKGROUND;
import static com.example.geoquiz.Controller.Activity.SettingActivity.SIZEOFTEXTQUESTION;


public class GeoQuiz_Fragment<list> extends Fragment {
    private static final String CURRENTINDEX = "currentindex";

    private static final String SCORE = "score";
    public static final String EXTRA_QUESTION_A_NSWER = " com.example.geoquiz.Question_Answer";
    private static final int REQUEST_CODE_CHEAT = 0;
    public static final String EXTRA_MCURRENTINDEX = "com.example.geoquiz.mcurrentindex";
    public static final String M_IS_CHEATER = "mIsCheater";
    public static final String INDEXCHEATER = "indexcheater";
    public static final int REQUEST_CODE_SETTING = 1;
    private static final String EXTRA_IS_CHEAT = "is_cheat";
    public static final String ISANSWER = "isanswer";
    private int sizeoftextQuestion;
    private int colorofbackground;
    private Button btn_true;
    private Button btn_false;
    private ImageButton btn_next;
    private Button btn_cheat;
    private ImageButton btn_pre;
    private ImageButton btn_doublenext;
    private ImageButton btn_doublepre;
    private int mcurrentindex;
    private TextView mtextviewQuestion;
    private int score = 0;
    private Button btn_score;
    private String scorestr;
    private String str;
    private ImageButton btnreset;
    private boolean mIscheater;
    private int indexcheater;
    private Button btn_setting;
    private LinearLayout linearLayout;
    private LinearLayout linearLayoutpre;
    private LinearLayout linearLayoutnext;
    private LinearLayout linearLayoutmtxtquestion;
    private FrameLayout frameLayoutmtxtquestion;
    private UUID id;
    private Question mQuestion;
    QuestionRepository questionRepository = QuestionRepository.getInstance();
    private final int size = questionRepository.QuestionSize();
    private boolean[] isAnswer = new boolean[size];


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        for (int i = 0; i < isAnswer.length; i++) {
            isAnswer[i] = false;
        }

        getIdandsetit();


        if (savedInstanceState != null) {
            mcurrentindex = savedInstanceState.getInt(CURRENTINDEX, 0);
            score = savedInstanceState.getInt(SCORE, 0);
            isAnswer = savedInstanceState.getBooleanArray(ISANSWER);
            indexcheater = savedInstanceState.getInt(INDEXCHEATER, 10);
            mIscheater = savedInstanceState.getBoolean(M_IS_CHEATER, false);
            sizeoftextQuestion = savedInstanceState.getInt(SIZEOFTEXTQUESTION, 16);
            colorofbackground = savedInstanceState.getInt(COLOROFBACKGROUND, 0);

        }

    }

    private void getIdandsetit() {
        id = (UUID) getActivity().getIntent().getSerializableExtra(QuestionlistFragment.QUESTION_ID);
        mQuestion = questionRepository.getQuestion(id);
        mcurrentindex = questionRepository.getPosition(id);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_geo_quiz_, container, false);


        Bundle bundle = getArguments();
        if (bundle != null) {
            mIscheater = bundle.getBoolean(EXTRA_IS_CHEAT, false);
            indexcheater = bundle.getInt(EXTRA_MCURRENTINDEX, 10);
            mcurrentindex = bundle.getInt(EXTRA_MCURRENTINDEX, 10);
            score = bundle.getInt(SCORE, score);
            isAnswer = bundle.getBooleanArray(ISANSWER);
        }

        findview(view);
        setListener(view);
        setquestion(view);
        makestr();
        setScore(str);
        setVisibility(view);


        return view;
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onResume() {
        super.onResume();
        setSizeQuestiionText();
        setColorofbackground();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setColorofbackground() {
        Fragment fragment = (Fragment) getFragmentManager().findFragmentById(R.id.container_QeoQuiz_fragment);


        if (colorofbackground == 3) {
            fragment.getView().setBackgroundColor(getColor(getContext(), R.color.lightRed));

        }
        if (colorofbackground == 2) {
            fragment.getView().setBackgroundColor(getColor(getContext(), R.color.lightGreen));
        }
        if (colorofbackground == 1) {
            fragment.getView().setBackgroundColor(getColor(getContext(), R.color.lightBlue));
        }
        if (colorofbackground == 4) {
            fragment.getView().setBackgroundColor(getColor(getContext(), R.color.White));


        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK || data == null)
            return;

        if (requestCode == REQUEST_CODE_SETTING) {
            sizeoftextQuestion = data.getIntExtra(SIZEOFTEXTQUESTION, 16);
            colorofbackground = data.getIntExtra(COLOROFBACKGROUND, 0);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENTINDEX, mcurrentindex);
        outState.putInt(SCORE, score);
        outState.putBooleanArray(ISANSWER, isAnswer);
        outState.putBoolean(M_IS_CHEATER, mIscheater);
        outState.putInt(INDEXCHEATER, indexcheater);
        outState.putInt(SIZEOFTEXTQUESTION, sizeoftextQuestion);
        outState.putInt(COLOROFBACKGROUND, colorofbackground);


    }

    private void setVisibility(View view) {
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
            btnreset.setVisibility(view.findViewById(R.id.btn_reset).VISIBLE);
        }

    }


    private void setScore(String s) {
        btn_score.setText(s);
    }


    private void setquestion(View view) {

        btnreset.setVisibility(view.findViewById(R.id.btn_reset).INVISIBLE);


        int questionId = questionRepository.getQuestion(mcurrentindex).getmQuestiontextResId();

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


    private void setListener(final View view) {


        final View v = view;
        btn_true.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true, v);
                setScore(str);
            }
        });

        btn_false.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false, v);
                setScore(str);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mcurrentindex = (mcurrentindex + 1) % size;
                setquestion(view);
            }
        });

        btn_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mcurrentindex = (mcurrentindex - 1 + size) % size;
                setquestion(view);
            }
        });


        btn_doublenext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mcurrentindex = 5;
                setquestion(view);
            }
        });

        btn_doublepre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mcurrentindex = 0;
                setquestion(view);
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
                setquestion(view);
                score = 0;
                makestr();
                setScore(str);
            }
        });


        btn_cheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Bundle bundle = new Bundle();
                bundle.putBoolean(EXTRA_QUESTION_A_NSWER, questionRepository.getQuestion(mcurrentindex).ismIsAnswerTrue());
                bundle.putInt(EXTRA_MCURRENTINDEX, mcurrentindex);
                bundle.putInt(SCORE, score);
                bundle.putBooleanArray(ISANSWER, isAnswer);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                Cheat_Fragment cheat_fragment = new Cheat_Fragment();
                cheat_fragment.setArguments(bundle);
                fragmentManager.beginTransaction()
                        .replace(R.id.container_QeoQuiz_fragment, cheat_fragment)
                        .commit();


            }
        });


        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivityForResult(intent, REQUEST_CODE_SETTING);


            }
        });
    }

    private void findview(View view) {
        btn_true = view.findViewById(R.id.btn_true);
        btn_false = view.findViewById(R.id.btn_false);
        btn_next = view.findViewById(R.id.btn_next);
        btn_pre = view.findViewById(R.id.btn_pre);
        btn_doublenext = view.findViewById(R.id.btn_doublenext);
        btn_doublepre = view.findViewById(R.id.btn_doublepre);
        mtextviewQuestion = view.findViewById(R.id.txt_questiom);
        btn_score = view.findViewById(R.id.btn_score);
        btnreset = view.findViewById(R.id.btn_reset);
        linearLayout = view.findViewById(R.id.hideUI);
        linearLayoutpre = view.findViewById(R.id.pre_layout);
        linearLayoutnext = view.findViewById(R.id.next_layout);
        linearLayoutmtxtquestion = view.findViewById(R.id.layout_txtquestion);
        btn_cheat = view.findViewById(R.id.cheat_btn);
        btn_setting = view.findViewById(R.id.btn_setting);
        frameLayoutmtxtquestion = view.findViewById(R.id.container_QeoQuiz_fragment);
    }


    private void checkAnswer(boolean userpressed, View view) {

        final LinearLayout layouttrue = new LinearLayout(getActivity());
        final TextView textViewtrue = new TextView(getActivity());
        final Toast toasttrue = new Toast(getContext());
        final ImageView imageViewtrue = new ImageView(getActivity());

        final LinearLayout layoutfalse = new LinearLayout(getActivity());
        final TextView textViewfalse = new TextView(getActivity());
        final Toast toastfalse = new Toast(getContext());
        final ImageView imageViewfalse = new ImageView(getActivity());

        final Toast toastcheat = new Toast(getContext());
        final TextView textViewcheat = new TextView(getActivity());
        final LinearLayout layoutcheat = new LinearLayout(getActivity());

        if (mIscheater && indexcheater == mcurrentindex) {

            textViewcheat.setText(R.string.judgment);
            toastcheat.setDuration(Toast.LENGTH_SHORT);
            layoutcheat.removeAllViews();
            layoutcheat.addView(textViewcheat);
            toastcheat.setView(layoutcheat);
            toastcheat.show();
            isAnswer[mcurrentindex] = true;
            disableButton();
            setVisibility(view);

            if (questionRepository.getQuestion(mcurrentindex).ismIsAnswerTrue() == userpressed) {
                score++;
                makestr();
            } else {
                makestr();
            }


        } else {
            if (questionRepository.getQuestion(mcurrentindex).ismIsAnswerTrue() == userpressed) {
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
                setVisibility(view);
            } else if (questionRepository.getQuestion(mcurrentindex).ismIsAnswerTrue() != userpressed) {
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
                setVisibility(view);
            }
        }


    }

    private void makestr() {
        scorestr = String.valueOf(score);
        str = "امتیاز: " + scorestr;
    }


}