package com.dupleit.kotlin.mcq_app;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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

    private static String Progress = constants.notStarted;

    public static synchronized ServerDataGetter getInstance() {
        if (instance == null){
            instance = new ServerDataGetter();
        }
        return instance;
    }

    protected ServerDataGetter() {
        ConvertedQuestionData = new ArrayList<>();
        ServerQuestionData = new ArrayList<>();
        getAllQuestionFromServer();
    }

    private void getAllQuestionFromServer() {
        APIService service = ApiClient.getClient().create(APIService.class);
        Call<Question> userCall = service.getQuestionAll();
        userCall.enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {
                if (response != null && response.isSuccessful()) {
                    ServerQuestionData = response.body().getQuestion();
                    for (Question_Data question: ServerQuestionData) {
                        ConvertedQuestionData.add(new QuestionModal(question,false));
                    }

                    for (QuestionModal newQuestion: ConvertedQuestionData) {
                        Log.d("userQuestion",newQuestion.getUserQuestion().getQUESTIONID()+"---"+newQuestion.getUserQuestion().getQUESTIONTEXT()+"---"+newQuestion.isAttempted());
                    }

                } else {
                    //Toast.makeText( , "data not found", Toast.LENGTH_SHORT).show();
                    Log.d("userQuestion","data not found");
                }
            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }

    public List<Question_Data> getServerQuestionData() {
        return ServerQuestionData;
    }

    public List<QuestionModal> getConvertedQuestionData() {
        return ConvertedQuestionData;
    }

    public String getProgress() {
        return Progress;
    }

    public void setProgress(String progress) {
        Progress = progress;
    }

    public int getQuestionCount(){
        return ServerQuestionData.size();
    }

}
