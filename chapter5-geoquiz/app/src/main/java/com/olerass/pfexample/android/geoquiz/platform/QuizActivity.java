package com.olerass.pfexample.android.geoquiz.platform;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.olerass.pfexample.android.geoquiz.R;
import com.olerass.pfexample.android.geoquiz.domain.question.Question;
import com.olerass.pfexample.android.geoquiz.domain.question.QuestionPresenter;
import com.olerass.pfexample.android.geoquiz.domain.question.QuestionView;
import com.olerass.pfexample.android.geoquiz.domain.question.StandardQuestionModel;
import com.olerass.pfexample.android.geoquiz.domain.state.AndroidStateManager;

public class QuizActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_CHEAT = 0;
    private StandardQuestionModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        model = new StandardQuestionModel(new Question[] {
                new Question(getString(R.string.question_oceans), true),
                new Question(getString(R.string.question_mideast), false),
                new Question(getString(R.string.question_africa), false),
                new Question(getString(R.string.question_americas), true),
                new Question(getString(R.string.question_asia), true)
        });
        model.loadState(new AndroidStateManager(savedInstanceState));
        QuestionView view = new AndroidQuestionView(this);
        new QuestionPresenter(view, model);

        model.whenCheatRequested((answer) -> {
            Intent i = CheatActivity.newIntent(QuizActivity.this, answer);
            startActivityForResult(i, REQUEST_CODE_CHEAT);
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        model.saveState(new AndroidStateManager(outState));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            boolean isCheater = CheatActivity.wasAnswerShown(data);
            model.setCheater(isCheater);
        }
    }
}
