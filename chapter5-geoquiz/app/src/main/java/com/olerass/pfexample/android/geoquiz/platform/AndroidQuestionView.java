package com.olerass.pfexample.android.geoquiz.platform;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.olerass.pfexample.android.geoquiz.R;
import com.olerass.pfexample.android.geoquiz.domain.event.DataEvent;
import com.olerass.pfexample.android.geoquiz.domain.event.DataEventListener;
import com.olerass.pfexample.android.geoquiz.domain.event.Event;
import com.olerass.pfexample.android.geoquiz.domain.event.EventListener;
import com.olerass.pfexample.android.geoquiz.domain.question.QuestionView;

public class AndroidQuestionView implements QuestionView {
    private Activity base;
    private TextView questionTextView;
    private Button trueButton;
    private Button falseButton;
    private Button nextButton;
    private Button cheatButton;
    private DataEvent<Boolean> answerEvent = new DataEvent<>();
    private Event nextQuestionEvent = new Event();
    private Event cheatEvent = new Event();

    public AndroidQuestionView(Activity base) {
        this.base = base;
        questionTextView = (TextView)base.findViewById(R.id.question_text_view);

        trueButton = (Button)base.findViewById(R.id.true_button);
        trueButton.setOnClickListener(v -> answerEvent.dispatch(true));

        falseButton = (Button)base.findViewById(R.id.false_button);
        falseButton.setOnClickListener(v -> answerEvent.dispatch(false));

        nextButton = (Button)base.findViewById(R.id.next_button);
        nextButton.setOnClickListener(v -> nextQuestionEvent.dispatch());

        cheatButton = (Button)base.findViewById(R.id.cheat_button);
        cheatButton.setOnClickListener(v -> cheatEvent.dispatch());
    }

    @Override
    public void whenAnswerGiven(DataEventListener<Boolean> listener) {
        answerEvent.addListener(listener);
    }

    @Override
    public void whenNextQuestionRequested(EventListener listener) {
        nextQuestionEvent.addListener(listener);
    }

    @Override
    public void whenCheatRequested(EventListener listener) {
        cheatEvent.addListener(listener);
    }

    @Override
    public void setQuestionText(String question) {
        questionTextView.setText(question);
    }

    @Override
    public void showCorrectAnswerMessage() {
        showToast(R.string.correct_msg);
    }

    @Override
    public void showIncorrectAnswerMessage() {
        showToast(R.string.incorrect_msg);
    }

    @Override
    public void showCheaterMessage() {
        showToast(R.string.judgment_toast);
    }

    private void showToast(int msgId) {
        Toast.makeText(base, msgId, Toast.LENGTH_SHORT).show();
    }
}
