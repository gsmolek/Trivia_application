package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button arcadeButton;
    private Button practiceButton;
    private Button allList;
    private String bat;
    public static final int BATTERY_PERMISSION = 1111;
    AppCompatActivity save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arcadeButton=findViewById(R.id.Arcadebutton);
        practiceButton=findViewById(R.id.Practicebutton);
        allList=findViewById(R.id.Allquestionsbutton);

        save=this;
        startConnectivity();
        arcadeButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent myintent=new Intent(MainActivity.this,ArcadeActivity.class);
                startActivity(myintent);
            }
        });
        allList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent=new Intent(MainActivity.this,AllQuestionsList.class);
                startActivity(myintent);
            }
        });
    }
    public void startConnectivity()
    {
        IntentFilter filter =new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(new NetworkChangeReciever(),filter);
        registerBattery();
    }
    public void practiceClicked(View view)
    {
        Intent returnBtn = new Intent(getApplicationContext(), ChooseNumOfQuestions.class);
        startActivity(returnBtn);
       // setContentView(R.layout.activity_choose_num_of_questions);
        //setContentView(R.layout.practice_layout);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case BATTERY_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    registerBattery();
                }
            }
        }
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
                Toast.makeText(this,"My Record selected",Toast.LENGTH_SHORT).show();
                Intent returnBtn = new Intent(getApplicationContext(), MyRecord.class);
                startActivity(returnBtn);
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
        Intent returnBtn = new Intent(getApplicationContext(),
                MainActivity.class);

        startActivity(returnBtn);
    }
    private void registerBattery() {
        BattaryActivity batteryStatusReceiver = new BattaryActivity();
        registerReceiver(batteryStatusReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }
}
