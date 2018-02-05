package com.dupleit.kotlin.mcq_app.Question_Ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.dupleit.kotlin.mcq_app.HelperClass.GlideImageGetter;
import com.dupleit.kotlin.mcq_app.R;
import com.dupleit.kotlin.mcq_app.ServerDataGetter;
import com.dupleit.kotlin.mcq_app.modal.QuestionModal;
import com.dupleit.kotlin.mcq_app.utils.constants;

import java.util.ArrayList;
import java.util.List;

import static com.dupleit.kotlin.mcq_app.utils.constants.ARG_PAGE;

public class questionFragment extends Fragment {
    /**
     * The argument key for the page number this fragment represents.
     */
    TextView question, option1,option2,option3,option4;

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
        mPageNumber = getArguments() != null ? getArguments().getInt(ARG_PAGE) : 1;
        question = (TextView) v.findViewById(R.id.question);
        option1 = (TextView) v.findViewById(R.id.option1);
        option2 = (TextView) v.findViewById(R.id.option2);
        option3 = (TextView) v.findViewById(R.id.option3);
        option4 = (TextView) v.findViewById(R.id.option4);

        ConvertedQuestionData = new ArrayList<>(ServerDataGetter.getInstance().getConvertedQuestionData());

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
        //Log.d(constants.question,getArguments().getString(constants.question));
        return v;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }

}
