package com.dupleit.kotlin.mcq_app.modal;

import com.dupleit.kotlin.mcq_app.utils.constants;

/**
 * Created by android on 23/1/18.
 */
public class QuestionModal{

    Question_Data userQuestion;

    private boolean IsAttempted = false;

    private String processStart = constants.notStarted;

    private  int answerProvided;
    private int timeCounter;
    private boolean ismarked;

    public QuestionModal(Question_Data userQuestion, boolean isAttempted) {
        this.userQuestion = userQuestion;
        this.IsAttempted = isAttempted;
        this.timeCounter = 0;
        this.ismarked = false;
    }

    public Question_Data getUserQuestion() {
        return userQuestion;
    }

    public void setUserQuestion(Question_Data userQuestion) {
        this.userQuestion = userQuestion;
    }

    public boolean isAttempted() {
        return IsAttempted;
    }

    public void setAttempted(boolean attempted) {
        IsAttempted = attempted;
    }

    public String getProcessStart() {
        return processStart;
    }

    public void setProcessStart(String processStart) {
        this.processStart = processStart;
    }

    public int getAnswerProvided() {
        return answerProvided;
    }

    public void setAnswerProvided(int answerProvided) {
        this.answerProvided = answerProvided;
    }

    public int getTimeCounter() {
        return timeCounter;
    }

    public void setTimeCounter(int timeCounter) {
        this.timeCounter = timeCounter;
    }

    public boolean isIsmarked() {
        return ismarked;
    }

    public void setIsmarked(boolean ismarked) {
        this.ismarked = ismarked;
    }
}


