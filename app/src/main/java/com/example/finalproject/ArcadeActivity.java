package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ArcadeActivity extends AppCompatActivity {

    private Button a;
    private Button b;
    private Button c;
    private Button d;
    private TextView question;
    private TextView points;
    private TextView points2;
    private DatabaseReference reff;
    private FirebaseDatabase mfd;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuteListener;
    private ArrayList<Question> qList;
    private Question current;
    private Button ok;
    private int rec;
    private ImageView newRecord;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        setContentView(R.layout.arcade_menu);
        points2=findViewById(R.id.pointsText);
        a=findViewById(R.id.arcadeA);
        b=findViewById(R.id.arcadeB);
        c=findViewById(R.id.arcadeC);
        d=findViewById(R.id.arcadeD);
        question=findViewById(R.id.arcadequestion);

        a.setVisibility(View.INVISIBLE);
        b.setVisibility(View.INVISIBLE);
        c.setVisibility(View.INVISIBLE);
        d.setVisibility(View.INVISIBLE);
        question.setVisibility(View.INVISIBLE);


        points=findViewById(R.id.points);
        mAuth=FirebaseAuth.getInstance();
        mfd=FirebaseDatabase.getInstance();
        reff= mfd.getReference();
        this.points.setText("0");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        a.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Click(view);
            }
        });
        b.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Click(view);
            }
        });
        c.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Click(view);
            }
        });
        d.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Click(view);
            }
        });
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
            this.question.setText(current.getQ());
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
            a.setVisibility(View.VISIBLE);
            b.setVisibility(View.VISIBLE);
            c.setVisibility(View.VISIBLE);
            d.setVisibility(View.VISIBLE);
            question.setVisibility(View.VISIBLE);
        }
       else
       {
           int intPoints=Integer.valueOf(points.getText().toString());
           SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
           String record = preferences.getString("Record", "");
           System.out.println("Record: "+record);
           if(record.equals(""))
           {
               SharedPreferences.Editor editor = preferences.edit();
               editor.putString("Record",String.valueOf(intPoints));
               editor.apply();
           }
           else if(Integer.valueOf(record)<intPoints)
           {
               SharedPreferences.Editor editor = preferences.edit();
               editor.putString("Record",String.valueOf(intPoints));
               editor.apply();
           }
           Intent myintent=new Intent(ArcadeActivity.this,welldonelayout.class);
           startActivity(myintent);
            this.onBackPressed();
       }
        setOriginal();

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
            try {
                rec = Integer.valueOf(ds.child("record").getValue(String.class));
                Question.setRecord(rec);
            }
            catch (Exception e)
            {

            }
            qList.add(qInfo);
        }
        chooseQuestion();
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
    public void increasePoints()
    {
        String convert=this.points.getText().toString();
        int conVal=Integer.valueOf(convert);
        conVal++;
        this.points.setText(String.valueOf(conVal));
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
                Toast.makeText(this,"Exit selected",Toast.LENGTH_SHORT).show();
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
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Are you sure you want to Return To Main Menu?");
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
    public void Click(View view) {
        if (compareButtonText(view))
        {
            setCorrect(view);
            increasePoints();
            chooseQuestion();
        }
        else{
            setContentView(R.layout.game_over_layout);
            ok=findViewById(R.id.button3);
            ok.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
            this.points2=findViewById(R.id.pointsText);
            this.points2.setText(points.getText());
            newRecord=findViewById(R.id.imageView4);
            newRecord.setVisibility(View.INVISIBLE);
            int intPoints=Integer.valueOf(points.getText().toString());
            if(intPoints>rec) {
                String key = reff.push().getKey();
                reff.child("record").child("record").setValue(String.valueOf(intPoints));
                newRecord.setVisibility(View.VISIBLE);
            }
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            String record = preferences.getString("Record", "");
            System.out.println("Record: "+record);
            if(record.equals(""))
            {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Record",String.valueOf(intPoints));
                editor.apply();
            }
            else if(Integer.valueOf(record)<intPoints)
            {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Record",String.valueOf(intPoints));
                editor.apply();
            }
        }
    }
}
