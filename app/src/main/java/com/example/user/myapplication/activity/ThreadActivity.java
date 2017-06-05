package com.example.user.myapplication.activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.user.myapplication.R;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by User on 2017-06-02.
 */

public class ThreadActivity extends AppCompatActivity {


    private EditText etUrl;
    private Button btnSave;
    private ImageView imageSample;
    private ProgressDialog pdImageProgress;
    private String strUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_async_task);

        initializeMyActivity();
        setActionListener();

        strUrl  ="http://wallpaper-gallery.net/images/images-of-cute-animals/images-of-cute-animals-10.jpg";
    }



    public void initializeMyActivity(){
        etUrl = (EditText) findViewById(R.id.et_url);
        btnSave=(Button) findViewById(R.id.btn_ok);
        imageSample = (ImageView) findViewById(R.id.image_sample);
    }

    public void setActionListener(){
        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                createImageThread();
            }
        });
    }

    public void showProgress(){
        pdImageProgress= ProgressDialog.show(this,"Downloading...","Please Wait till the file is downloaded");
    }

    public void createImageThread(){
        showProgress();
        new Thread(){
            @Override
            public void run() {
                Bitmap image =null;
                Message msgImage= new Message();
                InputStream is = null;
                try{
                    URL link=new URL(strUrl);
                    HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
                     is= httpConn.getInputStream();
                    image = BitmapFactory.decodeStream(is);
                    msgImage.obj  =image;
                    imageThreadHandler.sendMessage(msgImage);
                }catch (Exception ex){
                    Log.e(getClass().getSimpleName(),"Exception thrown " + ex.getMessage());
                }finally {
                    try{
                        if (is !=null){
                            is.close();
                        }
                    }catch (Exception ex){
                    }
                }
            }
        }.start();
    }

    private Handler imageThreadHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            Bitmap bDownloadedImage = (Bitmap) msg.obj;
            imageSample.setImageBitmap(bDownloadedImage);
            pdImageProgress.dismiss();
        }
    };
}
