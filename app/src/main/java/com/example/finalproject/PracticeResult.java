package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PracticeResult extends AppCompatActivity {
private ArrayList<Answer> List;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.relative);
        Bundle bundleObject=getIntent().getBundleExtra("bundle");
        List=(ArrayList<Answer>)bundleObject.getSerializable("answerList") ;
        ListView listView =(ListView) findViewById(R.id.listView);
        System.out.println("((((((((((((((((((((((((((((((("+List.size());
        AnsweAdapter adapter=new AnsweAdapter(this,R.layout.activity_practice_result,List);
        listView.setAdapter(adapter);
        /*
        for(int i=0;i<List.size();i++)
        {
            Answer newAnswer=new Answer(List.get(i).question,List.get(i).selected,List.get(i).correct,List.get(i).answerRight);
            adapter.add(newAnswer);
        }

         */

    }
    @Override
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
}
