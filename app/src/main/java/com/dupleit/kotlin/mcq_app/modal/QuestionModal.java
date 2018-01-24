package com.dupleit.kotlin.mcq_app.modal;

/**
 * Created by android on 23/1/18.
 */
public class QuestionModal{

    private String QUESTION_ID;

    private String QUESTION_TEXT;

    private String QUESTION_OPTION_1;

    private String QUESTION_OPTION_2;

    private String QUESTION_OPTION_3;

    private String QUESTION_OPTION_4;

    private String QUESTION_CORRECT_OPTION;

    private String DATETIME;

    private boolean IsAttempted = false;

    private String processStart = "notStarted";

    public QuestionModal(String QUESTION_ID, String QUESTION_TEXT, String QUESTION_OPTION_1, String QUESTION_OPTION_2, String QUESTION_OPTION_3, String QUESTION_OPTION_4, String QUESTION_CORRECT_OPTION, String DATETIME, boolean isAttempted, String processStart) {
        this.QUESTION_ID = QUESTION_ID;
        this.QUESTION_TEXT = QUESTION_TEXT;
        this.QUESTION_OPTION_1 = QUESTION_OPTION_1;
        this.QUESTION_OPTION_2 = QUESTION_OPTION_2;
        this.QUESTION_OPTION_3 = QUESTION_OPTION_3;
        this.QUESTION_OPTION_4 = QUESTION_OPTION_4;
        this.QUESTION_CORRECT_OPTION = QUESTION_CORRECT_OPTION;
        this.DATETIME = DATETIME;
        IsAttempted = isAttempted;
        this.processStart = processStart;
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

    public String getQUESTION_OPTION_2 (){
        return QUESTION_OPTION_2;
    }

    public void setQUESTION_OPTION_2 (String QUESTION_OPTION_2){
        this.QUESTION_OPTION_2 = QUESTION_OPTION_2;
    }

    public String getQUESTION_OPTION_1 (){
        return QUESTION_OPTION_1;
    }

    public void setQUESTION_OPTION_1 (String QUESTION_OPTION_1){
        this.QUESTION_OPTION_1 = QUESTION_OPTION_1;
    }

    public String getQUESTION_CORRECT_OPTION (){
        return QUESTION_CORRECT_OPTION;
    }

    public void setQUESTION_CORRECT_OPTION (String QUESTION_CORRECT_OPTION){
        this.QUESTION_CORRECT_OPTION = QUESTION_CORRECT_OPTION;
    }

    public String getQUESTION_OPTION_4 (){
        return QUESTION_OPTION_4;
    }

    public void setQUESTION_OPTION_4 (String QUESTION_OPTION_4){
        this.QUESTION_OPTION_4 = QUESTION_OPTION_4;
    }

    public String getQUESTION_TEXT (){
        return QUESTION_TEXT;
    }

    public void setQUESTION_TEXT (String QUESTION_TEXT){
        this.QUESTION_TEXT = QUESTION_TEXT;
    }

    public String getQUESTION_OPTION_3 (){
        return QUESTION_OPTION_3;
    }

    public void setQUESTION_OPTION_3 (String QUESTION_OPTION_3){
        this.QUESTION_OPTION_3 = QUESTION_OPTION_3;
    }

    public String getDATETIME (){
        return DATETIME;
    }

    public void setDATETIME (String DATETIME){
        this.DATETIME = DATETIME;
    }

    public String getQUESTION_ID (){
        return QUESTION_ID;
    }

    public void setQUESTION_ID (String QUESTION_ID){
        this.QUESTION_ID = QUESTION_ID;
    }

    @Override
    public String toString(){
        return "ClassPojo [QUESTION_OPTION_2 = "+QUESTION_OPTION_2+", QUESTION_OPTION_1 = "+QUESTION_OPTION_1+", QUESTION_CORRECT_OPTION = "+QUESTION_CORRECT_OPTION+", QUESTION_OPTION_4 = "+QUESTION_OPTION_4+", QUESTION_TEXT = "+QUESTION_TEXT+", QUESTION_OPTION_3 = "+QUESTION_OPTION_3+", DATETIME = "+DATETIME+", QUESTION_ID = "+QUESTION_ID+"]";
    }
}


