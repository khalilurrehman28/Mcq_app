package com.dupleit.kotlin.mcq_app.Question_Ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.dupleit.kotlin.mcq_app.R;

public class questionFragment extends Fragment {
    /**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";

    TextView question;
    RadioButton option1,option2,option3,option4;
    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
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

    public questionFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
    }

    @SuppressLint("StringFormatInvalid")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
        ViewGroup rootView = (ViewGroup) inflater
                .inflate(R.layout.fragment_question, container, false);

        // Set the title view to show the page number.
     /*   ((TextView) rootView.findViewById(android.R.id.text1)).setText(
                getString(R.string.title_template_step, mPageNumber + 1));*/
        question = (TextView) rootView.findViewById(R.id.question);
        option1 = (RadioButton) rootView.findViewById(R.id.option1);
        option2 = (RadioButton) rootView.findViewById(R.id.option2);
        option3 = (RadioButton) rootView.findViewById(R.id.option3);
        option4 = (RadioButton) rootView.findViewById(R.id.option4);

        return rootView;
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }

}