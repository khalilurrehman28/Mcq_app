package com.dupleit.kotlin.mcq_app.Network;

import com.dupleit.kotlin.mcq_app.utils.constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.logging.Level;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by mandeep on 6/7/17.
 */

public class ApiClient {

    public static final String BASE_URL = constants.webUrl+constants.api_url;
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        OkHttpClient httpClient = new OkHttpClient.Builder().addInterceptor(logging).build();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (retrofit==null) {

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient)
                    .build();
        }
        return retrofit;
    }
}