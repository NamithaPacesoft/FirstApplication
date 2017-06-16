package com.example.user.myapplication.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by User on 2017-06-06.
 */

public class InternetCheckBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        getNetworkStatus(intent,"Network Connectivity Check");
       isNetworkConnected(context);
    }

    public void getNetworkStatus(Intent intent,String strTag){

        Log.v(strTag ,"Action : " + intent.getAction());
        Log.v(strTag,"Component :" + intent.getComponent());

        Bundle extras = intent.getExtras();
        if (extras!=null){
            for (String key: extras.keySet()){
                Log.v(strTag,key + " : "+ extras.get(key).toString());
            }
        }else{
            Log.v(strTag,"Nothing to display");
        }
    }

    public void isNetworkConnected(Context context){

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo objNetworkInfo =null;

        if (connectivityManager!=null) {

            objNetworkInfo=connectivityManager.getActiveNetworkInfo();
            if (objNetworkInfo!=null && objNetworkInfo.isConnected()){
                    Toast.makeText(context,"Network is ON",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(context,"Network is OFF",Toast.LENGTH_LONG).show();
            }
        }

    }
}
