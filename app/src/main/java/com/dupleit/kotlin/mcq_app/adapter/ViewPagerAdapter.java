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

import static com.dupleit.kotlin.mcq_app.utils.constants.option1;
import static com.dupleit.kotlin.mcq_app.utils.constants.option2;
import static com.dupleit.kotlin.mcq_app.utils.constants.option3;
import static com.dupleit.kotlin.mcq_app.utils.constants.option4;
import static com.dupleit.kotlin.mcq_app.utils.constants.question;

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
        Bundle args = new Bundle();
        /* args.putString(question,questionList.get(position).getUserQuestion().getQUESTIONTEXT());
        args.putString(option1,questionList.get(position).getUserQuestion().getQUESTIONOPTION1());
        args.putString(option2,questionList.get(position).getUserQuestion().getQUESTIONOPTION2());
        args.putString(option3,questionList.get(position).getUserQuestion().getQUESTIONOPTION3());
        args.putString(option4,questionList.get(position).getUserQuestion().getQUESTIONOPTION4());*/
        return questionFragment.create(position);
    }

    @Override
    public int getCount() {
        return questionList.size();
    }

    @Override
    public int getItemPosition(Object object) {
        super.getItemPosition(object);
        return PagerAdapter.POSITION_NONE;
    }
}