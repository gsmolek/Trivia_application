package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DataRetrieve extends AppCompatActivity {

    private DatabaseReference reff;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_retrieve);
        reff= FirebaseDatabase.getInstance().getReference();
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showData(DataSnapshot dataSnapshot) {
        int i=1;
        for(DataSnapshot ds : dataSnapshot.getChildren())
        {
            Question qInfo= new Question();
            qInfo.setA(ds.child(String.valueOf(i)).getValue(Question.class).getA());
            qInfo.setB(ds.child(String.valueOf(i)).getValue(Question.class).getB());
            qInfo.setC(ds.child(String.valueOf(i)).getValue(Question.class).getC());
            qInfo.setD(ds.child(String.valueOf(i)).getValue(Question.class).getD());
            qInfo.setQ(ds.child(String.valueOf(i)).getValue(Question.class).getQ());
            qInfo.setCorrect(ds.child(String.valueOf(i)).getValue(Question.class).getCorrect());

            Log.d("i","a: "+qInfo.getA());
            Log.d("i","b: "+qInfo.getB());
            Log.d("i","c: "+qInfo.getC());
            Log.d("i","d: "+qInfo.getD());
            Log.d("i","correct: "+qInfo.getCorrect());
            Log.d("i","q: "+qInfo.getQ());

        }
    }
}
