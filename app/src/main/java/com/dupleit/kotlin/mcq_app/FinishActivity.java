package com.dupleit.kotlin.mcq_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dupleit.kotlin.mcq_app.modal.QuestionModal;

import java.util.ArrayList;
import java.util.List;

public class FinishActivity extends AppCompatActivity {

    List<QuestionModal> modalList;
    TextView Result,Correct,ViewAll,Marked;
    int markedAns = 0,correctAns=0,attemptedAns = 0;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        Result = findViewById(R.id.Result);
        Correct = findViewById(R.id.Correct);
        Marked = findViewById(R.id.marked);
        ViewAll = findViewById(R.id.ViewAll);
        button2 = findViewById(R.id.button2);

        modalList = new ArrayList<>(ServerDataGetter.getInstance().getConvertedQuestionData());
        Toast.makeText(this, ""+modalList.size(), Toast.LENGTH_SHORT).show();
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i <modalList.size() ; i++) {
                    if (modalList.get(i).isIsmarked()){
                        markedAns+=1;
                    }
                    if (modalList.get(i).isAttempted()){
                        attemptedAns +=1;
                    }
                    if (modalList.get(i).isAttempted()){
                        if (modalList.get(i).getAnswerProvided() == Integer.parseInt(modalList.get(i).getUserQuestion().getQUESTIONCORRECTOPTION())){
                            correctAns +=1;
                        }
                    }
                }

                Marked.setText(Integer.toString(markedAns));
                Correct.setText(Integer.toString(correctAns));
                ViewAll.setText(Integer.toString(attemptedAns));
            }
        });
    }
}
