package com.olerass.pfexample.android.geoquiz.platform;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Build;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.olerass.pfexample.android.geoquiz.R;
import com.olerass.pfexample.android.geoquiz.domain.cheat.CheatView;
import com.olerass.pfexample.android.geoquiz.domain.event.Event;
import com.olerass.pfexample.android.geoquiz.domain.event.EventListener;

public class AndroidCheatView implements CheatView {
    private TextView answerTextView;
    private Button showAnswerButton;
    private TextView apiLevelTextView;
    private Event showAnswerEvent = new Event();

    public AndroidCheatView(Activity base) {
        answerTextView = (TextView)base.findViewById(R.id.answer_text_view);

        apiLevelTextView = (TextView)base.findViewById(R.id.api_level_text_view);
        apiLevelTextView.setText(base.getString(R.string.api_level, Build.VERSION.SDK_INT));

        showAnswerButton = (Button)base.findViewById(R.id.show_answer_button);
        showAnswerButton.setOnClickListener(v -> {
            showAnswerEvent.dispatch();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                animateShowAnswerButton();
            }
        });
    }

    @Override
    public void whenShowAnswerRequested(EventListener listener) {
        showAnswerEvent.addListener(listener);
    }

    @Override
    public void setAnswer(String answer) {
        answerTextView.setText(answer);
    }

    private void animateShowAnswerButton() {
        int cx = showAnswerButton.getWidth() / 2;
        int cy = showAnswerButton.getHeight() / 2;
        float radius = showAnswerButton.getWidth();
        Animator anim = ViewAnimationUtils.createCircularReveal(showAnswerButton, cx, cy, radius, 0);
        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                showAnswerButton.setVisibility(View.INVISIBLE);
            }
        });
        anim.start();
    }
}