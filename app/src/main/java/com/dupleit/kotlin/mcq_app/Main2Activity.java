package com.dupleit.kotlin.mcq_app;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dupleit.kotlin.mcq_app.Network.APIService;
import com.dupleit.kotlin.mcq_app.Network.ApiClient;
import com.dupleit.kotlin.mcq_app.adapter.CustomViewPager;
import com.dupleit.kotlin.mcq_app.adapter.ViewPagerAdapter;
import com.dupleit.kotlin.mcq_app.modal.Question;
import com.dupleit.kotlin.mcq_app.modal.QuestionModal;
import com.dupleit.kotlin.mcq_app.modal.Question_Data;
import com.dupleit.kotlin.mcq_app.utils.constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity {

    private CustomViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;
    //List<QuestionModal> question;

    private static List<QuestionModal> ConvertedQuestionData;

    private static List<Question_Data> ServerQuestionData;

    private static String Progress = constants.notStarted;

    Button next, previous;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*question = new ArrayList<>(ServerDataGetter.getInstance().getConvertedQuestionData());
        Toast.makeText(this, ""+question.size(), Toast.LENGTH_SHORT).show();*/
        ConvertedQuestionData = new ArrayList<>();
        ServerQuestionData = new ArrayList<>();
        mPager = findViewById(R.id.pager);
        mPager.setPagingEnabled(false);
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),ConvertedQuestionData);
        mPager.setAdapter(mPagerAdapter);
        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);

        getServerData();
        /*mPager.setCurrentItem(1,false);
        mPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When changing pages, reset the action bar actions since they are dependent
                // on which page is currently active. An alternative approach is to have each
                // fragment expose actions itself (rather than the activity exposing actions),
                // but for simplicity, the activity provides the actions in this sample.
                invalidateOptionsMenu();
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                Toast.makeText(Main2Activity.this, ""+position, Toast.LENGTH_SHORT).show();
            }
        });*/

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int val = mPager.getCurrentItem();
                mPager.setCurrentItem(val+1);
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int val = mPager.getCurrentItem();
                mPager.setCurrentItem(val-1);
            }
        });
    }

    private void getServerData() {
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

                    ServerDataGetter.getInstance().setConvertedQuestionData(ConvertedQuestionData);

                    mPager.getAdapter().notifyDataSetChanged();

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


}
