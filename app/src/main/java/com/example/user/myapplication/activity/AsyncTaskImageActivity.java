package com.example.user.myapplication.activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.asynctask.SampleAsycTaskListener;
import com.example.user.myapplication.asynctask.SampleImageAsyncTask;

/**
 * Created by User on 2017-06-01.
 */

public class AsyncTaskImageActivity extends AppCompatActivity implements SampleAsycTaskListener {

    private EditText etUrl;
    private Button btnSave;
    private ImageView imageSample;
    private ProgressDialog pdAsyncTask;
    private SampleImageAsyncTask objMyImageAsyncTask;
    private String strUrl;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
        initializeMyActivity();
        setActionListener();

        strUrl="http://wallpaper-gallery.net/images/images-of-cute-animals/images-of-cute-animals-10.jpg";
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
                createAsyncTask();
            }
        });
    }

    public void createAsyncTask(){

        objMyImageAsyncTask = new SampleImageAsyncTask(this);
        //objMyImageAsyncTask.execute("https://a.disquscdn.com/uploads/mediaembed/images/669/1419/original.jpg");
        //http://wallpaper-gallery.net/images/images-of-cute-animals/images-of-cute-animals-14.jpg
        //objMyImageAsyncTask.execute("http://wallpaper-gallery.net/images/images-of-cute-animals/images-of-cute-animals-1.jpg");
        objMyImageAsyncTask.execute(strUrl);
    }

    @Override
    public void startingExecution() {
        showProgress("Please wait till the download is done");
    }

    @Override
    public void showProgress(String status) {
        pdAsyncTask=ProgressDialog.show(this,"Downloading image",status);
    }

    @Override
    public void finishExecution(Object result) {
        Bitmap image = (Bitmap) result;

        imageSample.setImageBitmap(image);
        pdAsyncTask.dismiss();
    }

    @Override
    protected void onDestroy() {

        if(objMyImageAsyncTask != null && (objMyImageAsyncTask.getStatus() == AsyncTask.Status.PENDING ||
                objMyImageAsyncTask.getStatus() == AsyncTask.Status.RUNNING)){
            objMyImageAsyncTask.cancel(true);

        }
        super.onDestroy();
    }
}
