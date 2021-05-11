package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyRecord extends AppCompatActivity {
private Button okButton;
private Button resetButton;
private TextView personalRecord;
private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_record);
        okButton=findViewById(R.id.okButtonMyRecord);
        resetButton=findViewById(R.id.resetButtonMyRecord);
        personalRecord=findViewById(R.id.myRecordTextView);
        context=this;
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetRec();
            }
        });
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnBtn = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(returnBtn);
            }
        });
        changeTheTextView();
    }
    public void resetRec()
    {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Are you sure you want to Reset the record?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                        String record = preferences.getString("Record", "");
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("Record",String.valueOf(0));
                        editor.apply();
                        personalRecord.setText("0");
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
    public void changeTheTextView()
    {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String record = preferences.getString("Record", "");
        System.out.println("Record: "+record);
        if(record.equals(""))
        {
            personalRecord.setText("0");
        }
        else
        {
            personalRecord.setText(record);
        }
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
}
