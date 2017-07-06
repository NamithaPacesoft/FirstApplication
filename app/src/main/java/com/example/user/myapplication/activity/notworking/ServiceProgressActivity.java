package com.example.user.myapplication.activity.notworking;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.myapplication.R;
import com.example.user.myapplication.services.startedservice.service.ProgressService;

/**
 * Created by User on 2017-06-03.
 */

public class ServiceProgressActivity extends AppCompatActivity {

    EditText etTime;
    Button btnOk;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_process);
        initializeMyComponents();

    }


    private void initializeMyComponents(){
        etTime = (EditText) findViewById(R.id.et_time);
        btnOk = (Button) findViewById(R.id.btn_ok);
    }

    private void addActionListener(){
        btnOk.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });
    }

    public void createProgressService(){
        Intent progressIntent = new Intent(this, ProgressService.class);
        startService(progressIntent);
    }
}
