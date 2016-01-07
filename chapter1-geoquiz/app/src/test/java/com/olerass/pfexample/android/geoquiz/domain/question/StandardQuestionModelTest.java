package com.olerass.pfexample.android.geoquiz.domain.question;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StandardQuestionModelTest {
    @Test
    public void getQuestionText_returnsTextForTheQuestionPassedIntoConstructor_whenCalled() {
        StandardQuestionModel model = new StandardQuestionModel(new Question("the question", true));
        assertEquals("the question", model.getQuestionText());
    }

    @Test
    public void isCorrectAnswer_returnsTrueWhenAnswerMatchesCorrectAnswer_whenCalled() {
        StandardQuestionModel model = new StandardQuestionModel(new Question("the question", true));
        assertEquals(true, model.isCorrectAnswer(true));
    }

    @Test
    public void isCorrectAnswer_returnsFalseWhenAnswerDoesntMatchCorrectAnswer_whenCalled() {
        StandardQuestionModel model = new StandardQuestionModel(new Question("the question", true));
        assertEquals(false, model.isCorrectAnswer(false));
    }
}
