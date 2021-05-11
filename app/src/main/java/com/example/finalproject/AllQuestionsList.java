package com.example.finalproject;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllQuestionsList extends AppCompatActivity {
    private ArrayList<Question> List;
    private FirebaseDatabase mfd;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuteListener;
    private DatabaseReference reffe;
    private int rec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative_all_list);
        mAuth=FirebaseAuth.getInstance();
        mfd=FirebaseDatabase.getInstance();
        reffe= mfd.getReference();
        reffe.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("INNN");
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
    private void showData(DataSnapshot dataSnapshot) {
        int i=1;

        System.out.println("IN");
        this.List=new ArrayList<Question>();
        Question.setTotal(dataSnapshot.getChildrenCount()-1);
        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            Question qInfo= new Question();
            qInfo.setA(ds.child("a").getValue(String.class));
            qInfo.setB(ds.child("b").getValue(String.class));
            qInfo.setC(ds.child("c").getValue(String.class));
            qInfo.setD(ds.child("d").getValue(String.class));
            qInfo.setQ(ds.child("q").getValue(String.class));
            qInfo.setCorrect(ds.child("correct").getValue(String.class));
            if(qInfo.getA()!=null)
                List.add(qInfo);

        }
        ListView listView =(ListView) findViewById(R.id.listView2);
        QuestionAdapter adapter=new QuestionAdapter(this,R.layout.all_questions_layout,List);
        listView.setAdapter(adapter);
    }
}
