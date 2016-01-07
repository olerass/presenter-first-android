package com.olerass.pfexample.android.geoquiz.platform;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;

import com.olerass.pfexample.android.geoquiz.R;
import com.olerass.pfexample.android.geoquiz.domain.cheat.CheatView;
import com.olerass.pfexample.android.geoquiz.domain.event.Event;
import com.olerass.pfexample.android.geoquiz.domain.event.EventListener;

public class AndroidCheatView implements CheatView {
    private TextView answerTextView;
    private Button showAnswerButton;
    private Event showAnswerEvent = new Event();

    public AndroidCheatView(Activity base) {
        answerTextView = (TextView)base.findViewById(R.id.answer_text_view);

        showAnswerButton = (Button)base.findViewById(R.id.show_answer_button);
        showAnswerButton.setOnClickListener(v -> showAnswerEvent.dispatch());
    }

    @Override
    public void whenShowAnswerRequested(EventListener listener) {
        showAnswerEvent.addListener(listener);
    }

    @Override
    public void setAnswer(String answer) {
        answerTextView.setText(answer);
    }
}
