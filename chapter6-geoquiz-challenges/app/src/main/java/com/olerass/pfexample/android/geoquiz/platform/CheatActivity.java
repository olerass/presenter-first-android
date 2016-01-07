package com.olerass.pfexample.android.geoquiz.platform;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.olerass.pfexample.android.geoquiz.R;
import com.olerass.pfexample.android.geoquiz.domain.cheat.CheatPresenter;
import com.olerass.pfexample.android.geoquiz.domain.cheat.CheatView;
import com.olerass.pfexample.android.geoquiz.domain.cheat.StandardCheatModel;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER = "com.olerass.pfexample.android.geoquiz.answer";
    private static final String EXTRA_ANSWER_SHOWN = "com.olerass.pfexample.android.geoquiz.answerShown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        StandardCheatModel model = new StandardCheatModel(answerFromIntent());
        CheatView view = new AndroidCheatView(this);
        new CheatPresenter(view, model);

        model.whenAnswerShownChanged(() -> setAnswerShownResult(model.isAnswerShown()));
    }

    public static Intent newIntent(Context packageContext, boolean answer) {
        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER, answer);
        return i;
    }

    public static boolean wasAnswerShown(Intent result) {
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }

    private String answerFromIntent() {
        boolean answer = getIntent().getBooleanExtra(EXTRA_ANSWER, false);
        return getString(answer ? R.string.true_str : R.string.false_str);
    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }
}
