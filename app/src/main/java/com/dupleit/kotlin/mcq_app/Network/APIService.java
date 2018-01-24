package com.dupleit.kotlin.mcq_app.Network;

import com.dupleit.kotlin.mcq_app.modal.Question;
import com.dupleit.kotlin.mcq_app.modal.QuestionModal;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {
    //getQuestionAll

    @GET("getquestion_request")
    Call<Question> getQuestionAll();

}