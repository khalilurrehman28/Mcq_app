package com.dupleit.kotlin.mcq_app.Question_Ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.dupleit.kotlin.mcq_app.HelperClass.GlideImageGetter;
import com.dupleit.kotlin.mcq_app.R;
import com.dupleit.kotlin.mcq_app.ServerDataGetter;
import com.dupleit.kotlin.mcq_app.modal.QuestionModal;
import com.dupleit.kotlin.mcq_app.utils.constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static com.dupleit.kotlin.mcq_app.utils.constants.ARG_PAGE;

public class questionFragment extends Fragment {
    /**
     * The argument key for the page number this fragment represents.
     */
    TextView question;
    RadioButton option1,option2,option3,option4;
    RadioGroup radioGroup;
    Button erase;
    ImageView markQuestion;
    TextView Timertxt ;

    private int mPageNumber;

    private static List<QuestionModal> ConvertedQuestionData;
    private Timer t;

    TimerTask timer= new TimerTask(){
        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    int counter = ConvertedQuestionData.get(mPageNumber).getTimeCounter();
                    counter+=1;
                    ConvertedQuestionData.get(mPageNumber).setTimeCounter(counter);
                    Timertxt.setText(Integer.toString(counter));
                }
            });

        }
    };

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static questionFragment create(int pageNumber) {
        questionFragment fragment = new questionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * When creating, retrieve this instance's number from its arguments.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question, container, false);
        ConvertedQuestionData = new ArrayList<>(ServerDataGetter.getInstance().getConvertedQuestionData());
        mPageNumber = getArguments() != null ? getArguments().getInt(ARG_PAGE) : 1;
        question = (TextView) v.findViewById(R.id.question);
        option1 = v.findViewById(R.id.option1);
        option2 = v.findViewById(R.id.option2);
        option3 = v.findViewById(R.id.option3);
        option4 = v.findViewById(R.id.option4);
        erase = v.findViewById(R.id.erase);
        //Timertxt = v.findViewById(R.id.TimerText);
        markQuestion = v.findViewById(R.id.markQuestion);
        if (ConvertedQuestionData.get(mPageNumber).isIsmarked()){
            markQuestion.setImageResource(R.drawable.ic_marked_click);
        }else{
            markQuestion.setImageResource(R.drawable.ic_marked_unclicked);
        }
        markQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ConvertedQuestionData.get(mPageNumber).isIsmarked()){
                    markQuestion.setImageResource(R.drawable.ic_marked_unclicked);
                    ConvertedQuestionData.get(mPageNumber).setIsmarked(false);
                }else{
                    markQuestion.setImageResource(R.drawable.ic_marked_click);
                    ConvertedQuestionData.get(mPageNumber).setIsmarked(true);
                }
            }
        });


        t = new Timer();
        t.scheduleAtFixedRate(timer , 0 , 1000);

        question.setText(Html.fromHtml(ConvertedQuestionData.get(mPageNumber).getUserQuestion().getQUESTIONTEXT(), new GlideImageGetter(getContext(), question), null));
        option1.setText(Html.fromHtml(ConvertedQuestionData.get(mPageNumber).getUserQuestion().getQUESTIONOPTION1(), new GlideImageGetter(getContext(), option1), null));
        option2.setText(Html.fromHtml(ConvertedQuestionData.get(mPageNumber).getUserQuestion().getQUESTIONOPTION2(), new GlideImageGetter(getContext(), option2), null));
        option3.setText(Html.fromHtml(ConvertedQuestionData.get(mPageNumber).getUserQuestion().getQUESTIONOPTION3(), new GlideImageGetter(getContext(), option3), null));
        option4.setText(Html.fromHtml(ConvertedQuestionData.get(mPageNumber).getUserQuestion().getQUESTIONOPTION4(), new GlideImageGetter(getContext(), option4), null));
        question.setMovementMethod(ScrollingMovementMethod.getInstance());
        option1.setMovementMethod(ScrollingMovementMethod.getInstance());
        option2.setMovementMethod(ScrollingMovementMethod.getInstance());
        option3.setMovementMethod(ScrollingMovementMethod.getInstance());
        option4.setMovementMethod(ScrollingMovementMethod.getInstance());

        erase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioGroup.clearCheck();
                ConvertedQuestionData.get(mPageNumber).setAnswerProvided(0);
                ConvertedQuestionData.get(mPageNumber).setAttempted(false);
                ConvertedQuestionData.get(mPageNumber).setProcessStart(constants.Started);
            }
        });

        radioGroup = (RadioGroup) v.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int answerProvided = 0;

                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.option1:
                        answerProvided = 1;
                        break;
                    case R.id.option2:
                        answerProvided = 2;
                        break;
                    case R.id.option3:
                        answerProvided = 3;
                        break;
                    case R.id.option4:
                        answerProvided = 4;
                        break;
                }
                ConvertedQuestionData.get(mPageNumber).setAnswerProvided(answerProvided);
                ConvertedQuestionData.get(mPageNumber).setAttempted(true);
                ConvertedQuestionData.get(mPageNumber).setProcessStart(constants.Started);
            }
        });
        return v;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        t.cancel();
    }
}
