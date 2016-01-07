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

        QuestionModel model = new StandardQuestionModel(new Question(getString(R.string.question_text), false));
        QuestionView view = new AndroidQuestionView(this);
        new QuestionPresenter(view, model);
    }
}
