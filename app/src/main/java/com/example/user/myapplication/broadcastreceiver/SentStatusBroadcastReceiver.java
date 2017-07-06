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

public class SentStatusBroadcastReceiver extends BroadcastReceiver {

    String status;
    @Override
    public void onReceive(Context context, Intent intent) {
        switch (getResultCode()){
            case Activity.RESULT_OK :
                Toast.makeText(context,"Message Sent",Toast.LENGTH_LONG).show();
                break;
            case SmsManager.RESULT_ERROR_NO_SERVICE :  Toast.makeText(context,"No Service",Toast.LENGTH_LONG).show();;
                break;
            case SmsManager.RESULT_ERROR_GENERIC_FAILURE :  Toast.makeText(context,"Generic Failure",Toast.LENGTH_LONG).show();;
                break;
            default: status="SMS not Sent";

        }
    }
}
