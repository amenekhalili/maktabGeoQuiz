package com.example.geoquiz.Controller;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.geoquiz.R;

import static android.app.Activity.RESULT_OK;
import static android.os.SystemClock.sleep;
import static com.example.geoquiz.Controller.GeoQuiz_Fragment.EXTRA_MCURRENTINDEX;
import static com.example.geoquiz.Controller.GeoQuiz_Fragment.EXTRA_QUESTION_A_NSWER;
import static com.example.geoquiz.Controller.GeoQuiz_Fragment.ISANSWER;

public class Cheat_Fragment extends Fragment {

    public static final String M_IS_ANSWERTRUE = "mIsAnswertrue";
    public static final String MCURRENTINDEX = "mcurrentindex";
    public static final String FLAG = "flag";
    private static final String EXTRA_IS_CHEAT ="is_cheat" ;
    public static final String Score  = "score";
    private Button mbtn_showcheat;
    private TextView mtxtview_Answer;
    private Button btn_back;
    private boolean mIsanswertrue;
    int [] mcurrentindex = {10,10,10,10,10,10};
    private int mcurrent = 0;
    private boolean flag = false;
    private  int score;
    boolean[] isAnswer = {false, false, false, false, false, false};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){
            mIsanswertrue = savedInstanceState.getBoolean(M_IS_ANSWERTRUE , false);
            flag = savedInstanceState.getBoolean(FLAG , false);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_cheat_ , container , false);

       Bundle bundle = getArguments();
            if(bundle != null){
                mIsanswertrue = bundle.getBoolean(EXTRA_QUESTION_A_NSWER,false);
                mcurrentindex[mcurrent] = bundle.getInt(EXTRA_MCURRENTINDEX , 10);
                 score = bundle.getInt(Score, score) ;
                 isAnswer = bundle.getBooleanArray(ISANSWER);
            }



        findView(view);
        setlistener();

       return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        if(flag == true ){
            showAnswerforCheater();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(M_IS_ANSWERTRUE, mIsanswertrue);
        outState.putBoolean(FLAG, flag);
    }

    private void setlistener() {
        mbtn_showcheat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAnswerforCheater();
                flag = true;
               // setshowmAnswerResult(true , mcurrentindex[mcurrent]);

            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setshowmAnswerResult(true , mcurrentindex[mcurrent]);

            }
        });
    }

    private void showAnswerforCheater() {
        if (mIsanswertrue)
            mtxtview_Answer.setText(R.string.str_true);
        else
            mtxtview_Answer.setText(R.string.str_false);
    }

    private void setshowmAnswerResult(boolean ischeat , int mcurrentindex) {


            Bundle bundle = new Bundle();
            bundle.putInt(EXTRA_MCURRENTINDEX , mcurrentindex);
            bundle.putBoolean(EXTRA_IS_CHEAT ,ischeat);
            bundle.putInt(Score , score);
            bundle.putBooleanArray(ISANSWER , isAnswer);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            GeoQuiz_Fragment geoQuiz_fragment = new GeoQuiz_Fragment();
            geoQuiz_fragment.setArguments(bundle);

        fragmentManager.beginTransaction()
                .replace(R.id.container_QeoQuiz_fragment , geoQuiz_fragment)
                .commit();

    }


    private void findView(View view) {
        mbtn_showcheat = view.findViewById(R.id.show_cheat);
        mtxtview_Answer = view.findViewById(R.id.answer_txtview);
        btn_back = view.findViewById(R.id.btn_back);
    }
}