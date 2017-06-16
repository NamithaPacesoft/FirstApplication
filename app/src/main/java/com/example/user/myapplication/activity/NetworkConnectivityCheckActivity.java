package com.example.user.myapplication.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.user.myapplication.R;
import com.example.user.myapplication.broadcastreceiver.InternetCheckBroadcastReceiver;


/**
 * Created by User on 2017-06-07.
 */

public class NetworkConnectivityCheckActivity extends AppCompatActivity {

    private InternetCheckBroadcastReceiver broadcastReceiver;
    private Button btnCheck;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_info);

        initializeMyComponent();
        addActionListener();
    }


    private void initializeMyComponent(){
        btnCheck = (Button) findViewById(R.id.btn_check);
    }

    private void addActionListener(){
        btnCheck.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                fnBroadcastNetworkChange();
            }
        });
    }

    @Override
    protected void onStart() {

        Log.v(getClass().getSimpleName(),"OnStart() Called");
        broadcastReceiver = new InternetCheckBroadcastReceiver();
        IntentFilter intentFilterConnectivity =new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        intentFilterConnectivity.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);

        registerReceiver(broadcastReceiver,intentFilterConnectivity);
        super.onStart();
    }

    private void fnBroadcastNetworkChange(){

    }

    @Override
    protected void onStop() {
        Log.v(getClass().getSimpleName(),"OnStop() Called");
            unregisterReceiver(broadcastReceiver);


        super.onStop();
    }


}
