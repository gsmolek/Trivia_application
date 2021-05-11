package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class ChooseNumOfQuestions extends AppCompatActivity {
private Button okButton;
private EditText numberOfQuestions;
private ImageView v;
private ImageView x;
private int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_num_of_questions);
        okButton=findViewById(R.id.buttonOKPractice);
        numberOfQuestions=findViewById(R.id.editTextNoQuestion);
        v=findViewById(R.id.vimage);
        x=findViewById(R.id.ximage);
        numberOfQuestions.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String toCheck=numberOfQuestions.getText().toString();
                String ns;
                int flag2=0;
                ns=toCheck.replace("-","");
                System.out.println(toCheck);
                if(ns.equals("")) {
                    flag2 = 1;
                    System.out.println("flag2");
                }
                else
                    {
                        flag2=0;
                    }
                if(numberOfQuestions.getText().toString().equals("") ||
                        flag2==0&&Integer.valueOf(toCheck)<=0)
                {

                    flag=0;
                    v.setVisibility(View.INVISIBLE);
                    x.setVisibility(View.VISIBLE);
                }
                else if(flag2==0 &&Integer.valueOf(numberOfQuestions.getText().toString())>0)
                {
                    flag=1;
                    x.setVisibility(View.INVISIBLE);
                    v.setVisibility(View.VISIBLE);
                }
            }
        });
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
    public void clickedOK(View view)
    {
        if(flag==1)
        {
            Intent intent = new Intent(getApplicationContext(),practice.class);
            intent.putExtra("numberOfQuestions",numberOfQuestions.getText().toString());
            startActivity(intent);
        }
    }
}
