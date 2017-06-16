package com.example.user.myapplication.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.constants.ConstantDetails;
import com.example.user.myapplication.services.binder.ImageMessengerService;

/**
 * Created by User on 2017-06-05.
 */

public class DownloadImageMessengerServiceActivity extends AppCompatActivity {

    private EditText etUrl;
    private Button btnDisplay;
    private ImageView imgViewDisplay;
    private Boolean isServiceBound;
    private Messenger messengerSender;

    private Messenger messengerReceiver = new Messenger(new ImageHandler());

    class ImageHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case ConstantDetails.MSG_TYPE_DOWNLOAD_IMAGE:{
                   imgViewDisplay.setImageBitmap ((Bitmap)msg.obj);
                }default: super.handleMessage(msg);
            }

        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);

        initializeMyComponents();
        addActionListener();
    }
    @Override
    protected void onStart() {
        super.onStart();

        Intent intentImageDownload = new Intent(this, ImageMessengerService.class);
        bindService(intentImageDownload,serviceConnDownloadImage,BIND_AUTO_CREATE);
    }

    public void initializeMyComponents(){

        isServiceBound =false;
        etUrl = (EditText) findViewById(R.id.et_image_url);
        btnDisplay = (Button) findViewById(R.id.btn_image_display);
        imgViewDisplay = (ImageView) findViewById(R.id.image_dispaly);

    }

    public void addActionListener(){
        btnDisplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                prepareAndSendData();
            }
        });

    }

    private void prepareAndSendData(){

        //Preparing data
        Message messageToSend =Message.obtain();
        messageToSend.getData().putString(ConstantDetails.TAG_URL,etUrl.getText().toString());
        messageToSend.what=ConstantDetails.MSG_TYPE_DOWNLOAD_IMAGE;
        messageToSend.replyTo= messengerReceiver;


        //Sending Data
        try{
            messengerSender.send(messageToSend);
        }catch (RemoteException ex){
            Log.e(getClass().getSimpleName(),"Exception thrown " + ex.getMessage());
        }
    }


    @Override
    protected void onStop() {
        if(isServiceBound){
            unbindService(serviceConnDownloadImage);
            isServiceBound=false;
        }
        super.onStop();
    }

    private ServiceConnection serviceConnDownloadImage =new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messengerSender = new Messenger(service);
            isServiceBound=true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isServiceBound=false;
        }
    };
}
