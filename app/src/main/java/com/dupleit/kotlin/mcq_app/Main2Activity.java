package com.dupleit.kotlin.mcq_app;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.dupleit.kotlin.mcq_app.Network.APIService;
import com.dupleit.kotlin.mcq_app.Network.ApiClient;
import com.dupleit.kotlin.mcq_app.modal.Question;
import com.dupleit.kotlin.mcq_app.modal.Question_Data;
import com.dupleit.kotlin.mcq_app.utils.constants;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends FragmentActivity {

    private List<Question_Data> question;
    TextView testingId;
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        //testingId = findViewById(R.id.textView);
        webview = findViewById(R.id.webview);
        question = new ArrayList<>();
        getAllQuestionFromServer();
    }

    private void getAllQuestionFromServer() {
        APIService service = ApiClient.getClient().create(APIService.class);
        Call<Question> userCall = service.getQuestionAll();
        userCall.enqueue(new Callback<Question>() {
            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {
                if(response!=null && response.isSuccessful()) {
                    //If the app is on foreground run the task
                    Log.d("question",""+response.body().getQuestion().get(2).getQUESTIONTEXT());

                    webview.loadUrl(response.body().getQuestion().get(2).getQUESTIONTEXT());

                    /*
                    testingId.setText(Html.fromHtml(response.body().getQuestion().get(2).getQUESTIONTEXT(), new Html.ImageGetter() {

                        @Override
                        public Drawable getDrawable(String source) {
                            //int resourceId = getResources().getIdentifier(source, "drawable",getPackageName());
                            //Drawable drawable = getResources().getDrawable(resourceId);
                            Drawable drawable = null;
                            try {
                                drawable = drawableFromUrl(constants.webUrl+source);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            Log.d("image",""+drawable.getIntrinsicHeight()+"-- width->"+drawable.getIntrinsicWidth());
                            drawable.setBounds(0, 0, 300, 100*//*height*//*);
                            return drawable;
                        }
                    }, null));*/


                }else{
                    Toast.makeText(Main2Activity.this, "data not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                Log.d("onFailure", t.toString());
            }
        });
    }

    public static Drawable drawableFromUrl(String url) throws IOException {
        Bitmap x;

        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.connect();
        InputStream input = connection.getInputStream();

        x = BitmapFactory.decodeStream(input);
        return new BitmapDrawable(x);
    }
}
