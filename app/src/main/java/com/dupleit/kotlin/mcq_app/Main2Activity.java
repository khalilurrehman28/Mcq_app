package com.dupleit.kotlin.mcq_app;

import android.app.Fragment;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dupleit.kotlin.mcq_app.HelperClass.GridSpacingItemDecoration;
import com.dupleit.kotlin.mcq_app.HelperClass.RecyclerTouchListener;
import com.dupleit.kotlin.mcq_app.Network.APIService;
import com.dupleit.kotlin.mcq_app.Network.ApiClient;
import com.dupleit.kotlin.mcq_app.Question_Ui.questionFragment;
import com.dupleit.kotlin.mcq_app.adapter.CustomViewPager;
import com.dupleit.kotlin.mcq_app.adapter.QuestionListAdapter;
import com.dupleit.kotlin.mcq_app.adapter.ViewPagerAdapter;
import com.dupleit.kotlin.mcq_app.modal.Question;
import com.dupleit.kotlin.mcq_app.modal.QuestionModal;
import com.dupleit.kotlin.mcq_app.modal.Question_Data;
import com.dupleit.kotlin.mcq_app.utils.constants;
import com.mancj.slideup.SlideUp;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main2Activity extends AppCompatActivity {

    private CustomViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;
    FloatingActionButton fab;
    //List<QuestionModal> question;

    private static List<QuestionModal> ConvertedQuestionData;

    private static List<Question_Data> ServerQuestionData;

    private static String Progress = constants.notStarted;

    Button next, previous, finish;
    private SlideUp slideUp;
    private View dim;
    private View slideView;
    QuestionListAdapter Questionadapter;
    RecyclerView recyclerView;
    private CardView card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*question = new ArrayList<>(ServerDataGetter.getInstance().getConvertedQuestionData());
        Toast.makeText(this, ""+question.size(), Toast.LENGTH_SHORT).show();*/
        ConvertedQuestionData = new ArrayList<>();
        ServerQuestionData = new ArrayList<>();
        Questionadapter = new QuestionListAdapter(this,ConvertedQuestionData);

        //RecyclerView For the Question
        recyclerView = findViewById(R.id.QuestionListRecycler);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 4);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(1), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(Questionadapter);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                card = (CardView) view.findViewById(R.id.QuestionCard);
                //QuestionModal cdm = ConvertedQuestionData.get(position);
                /*Toast.makeText(getActivity(), ""+cdm.getSTUDENTROLLNO(), Toast.LENGTH_SHORT).show();
                if (cdm.getCheck()){
                    card.setCardBackgroundColor(Color.GREEN);
                    cdm.setCheck(false);
                  *//*  attList.add(cdm);
                    for (int i = 0; i <attList.size() ; i++) {
                        Log.d("StudentAdd",""+attList.get(i).getSTUDENTROLLNO());
                    }*//*
                }else {
                    card.setCardBackgroundColor(Color.RED);
                    cdm.setCheck(true);
                  *//*  if (attList.size()>0) {
                        attList.remove(attList.indexOf(attList.contains(cdm.getSTUDENTROLLNO())));
                        for (int i = 0; i <attList.size() ; i++) {
                            Log.d("StudentRemove",""+attList.get(i).getSTUDENTROLLNO());
                        }
                    }*//*
                }
                adapter.notifyDataSetChanged();*/
                mPager.setCurrentItem(position);
                slideUp.animateOut();
            }

            @Override
            public void onLongClick(View view, int position) {
            }
        }));

        mPager = findViewById(R.id.pager);
        mPager.setPagingEnabled(false);
        mPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),ConvertedQuestionData);
        mPager.setAdapter(mPagerAdapter);
        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);
        finish = findViewById(R.id.finish);
        fab = findViewById(R.id.fab);

        slideView = findViewById(R.id.slideView);
        dim = findViewById(R.id.dim);

        slideUp = new SlideUp(slideView);
        slideUp.hideImmediately();

        getServerData();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int val = mPager.getCurrentItem();
                mPager.setCurrentItem(val+1);
                if (mPager.getCurrentItem()!=ConvertedQuestionData.size()){
                    ConvertedQuestionData.get(val+1).setProcessStart(constants.Submittd);
                }
            }
        });
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int val = mPager.getCurrentItem();
                mPager.setCurrentItem(val-1);
                if (mPager.getCurrentItem()!=ConvertedQuestionData.size()){
                    ConvertedQuestionData.get(val-1).setProcessStart(constants.Submittd);
                }
            }
        });
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*int count =0;
                for (int i = 0; i <ConvertedQuestionData.size() ; i++) {
                    if (ConvertedQuestionData.get(i).isAttempted()){
                        if (ConvertedQuestionData.get(i).getAnswerProvided() == Integer.parseInt(ConvertedQuestionData.get(i).getUserQuestion().getQUESTIONCORRECTOPTION())){
                            count +=1;
                        }
                    }
                }

                Toast.makeText(Main2Activity.this, "Total Correct Answer is "+count, Toast.LENGTH_SHORT).show();*/

                startActivity(new Intent(Main2Activity.this,FinishActivity.class));
            }
        });

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(final int position) {
                ConvertedQuestionData.get(position).setProcessStart(constants.Submittd);
                /*
                Toast.makeText(Main2Activity.this, "Page No->"+position, Toast.LENGTH_SHORT).show();
                Timer t;
                TimerTask timer= new TimerTask(){
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int counter = ConvertedQuestionData.get(position).getTimeCounter();
                                counter+=1;
                                ConvertedQuestionData.get(position).setTimeCounter(counter);
                                //Timertxt.setText(Integer.toString(counter));
                            }
                        });
                    }
                };
                t = new Timer();
                t.scheduleAtFixedRate(timer , 0 , 1000);*/
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slideUp.animateIn();
                fab.hide();
                Questionadapter.notifyDataSetChanged();
            }
        });

        slideUp.setSlideListener(new SlideUp.SlideListener() {
            @Override
            public void onSlideDown(float v) {
                dim.setAlpha(1 - (v / 100));
            }

            @Override
            public void onVisibilityChanged(int i) {
                if (i == View.GONE) {
                    fab.show();
                }

            }
        });
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
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

                    /*for (QuestionModal newQuestion: ConvertedQuestionData) {
                        Log.d("userQuestion",newQuestion.getUserQuestion().getQUESTIONID()+"---"+newQuestion.getUserQuestion().getQUESTIONTEXT()+"---"+newQuestion.isAttempted());
                    }*/

                    ServerDataGetter.getInstance().setConvertedQuestionData(ConvertedQuestionData);

                    mPagerAdapter.notifyDataSetChanged();

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
