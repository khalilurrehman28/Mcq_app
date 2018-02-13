package com.dupleit.kotlin.mcq_app.Question_Ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dupleit.kotlin.mcq_app.HelperClass.GlideImageGetter;
import com.dupleit.kotlin.mcq_app.R;
import com.dupleit.kotlin.mcq_app.ServerDataGetter;
import com.dupleit.kotlin.mcq_app.modal.QuestionModal;

import java.util.ArrayList;
import java.util.List;

import static com.dupleit.kotlin.mcq_app.utils.constants.*;

public class questionFragment extends Fragment {
    /**
     * The argument key for the page number this fragment represents.
     */
    TextView question;
    RadioButton option1, option2, option3, option4;
    RadioGroup radioGroup;
    ImageView markQuestion,erase;
    private int mPageNumber;

    private static List<QuestionModal> ConvertedQuestionData;

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

        question = v.findViewById(R.id.question);
        option1 = v.findViewById(R.id.option1);
        option2 = v.findViewById(R.id.option2);
        option3 = v.findViewById(R.id.option3);
        option4 = v.findViewById(R.id.option4);
        erase = v.findViewById(R.id.erase);

        markQuestion = v.findViewById(R.id.markQuestion);

        if (ConvertedQuestionData.get(mPageNumber).isIsmarked()) {
            markQuestion.setImageResource(R.drawable.ic_marked_click);
        } else {
            markQuestion.setImageResource(R.drawable.ic_marked_unclicked);
        }
        markQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ConvertedQuestionData.get(mPageNumber).isIsmarked()) {
                    markQuestion.setImageResource(R.drawable.ic_marked_unclicked);
                    ConvertedQuestionData.get(mPageNumber).setIsmarked(false);
                    if (ConvertedQuestionData.get(mPageNumber).getUserAnswerState().equals(questionIsMarked)) {
                        if(ConvertedQuestionData.get(mPageNumber).isAttempted()){
                            ConvertedQuestionData.get(mPageNumber).setUserAnswerState(answerGiven);
                        }else{
                            ConvertedQuestionData.get(mPageNumber).setUserAnswerState(answerSkip);
                        }
                    } else {
                        if(ConvertedQuestionData.get(mPageNumber).isAttempted()){
                            ConvertedQuestionData.get(mPageNumber).setUserAnswerState(answerGiven);
                        }else{
                            ConvertedQuestionData.get(mPageNumber).setUserAnswerState(answerSkip);
                        }
                    }
                } else {
                    markQuestion.setImageResource(R.drawable.ic_marked_click);
                    ConvertedQuestionData.get(mPageNumber).setIsmarked(true);
                    ConvertedQuestionData.get(mPageNumber).setUserAnswerState(questionIsMarked);
                }
            }
        });

        question.setText(Html.fromHtml(ConvertedQuestionData.get(mPageNumber).getUserQuestion().getQUESTIONTEXT(), new GlideImageGetter(getContext(), question), null));
        option1.setText(Html.fromHtml(ConvertedQuestionData.get(mPageNumber).getUserQuestion().getQUESTIONOPTION1(), new GlideImageGetter(getContext(), option1), null));
        option2.setText(Html.fromHtml(ConvertedQuestionData.get(mPageNumber).getUserQuestion().getQUESTIONOPTION2(), new GlideImageGetter(getContext(), option2), null));
        option3.setText(Html.fromHtml(ConvertedQuestionData.get(mPageNumber).getUserQuestion().getQUESTIONOPTION3(), new GlideImageGetter(getContext(), option3), null));
        option4.setText(Html.fromHtml(ConvertedQuestionData.get(mPageNumber).getUserQuestion().getQUESTIONOPTION4(), new GlideImageGetter(getContext(), option4), null));

        erase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                radioGroup.clearCheck();
                ConvertedQuestionData.get(mPageNumber).setAnswerProvided(0);
                ConvertedQuestionData.get(mPageNumber).setAttempted(false);
                ConvertedQuestionData.get(mPageNumber).setUserAnswerState(answerSkip);
            }
        });

        radioGroup = v.findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int answerProvided;
                switch (radioGroup.getCheckedRadioButtonId()) {
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
                    default:
                        answerProvided = 0;
                        break;
                }
                ConvertedQuestionData.get(mPageNumber).setAnswerProvided(answerProvided);
                ConvertedQuestionData.get(mPageNumber).setAttempted(true);
                if (ConvertedQuestionData.get(mPageNumber).isIsmarked()) {
                    ConvertedQuestionData.get(mPageNumber).setUserAnswerState(questionIsMarked);
                } else {
                    ConvertedQuestionData.get(mPageNumber).setUserAnswerState(answerGiven);
                }
            }
        });
        return v;
    }
}
