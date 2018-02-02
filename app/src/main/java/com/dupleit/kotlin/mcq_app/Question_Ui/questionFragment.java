package com.dupleit.kotlin.mcq_app.Question_Ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.dupleit.kotlin.mcq_app.R;
import com.dupleit.kotlin.mcq_app.utils.constants;

import static com.dupleit.kotlin.mcq_app.utils.constants.ARG_PAGE;

public class questionFragment extends Fragment {
    /**
     * The argument key for the page number this fragment represents.
     */
    TextView question;
    RadioButton option1,option2,option3,option4;

    private int mPageNumber;

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
        option1 = (RadioButton) v.findViewById(R.id.option1);
        option2 = (RadioButton) v.findViewById(R.id.option2);
        option3 = (RadioButton) v.findViewById(R.id.option3);
        option4 = (RadioButton) v.findViewById(R.id.option4);

        question.setText(getArguments().getString(constants.question));
        Log.d(constants.question,getArguments().getString(constants.question));
        return v;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }

}
