package com.example.geoquiz.Repository;

import com.example.geoquiz.Model.Question;
import com.example.geoquiz.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestionRepository {

    private static QuestionRepository sInstance;

    public static QuestionRepository getInstance() {

        if (sInstance == null) {
            sInstance = new QuestionRepository();
        }
        return sInstance;

    }

    private List<Question> mQuestions;


    private QuestionRepository() {
        mQuestions = new ArrayList<>();

        Question question1 = new Question(R.string.question_australia, false);
        Question question2 = new Question(R.string.question_oceans, true);
        Question question3 = new Question(R.string.question_mideast, false);
        Question question4 = new Question(R.string.question_africa, true);
        Question question5 = new Question(R.string.question_americas, false);
        Question question6 = new Question(R.string.question_asia, false);

        mQuestions.add(question1);
        mQuestions.add(question2);
        mQuestions.add(question3);
        mQuestions.add(question4);
        mQuestions.add(question5);
        mQuestions.add(question6);


    }


    public List<Question> getQuestions() {
        return mQuestions;
    }

    public void setQuestions(List<Question> questions) {
        mQuestions = questions;
    }

    public void insertQuestion(Question question) {
        mQuestions.add(question);
    }

    public Question getQuestion(UUID id) {

        for (Question question : mQuestions) {
            if (question.getUUID().equals(id))
                return question;
        }
        return null;
    }


}
