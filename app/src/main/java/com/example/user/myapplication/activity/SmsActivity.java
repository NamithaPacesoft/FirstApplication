package com.example.user.myapplication.activity;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.telephony.SmsManager;
import android.widget.Toast;

import com.example.user.myapplication.R;
import com.example.user.myapplication.broadcastreceiver.DeliveryStatusBroadcastReceiver;
import com.example.user.myapplication.broadcastreceiver.SentStatusBroadcastReceiver;


/**
 * Created by User on 2017-06-22.
 */

public class SmsActivity extends AppCompatActivity {

    EditText etMobile;
    EditText etSms;
    Button btnSend;

    TextView tvSentStatus;
    TextView tvDeliveryStatus;

    SmsSentStatus broadcastSentStatus;
    DeliveryStatusBroadcastReceiver broadcastDeliveryStatus;

    boolean isBroadCastRegistered =false;

    final String SENT="SMS_SENT";
    final String DELIVERED="SMS_DELIVERED";

    PendingIntent sentIntent;
    PendingIntent deliveryIntent;

    // Request code for READ_CONTACTS. It can be any number > 0.
    private static final int PERMISSIONS_SMS = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_send_and_report_sms);
        inititalizeMyComponent();
        addActionListener();

        Intent iSent = new Intent(this, SmsSentStatus.class);
        Intent iDelivery = new Intent(this, DeliveryStatusBroadcastReceiver.class);

        iSent.setAction(SENT);
        iDelivery.setAction(DELIVERED);

       // sentIntent=PendingIntent.getBroadcast(this,0,new Intent(this,SmsSentStatus.class),0);
        //deliveryIntent=PendingIntent.getBroadcast(this,0,new Intent(this,SmsDeliveryStatus.class),0);
        sentIntent =PendingIntent.getBroadcast(this,0,iSent,0);
        deliveryIntent  = PendingIntent.getBroadcast(this,0,iDelivery,0);

        registerReceiver(broadcastSentStatus,new IntentFilter(SENT));
        registerReceiver(broadcastDeliveryStatus,new IntentFilter(DELIVERED));


        /*
        if(savedInstanceState !=null){
            if(savedInstanceState.containsKey("Message")){
                tvSentStatus.setText("Message not sent");
            }
        }
        */
    }

    private void inititalizeMyComponent(){

        etMobile =(EditText) findViewById(R.id.et_mobile_number);
        etSms =(EditText) findViewById(R.id.et_sms);
        btnSend =(Button) findViewById(R.id.btn_send);

        tvSentStatus = (TextView) findViewById(R.id.tv_sms_status);
        tvDeliveryStatus=(TextView) findViewById(R.id.tv_delivery_status);
    }

    private void addActionListener(){
        btnSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                fnRequestPermissionsAndSendSms();
            }
        });
    }


    private void fnRequestPermissionsAndSendSms() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.SEND_SMS}, PERMISSIONS_SMS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {
            sendSMSMessage(etMobile.getText().toString(),etSms.getText().toString());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_SMS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                sendSMSMessage(etMobile.getText().toString(),etSms.getText().toString());
            }
        }
    }

    private void sendSMSMessage(String number, String message){
        SmsManager manager = SmsManager.getDefault();
        manager.sendTextMessage(number,null,message,sentIntent,deliveryIntent);

    }

    public class SmsSentStatus extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context,"SMS Sent",Toast.LENGTH_LONG).show();
                switch (getResultCode()){
                    case Activity.RESULT_OK : tvSentStatus.setText("SMS Sent");
                        Toast.makeText(context,"SMS Sent",Toast.LENGTH_LONG).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE : tvSentStatus.setText("No  Service");
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE : tvSentStatus.setText("Generic Failure");
                        break;
                    default: tvSentStatus.setText("SMS not Sent");
                }
        }
    }

    public class SmsDeliveryStatus extends  BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (getResultCode()){
                case Activity.RESULT_OK: tvDeliveryStatus.setText("Message Delivered");
                    break;
                case Activity.RESULT_CANCELED : tvDeliveryStatus.setText("Message not Delivered");
                    break;
            }
        }
    }

    @Override
    protected void onStop() {
        if(isBroadCastRegistered){
            unregisterReceiver(broadcastSentStatus);
            unregisterReceiver(broadcastDeliveryStatus);
            isBroadCastRegistered=false;
        }
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        /*
        if (isBroadCastRegistered){
                outState.putString("Message","Try again");
        }
        */
    }
}
