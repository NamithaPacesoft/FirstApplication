package com.example.user.myapplication.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;

import com.example.user.myapplication.R;
import com.example.user.myapplication.asynctask.SampleAsycTask;
import com.example.user.myapplication.asynctask.SampleAsycTaskListener;

/**
 * Created by User on 2017-06-01.
 */

public class AsyncTaskActivity extends AppCompatActivity implements SampleAsycTaskListener{


    private EditText etUrl;
    private Button btnSave;
    private ProgressDialog pdAsyncTask;
    private SampleAsycTask objMyAsyncTask;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        initializeMyActivity();
        setActionListener();

    }

    public void initializeMyActivity(){
        etUrl = (EditText) findViewById(R.id.et_url);
        btnSave=(Button) findViewById(R.id.btn_ok);
    }

    public void setActionListener(){
        btnSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                createAsyncTask();
            }
        });
    }

    private void createAsyncTask(){
        String strUrl= etUrl.getText().toString();
        if (!strUrl.isEmpty()){
            objMyAsyncTask = new SampleAsycTask(this);
            objMyAsyncTask.execute( strUrl);
        }
    }

    private void showProgress(){
        pdAsyncTask = ProgressDialog.show(AsyncTaskActivity.this,"Async Task Example","Wait for "+etUrl.getText().toString()+" seconds");
    }

    @Override
    protected void onDestroy() {

        if(objMyAsyncTask != null && (objMyAsyncTask.getStatus() == AsyncTask.Status.PENDING ||
                objMyAsyncTask.getStatus() == AsyncTask.Status.RUNNING)){
            objMyAsyncTask.cancel(true);

        }
        super.onDestroy();
    }

    @Override
    public void startingExecution() {
        showProgress();
    }

    @Override
    public void showProgress(String status) {

    }

    @Override
    public void finishExecution(Object result) {
        pdAsyncTask.dismiss();
    }
}
