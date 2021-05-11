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

import java.util.List;

public class QuestionAdapter extends ArrayAdapter<Question> {
    private Context mContext;
    private int mResource;
    public QuestionAdapter(@NonNull Context context, int resource, @NonNull List<Question> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String question=getItem(position).getQ();
        String a=("Option A: "+getItem(position).getA());
        String b=("Option B: "+getItem(position).getB());
        String c=("Option C: "+getItem(position).getC());
        String d=("Option D: "+getItem(position).getD());
        String correct=("Correct Answer: "+getItem(position).getCorrect());

        Question answer = new Question(question,a,b,c,d,correct);

        LayoutInflater inflater=LayoutInflater.from(mContext);
        convertView=inflater.inflate(mResource,parent,false);
        TextView q= (TextView) convertView.findViewById(R.id.questionAllList);
        TextView crct=(TextView) convertView.findViewById(R.id.selectedALLList);
        TextView A =(TextView) convertView.findViewById(R.id.AALLList);
        TextView B =(TextView) convertView.findViewById(R.id.BALLList);
        TextView C =(TextView) convertView.findViewById(R.id.CALLList);
        TextView D =(TextView) convertView.findViewById(R.id.DALLList);
        ImageView image =(ImageView) convertView.findViewById(R.id.reusltList);

        q.setText(question);
        crct.setText(correct);
        A.setText(a);
        B.setText(b);
        C.setText(c);
        D.setText(d);
        LinearLayout l=(LinearLayout) convertView.findViewById(R.id.linearlayoutALLList);
        l.setBackgroundResource(R.drawable.borders);

        return convertView;
    }


}
