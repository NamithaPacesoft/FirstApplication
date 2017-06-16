package com.example.user.myapplication.activity.notworking;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.services.binder.notworking.ImageBinderIntentService;


/**
 * Created by User on 2017-06-05.
 */

public class DownloadImageBoundIntentServiceActivity  extends AppCompatActivity{
    EditText etUrl;
    Button btnDisplay;
    ImageView imageViewDisplay;
    ProgressDialog pdImageProgress;
    ImageBinderIntentService objBinderService;
    ImageBinderIntentService.ImageBinder objImageBinder;
    Boolean isServiceBound;
    Bitmap imageDownloaded;



    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);

        initializeMyComponents();
        addActionListener();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Intent intentDownloadImage = new Intent(this,ImageBinderIntentService.class);
        bindService(intentDownloadImage, serviceConnImageDownload,BIND_AUTO_CREATE);

    }

    public void initializeMyComponents(){

        isServiceBound =false;
        etUrl = (EditText) findViewById(R.id.et_image_url);
        btnDisplay = (Button) findViewById(R.id.btn_image_display);
        imageViewDisplay = (ImageView) findViewById(R.id.image_dispaly);
    }


    public void addActionListener(){
        btnDisplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                fnDownloadImage();
            }
        });
    }

    private void fnDownloadImage(){
        Bitmap image;
        String strUrl = etUrl.getText().toString();
        image=objBinderService.fnDownloadImage(strUrl);
        imageViewDisplay.setImageBitmap(image);
    }




    private ServiceConnection serviceConnImageDownload = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            objImageBinder = (ImageBinderIntentService.ImageBinder) service;
            objBinderService = objImageBinder.getService();

            isServiceBound =true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isServiceBound =false;
        }
    };

    @Override
    protected void onStop() {

        if (isServiceBound){
            unbindService(serviceConnImageDownload);
            isServiceBound =false;
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
