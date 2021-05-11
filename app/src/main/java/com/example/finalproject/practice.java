package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class practice extends AppCompatActivity {
    private Button a;
    private Button b;
    private Button c;
    private Button d;
    private TextView q;
    private TextView noQues;
    private TextView outOf;
    private int countQ;
    private int upTo;
    private DatabaseReference reff;
    private FirebaseDatabase mfd;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuteListener;
    private ArrayList<Question> qList;
    private ArrayList<Question> BaseList;
    private ArrayList<Answer> answerList;
    private Question current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.practice_layout);
        answerList=new ArrayList<Answer>();
        a = findViewById(R.id.Abutton);
        a.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Click(view);
            }
        });
        b = findViewById(R.id.Bbutton);
        b.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Click(view);
            }
        });
        c = findViewById(R.id.Cbutton);
        c.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Click(view);
            }
        });
        d = findViewById(R.id.Dbutton);
        d.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Click(view);
            }
        });
        q = findViewById(R.id.question);

        a.setVisibility(View.INVISIBLE);
        b.setVisibility(View.INVISIBLE);
        c.setVisibility(View.INVISIBLE);
        d.setVisibility(View.INVISIBLE);
        q.setVisibility(View.INVISIBLE);


        noQues = findViewById(R.id.practiceNumber);
        outOf = findViewById(R.id.practiceNumber2);
        String getString = getIntent().getStringExtra("numberOfQuestions");
        noQues.setText("1");
        outOf.setText(getString);
        countQ = 1;
        upTo = Integer.valueOf(getString);
        mAuth = FirebaseAuth.getInstance();
        mfd = FirebaseDatabase.getInstance();
        reff= mfd.getReference();
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
            this.qList=new ArrayList<Question>();
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
                qList.add(qInfo);
            }
            BaseList=new ArrayList<Question>();
            BaseList.addAll(qList);
            chooseQuestion();
        }
    public void chooseQuestion()
    {
        Random r = new Random();
        int max=qList.size()-1;
        int min=0;
        if(max>0)
        {
            int result = r.nextInt(max - min) + min;
            ArrayList<String> shu = new ArrayList<String>();
            this.current = this.qList.get(result);
            this.qList.remove(result);
            this.q.setText(current.getQ());
            shu.add(current.getA());
            shu.add(current.getB());
            shu.add(current.getC());
            shu.add(current.getD());
            Collections.shuffle(shu);
            this.a.setText(shu.get(0));
            this.b.setText(shu.get(1));
            this.c.setText(shu.get(2));
            this.d.setText(shu.get(3));
            System.out.println(qList);
        }
        else{
            qList=new ArrayList<Question>();
            qList.addAll(BaseList);
            max=qList.size()-1;
            min=0;
            int result = r.nextInt(max - min) + min;
            ArrayList<String> shu = new ArrayList<String>();
            this.current = this.qList.get(result);
            this.qList.remove(result);
            this.q.setText(current.getQ());
            shu.add(current.getA());
            shu.add(current.getB());
            shu.add(current.getC());
            shu.add(current.getD());
            Collections.shuffle(shu);
            this.a.setText(shu.get(0));
            this.b.setText(shu.get(1));
            this.c.setText(shu.get(2));
            this.d.setText(shu.get(3));
            System.out.println(qList);
        }
        a.setVisibility(View.VISIBLE);
        b.setVisibility(View.VISIBLE);
        c.setVisibility(View.VISIBLE);
        d.setVisibility(View.VISIBLE);
        q.setVisibility(View.VISIBLE);
        setOriginal();

    }
        public void setCorrect(View view)
        {
            Button button=(Button)view;
            String buttonText=button.getText().toString();
            button.setBackgroundDrawable(getResources().getDrawable(R.drawable.correct));
        }
        public void setWrong(View view)
        {
            Button button=(Button)view;
            String buttonText=button.getText().toString();
            button.setBackgroundDrawable(getResources().getDrawable(R.drawable.incorrect));
        }
        public void setOriginal()
        {
            a.setBackgroundDrawable(getResources().getDrawable(R.drawable.answers_border));
            b.setBackgroundDrawable(getResources().getDrawable(R.drawable.answers_border));
            c.setBackgroundDrawable(getResources().getDrawable(R.drawable.answers_border));
            d.setBackgroundDrawable(getResources().getDrawable(R.drawable.answers_border));
        }
        public boolean compareButtonText(View view)
        {
            Button button=(Button)view;
            String buttonText=button.getText().toString();
            if (buttonText.equals(current.getCorrect()))
                return true;
            else return false;
        }
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Are you sure you want to EXIT?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent returnBtn = new Intent(getApplicationContext(),
                                MainActivity.class);

                        startActivity(returnBtn);
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }
        public boolean ifStop()
        {
            if(countQ==upTo)
                return false;
            return true;
        }
        public String buttonTextfunc(View view)
        {
            Button button=(Button)view;
            String buttonText=button.getText().toString();
            return buttonText;
        }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.item1:
                Toast.makeText(this,"Item1 selected",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                Toast.makeText(this,"Item2 selected",Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                builder1.setMessage("Are you sure you want to EXIT?");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    public void Click(View view) {
        if(ifStop()) {
            Answer temp=new Answer();
            temp.setQuestion(current.getQ());
            temp.setCorrect(current.getCorrect());
            temp.setSelected(buttonTextfunc(view));
            temp.setAnswerRight(compareButtonText(view));
            answerList.add(temp);
            countQ++;
            noQues.setText(String.valueOf(countQ));
            setCorrect(view);
            chooseQuestion();
        }
        else
            {
                if(countQ==upTo) {
                    Answer temp = new Answer();
                    temp.setQuestion(current.getQ());
                    temp.setCorrect(current.getCorrect());
                    temp.setSelected(buttonTextfunc(view));
                    temp.setAnswerRight(compareButtonText(view));
                    answerList.add(temp);
                }
/*
                Intent intent=new Intent(practice.this,PracticeResult.class);
                Bundle bundle =new Bundle();
                bundle.putSerializable("answerList",(Serializable)answerList);
                intent.putExtra("extra",bundle);
                startActivity(intent);
*/

                Intent intent = new Intent(practice.this, PracticeResult.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("answerList",answerList);
                intent.putExtra("bundle",bundle);

                startActivity(intent);

            }

    }

        }

