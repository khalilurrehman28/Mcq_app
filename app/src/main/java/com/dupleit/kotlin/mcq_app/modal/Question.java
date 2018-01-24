
package com.dupleit.kotlin.mcq_app.modal;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Question {

    @SerializedName("question")
    @Expose
    private List<Question_Data> question = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Question() {
    }

    /**
     * 
     * @param question
     */
    public Question(List<Question_Data> question) {
        super();
        this.question = question;
    }

    public List<Question_Data> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question_Data> question) {
        this.question = question;
    }


}
