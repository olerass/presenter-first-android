package com.olerass.pfexample.android.geoquiz.domain.cheat;

import com.olerass.pfexample.android.geoquiz.domain.event.EventListener;

public interface CheatView {
    void setAnswer(String answer);

    void whenShowAnswerRequested(EventListener listener);
}