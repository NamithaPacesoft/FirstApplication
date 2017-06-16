package com.example.user.myapplication.activity;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.os.IBinder;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.user.myapplication.R;

import com.example.user.myapplication.constants.ConstantDetails;
import com.example.user.myapplication.services.binder.ImageBinderService;

/**
 * Created by User on 2017-06-04.
 */

public class DownloadImageBoundServiceActivity extends AppCompatActivity {

    EditText etUrl;
    Button btnDisplay;
    Button btnStartDialog;

    ImageView imageDisplay;
    ProgressDialog pdImageProgress;
    ImageBinderService objBinderService;
    ImageBinderService.ImageBinder objImageBinder;
    Boolean isServiceBound;
    Bitmap imageDownloaded;



    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);

        Log.i(ConstantDetails.TAG_CLASS_LOGGER + getClass().getSimpleName(), "\n\n onCreate: Called ");
        initializeMyComponents();
        addActionListener();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i(ConstantDetails.TAG_CLASS_LOGGER + getClass().getSimpleName(), "\n\n onStart: Called ");
        Intent intentDownloadImage = new Intent(this,ImageBinderService.class);
        bindService(intentDownloadImage,connImageDownload,BIND_AUTO_CREATE);

    }

    public void initializeMyComponents(){

        isServiceBound =false;
        etUrl = (EditText) findViewById(R.id.et_image_url);
        btnDisplay = (Button) findViewById(R.id.btn_image_display);
        imageDisplay = (ImageView) findViewById(R.id.image_dispaly);
        /*
        btnStartDialog=(Button)findViewById(R.id.btn_start_dialog);

        pdImageProgress=new ProgressDialog(this);
        pdImageProgress.setMessage("Sleeping");
        */
    }


    public void addActionListener(){
        btnDisplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                fnDownloadImage();
            }
        });

        /*
        btnStartDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pdImageProgress.show();
                long count = (100000000L);

                for(long i = 0; i < count;i++){

                }

                pdImageProgress.dismiss();
            }
        });
        */
    }

    private void fnDownloadImage(){
        String strUrl = etUrl.getText().toString();
        objBinderService.fnDownloadImage(strUrl,imageDisplay);
        //imageDownloaded= objBinderService.getDownloadedImage();
      //  imageDisplay.setImageBitmap(imageDownloaded);
    }




    private ServiceConnection connImageDownload = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            objImageBinder = (ImageBinderService.ImageBinder) service;
            objBinderService = objImageBinder.getService();

            isServiceBound =true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isServiceBound =false;
        }
    };

    @Override
    protected void onPause() {
        Log.i(ConstantDetails.TAG_CLASS_LOGGER + getClass().getSimpleName(), "\n\n onPause: Called ");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.i(ConstantDetails.TAG_CLASS_LOGGER + getClass().getSimpleName(), "\n\n onStop: Called ");
        if (isServiceBound){
            unbindService(connImageDownload);
            isServiceBound =false;
        }
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(ConstantDetails.TAG_CLASS_LOGGER + getClass().getSimpleName(), "\n\n onDestroy: Called ");
        super.onDestroy();
    }
}
