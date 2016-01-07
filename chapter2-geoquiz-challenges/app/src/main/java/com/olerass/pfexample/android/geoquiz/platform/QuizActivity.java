package com.olerass.pfexample.android.geoquiz.platform;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.olerass.pfexample.android.geoquiz.R;
import com.olerass.pfexample.android.geoquiz.domain.question.Question;
import com.olerass.pfexample.android.geoquiz.domain.question.QuestionModel;
import com.olerass.pfexample.android.geoquiz.domain.question.QuestionPresenter;
import com.olerass.pfexample.android.geoquiz.domain.question.QuestionView;
import com.olerass.pfexample.android.geoquiz.domain.question.StandardQuestionModel;

public class QuizActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        QuestionModel model = new StandardQuestionModel(new Question[] {
                new Question(getString(R.string.question_oceans), true),
                new Question(getString(R.string.question_mideast), false),
                new Question(getString(R.string.question_africa), false),
                new Question(getString(R.string.question_americas), true),
                new Question(getString(R.string.question_asia), true)
        });
        QuestionView view = new AndroidQuestionView(this);
        new QuestionPresenter(view, model);
    }
}
