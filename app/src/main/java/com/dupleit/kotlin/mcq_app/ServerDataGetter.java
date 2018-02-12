package com.dupleit.kotlin.mcq_app;

import android.util.Log;

import com.dupleit.kotlin.mcq_app.Network.APIService;
import com.dupleit.kotlin.mcq_app.Network.ApiClient;
import com.dupleit.kotlin.mcq_app.modal.Question;
import com.dupleit.kotlin.mcq_app.modal.QuestionModal;
import com.dupleit.kotlin.mcq_app.modal.Question_Data;
import com.dupleit.kotlin.mcq_app.utils.constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by android on 31/1/18.
 */

public class ServerDataGetter {
    private static ServerDataGetter instance = null;

    private static List<QuestionModal> ConvertedQuestionData;

    private static List<Question_Data> ServerQuestionData;

    public static synchronized ServerDataGetter getInstance() {
        if (instance == null){
            instance = new ServerDataGetter();
        }
        return instance;
    }

    protected ServerDataGetter() {
        ConvertedQuestionData = new ArrayList<>();
        ServerQuestionData = new ArrayList<>();
       // getAllQuestionFromServer();
    }


    public List<QuestionModal> getConvertedQuestionData() {
        return ConvertedQuestionData;
    }

    public void setConvertedQuestionData(List<QuestionModal> convertedQuestionData) {
        ConvertedQuestionData = convertedQuestionData;
    }

}
