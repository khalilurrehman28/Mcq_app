package com.dupleit.kotlin.mcq_app.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dupleit.kotlin.mcq_app.R;
import com.dupleit.kotlin.mcq_app.modal.QuestionModal;

import java.util.List;

import static com.dupleit.kotlin.mcq_app.utils.constants.*;

/**
 * Created by android on 8/2/18.
 */

public class QuestionListAdapter extends RecyclerView.Adapter<QuestionListAdapter.MyViewHolder> {

    private Context mContext;
    private List<QuestionModal> ClassList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView rollnumber;
        public CardView card;
        public ImageView checked;

        public MyViewHolder(View view) {
            super(view);
            rollnumber = (TextView) view.findViewById(R.id.questionNumber);
            card = (CardView) itemView.findViewById(R.id.QuestionCard);
            checked = view.findViewById(R.id.checked);
        }
    }

    public QuestionListAdapter(Context mContext, List<QuestionModal> ClassList) {
        this.mContext = mContext;
        this.ClassList = ClassList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        QuestionModal classD = ClassList.get(position);
        holder.rollnumber.setText(""+(position+1));

        switch (classD.getUserAnswerState()){
            case answerGiven:
                holder.card.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.answerGiven));
                break;
            case answerNotViewed:
                holder.card.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.answerLoaded));
                break;
            case answerSkip:
                holder.card.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.answerSkip));
                break;
            case questionIsMarked:
                if (!classD.isAttempted()){
                    holder.checked.setVisibility(View.GONE);
                }
                holder.card.setCardBackgroundColor(ContextCompat.getColor(mContext, R.color.isMarked));
                break;
        }


    }

    @Override
    public int getItemCount() {
        return ClassList.size();
    }
}