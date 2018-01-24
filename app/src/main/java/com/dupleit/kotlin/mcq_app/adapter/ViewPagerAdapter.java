package com.dupleit.kotlin.mcq_app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dupleit.kotlin.mcq_app.Question_Ui.questionFragment;
import com.dupleit.kotlin.mcq_app.modal.Question_Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by android on 24/1/18.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    List<Question_Data> question;
    private ArrayList<Integer> page_indexes;

    public ViewPagerAdapter(FragmentManager fm, List<Question_Data> serverQuestion ) {
        super(fm);
        question = serverQuestion;
        page_indexes = new ArrayList<>();
        for (int i = 0; i < question.size(); i++) {
            page_indexes.add(i);
        }
    }

    @Override
    public Fragment getItem(int position) {
        Integer index = page_indexes.get(position);
        return questionFragment.create(index);
    }

    @Override
    public int getCount() {
        return question.size();
    }
}