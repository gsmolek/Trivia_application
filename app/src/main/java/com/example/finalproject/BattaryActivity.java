package com.example.finalproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class BattaryActivity extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null){
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
            if (level <= 15){
                Toast.makeText(context, "Batter level " + level + "% is very low. Please connect to a charger.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
