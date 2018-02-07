
package com.dupleit.kotlin.mcq_app.modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Question_Data {

    @SerializedName("QUESTION_ID")
    @Expose
    private String qUESTIONID;
    @SerializedName("QUESTION_TEXT")
    @Expose
    private String qUESTIONTEXT;
    @SerializedName("QUESTION_OPTION_1")
    @Expose
    private String qUESTIONOPTION1;
    @SerializedName("QUESTION_OPTION_2")
    @Expose
    private String qUESTIONOPTION2;
    @SerializedName("QUESTION_OPTION_3")
    @Expose
    private String qUESTIONOPTION3;
    @SerializedName("QUESTION_OPTION_4")
    @Expose
    private String qUESTIONOPTION4;
    @SerializedName("QUESTION_CORRECT_OPTION")
    @Expose
    private String qUESTIONCORRECTOPTION;
    @SerializedName("DATETIME")
    @Expose
    private String dATETIME;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Question_Data() {
    }

    /**
     * 
     * @param qUESTIONTEXT
     * @param qUESTIONOPTION3
     * @param qUESTIONOPTION4
     * @param qUESTIONOPTION1
     * @param dATETIME
     * @param qUESTIONCORRECTOPTION
     * @param qUESTIONOPTION2
     * @param qUESTIONID
     */
    public Question_Data(String qUESTIONID, String qUESTIONTEXT, String qUESTIONOPTION1, String qUESTIONOPTION2, String qUESTIONOPTION3, String qUESTIONOPTION4, String qUESTIONCORRECTOPTION, String dATETIME) {
        super();
        this.qUESTIONID = qUESTIONID;
        this.qUESTIONTEXT = qUESTIONTEXT;
        this.qUESTIONOPTION1 = qUESTIONOPTION1;
        this.qUESTIONOPTION2 = qUESTIONOPTION2;
        this.qUESTIONOPTION3 = qUESTIONOPTION3;
        this.qUESTIONOPTION4 = qUESTIONOPTION4;
        this.qUESTIONCORRECTOPTION = qUESTIONCORRECTOPTION;
        this.dATETIME = dATETIME;
    }

    public String getQUESTIONID() {
        return qUESTIONID;
    }

    public String getQUESTIONTEXT() {
        return qUESTIONTEXT;
    }

    public String getQUESTIONOPTION1() {
        return qUESTIONOPTION1;
    }

    public String getQUESTIONOPTION2() {
        return qUESTIONOPTION2;
    }

    public String getQUESTIONOPTION3() {
        return qUESTIONOPTION3;
    }

    public String getQUESTIONOPTION4() {
        return qUESTIONOPTION4;
    }

    public String getQUESTIONCORRECTOPTION() {
        return qUESTIONCORRECTOPTION;
    }

}
