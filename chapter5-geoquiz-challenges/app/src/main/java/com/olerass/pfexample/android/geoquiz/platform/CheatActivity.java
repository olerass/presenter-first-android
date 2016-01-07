package com.olerass.pfexample.android.geoquiz.platform;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.olerass.pfexample.android.geoquiz.R;
import com.olerass.pfexample.android.geoquiz.domain.cheat.CheatPresenter;
import com.olerass.pfexample.android.geoquiz.domain.cheat.CheatView;
import com.olerass.pfexample.android.geoquiz.domain.cheat.StandardCheatModel;
import com.olerass.pfexample.android.geoquiz.domain.state.AndroidStateManager;

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER = "com.olerass.pfexample.android.geoquiz.answer";
    private static final String EXTRA_ANSWER_SHOWN = "com.olerass.pfexample.android.geoquiz.answerShown";
    private StandardCheatModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        model = new StandardCheatModel(answerFromIntent());
        model.whenAnswerShownChanged(() -> setAnswerShownResult(model.isAnswerShown())); // Subscribe before loading state to ensure we get event when loading
        model.loadState(new AndroidStateManager(savedInstanceState));

        CheatView view = new AndroidCheatView(this);
        new CheatPresenter(view, model);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        model.saveState(new AndroidStateManager(outState));
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
