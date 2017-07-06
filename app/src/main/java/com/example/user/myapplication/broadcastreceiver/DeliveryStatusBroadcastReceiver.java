package com.example.user.myapplication.broadcastreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by User on 2017-06-23.
 */

public class DeliveryStatusBroadcastReceiver extends BroadcastReceiver {


    String status;
    @Override
    public void onReceive(Context context, Intent intent) {
        switch (getResultCode()){

                case Activity.RESULT_OK:  Toast.makeText(context,"Message Delivered",Toast.LENGTH_LONG).show();
                    break;
                case Activity.RESULT_CANCELED :  Toast.makeText(context,"Message Not Delivered",Toast.LENGTH_LONG).show();
                    break;


        }
    }
}
