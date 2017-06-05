package com.example.user.myapplication.activity.notworking;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.services.startedservice.ImageService;

/**
 * Created by User on 2017-06-03.
 */

public class ServiceImageActivity extends AppCompatActivity {

    EditText etUrl;
    Button btnDisplay;
    ImageView imageDisplay;
    ProgressDialog pdImageProgress;
    public static final String URL="URL";
    public static final String IMAGE="IMAGE";
    public static final String TAG_RECEIVER="SERVICE_RECEIVER";



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_image);

        initializeMyComponents();

    }

    public void initializeMyComponents(){
        etUrl = (EditText) findViewById(R.id.et_url);
        btnDisplay = (Button) findViewById(R.id.btn_image_display);
        imageDisplay = (ImageView) findViewById(R.id.image_dispaly);
    }


    public void addActionListener(){
        btnDisplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                createProgressService();
            }
        });
    }

    public void showProgress(){
        pdImageProgress= ProgressDialog.show(this,"Downloading Through Service","Please Wait till the file is downloaded");
    }

    public void createProgressService(){
        showProgress();
        Intent imageIntent = new Intent(this, ImageService.class);
        imageIntent.putExtra(URL,etUrl.getText().toString());
        imageIntent.putExtra(TAG_RECEIVER,new ResultReceiver(new Handler()){
            @Override
            protected void onReceiveResult(int resultCode, Bundle resultData) {
                if (resultCode== Activity.RESULT_OK){
                    Bitmap bDownloadedImage = (Bitmap) resultData.get(IMAGE);
                    imageDisplay.setImageBitmap(bDownloadedImage);
                }
            }
        });

        startService(imageIntent);
    }



}
