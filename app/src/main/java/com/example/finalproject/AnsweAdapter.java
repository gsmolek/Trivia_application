package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AnsweAdapter extends ArrayAdapter<Answer> {
    private Context mContext;
    private int mResource;

    public AnsweAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Answer> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String question=getItem(position).getQuestion();
        String selected=("Selected Option: "+getItem(position).getSelected());
        String correct=("Correct Answer: "+getItem(position).getCorrect());
        boolean answerRight=getItem(position).isAnswerRight();

        Answer answer = new Answer(question,selected,correct,answerRight);

        LayoutInflater inflater=LayoutInflater.from(mContext);
        convertView=inflater.inflate(mResource,parent,false);
        TextView q= (TextView) convertView.findViewById(R.id.questionList);
        TextView crct=(TextView) convertView.findViewById(R.id.correctList);
        TextView slctd =(TextView) convertView.findViewById(R.id.selectedList);
        ImageView image =(ImageView) convertView.findViewById(R.id.reusltList);

        q.setText(question);
        crct.setText(correct);
        slctd.setText(selected);
        if(answerRight==true)
        {
            image.setImageResource(R.drawable.v);
            crct.setText("");
            slctd.setText(selected);
        }
        else
        {
            image.setImageResource(R.drawable.x);
            crct.setText(correct);
            slctd.setText(selected);
            LinearLayout l=(LinearLayout) convertView.findViewById(R.id.linearlayoutList);
            l.setBackgroundResource(R.drawable.border_wrong);

        }

        return convertView;
    }

}
