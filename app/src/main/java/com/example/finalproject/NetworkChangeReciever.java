package com.example.finalproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

public class NetworkChangeReciever extends BroadcastReceiver {
    @Override
    public void onReceive(final Context context, final Intent intent) {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo wifi = connMgr.getActiveNetworkInfo();

        if ( wifi.getTypeName().equals("MOBILE") ) {
            Toast.makeText(context, "Wifi Turn Off", Toast.LENGTH_LONG).show();
            Log.d("Network Available ", "Flag No 0");
        }
        else if (wifi.getType() == ConnectivityManager.TYPE_WIFI) {
            Toast.makeText(context, "Wifi Turn On", Toast.LENGTH_LONG).show();
            Log.d("Network Available ", "Flag No 1");
        }
    }
}
