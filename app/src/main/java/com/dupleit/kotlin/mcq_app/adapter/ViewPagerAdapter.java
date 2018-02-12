package com.dupleit.kotlin.mcq_app.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.MotionEvent;

import com.dupleit.kotlin.mcq_app.Question_Ui.questionFragment;
import com.dupleit.kotlin.mcq_app.modal.QuestionModal;

import java.util.List;

/**
 * Created by android on 24/1/18.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    List<QuestionModal> questionList;
    public ViewPagerAdapter(FragmentManager fm, List<QuestionModal> question) {
        super(fm);
        this.questionList = question;
    }

    @Override
    public Fragment getItem(int position) {
        return questionFragment.create(position);
    }

    @Override
    public int getCount() {
        return questionList.size();
    }

}