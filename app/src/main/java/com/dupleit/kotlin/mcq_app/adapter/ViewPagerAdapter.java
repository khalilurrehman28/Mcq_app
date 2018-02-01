package com.dupleit.kotlin.mcq_app.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dupleit.kotlin.mcq_app.Question_Ui.questionFragment;
import com.dupleit.kotlin.mcq_app.modal.QuestionModal;
import com.dupleit.kotlin.mcq_app.modal.Question_Data;

import java.util.ArrayList;
import java.util.List;

import static com.dupleit.kotlin.mcq_app.utils.constants.ARG_PAGE;

/**
 * Created by android on 24/1/18.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    List<QuestionModal> question;
    private ArrayList<Integer> page_indexes;

    public ViewPagerAdapter(FragmentManager fm, List<QuestionModal> serverQuestion ) {
        super(fm);
        question = serverQuestion;
        page_indexes = new ArrayList<>();
        for (int i = 0; i < question.size(); i++) {
            page_indexes.add(i);
        }
    }

    @Override
    public Fragment getItem(int position) {
        questionFragment fragment = new questionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page_indexes.get(position));
        fragment.setArguments(args);
        return fragment;
        //return questionFragment.newInstance(index);
    }

    @Override
    public int getCount() {
        return question.size();
    }


}