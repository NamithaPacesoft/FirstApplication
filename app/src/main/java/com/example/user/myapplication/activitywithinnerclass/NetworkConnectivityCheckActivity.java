package com.example.user.myapplication.activitywithinnerclass;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.myapplication.R;



/**
 * Created by User on 2017-06-07.
 */

public class NetworkConnectivityCheckActivity extends AppCompatActivity {
    private InternetCheckBroadcastReceiver broadcastReceiver;
    private TextView tvInfo;

    String strInfo="";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_info);

        initializeMyComponent();

    }

    private void initializeMyComponent(){
        tvInfo = (TextView) findViewById(R.id.tv_info);
    }

    @Override
    protected void onStart() {

        strInfo="";
        Log.v(getClass().getSimpleName(),"OnStart() Called");
        broadcastReceiver = new InternetCheckBroadcastReceiver();
        IntentFilter intentFilterConnectivity =new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        intentFilterConnectivity.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);

        registerReceiver(broadcastReceiver,intentFilterConnectivity);
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.v(getClass().getSimpleName(),"OnStop() Called");
        unregisterReceiver(broadcastReceiver);
        super.onStop();
    }

    public class InternetCheckBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            getNetworkStatus(intent,"Network Connectivity Check");
            isNetworkConnected(context);
        }

        public void getNetworkStatus(Intent intent,String strTag){

            strInfo=strInfo + "\n\nAction : " + intent.getAction();
            strInfo=strInfo+"\nComponent : "+intent.getComponent();
            Bundle extras = intent.getExtras();
            if (extras!=null){
                for (String key: extras.keySet()){
                    strInfo=strInfo +"\n" + key+ " : " + extras.get(key).toString();
                }
            }else{
                strInfo = strInfo +  "\n No information was retrieved";
            }

            tvInfo.setText(strInfo);
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


}
