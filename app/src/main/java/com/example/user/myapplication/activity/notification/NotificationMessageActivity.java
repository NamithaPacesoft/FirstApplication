package com.example.user.myapplication.activity.notification;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.constants.ConstantDetails;

/**
 * Created by User on 2017-06-24.
 */

public class NotificationMessageActivity extends AppCompatActivity {

    TextView tvInfo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_info);

        tvInfo=(TextView) findViewById(R.id.tv_info);

        Intent message = getIntent();

        tvInfo.setText(message.getStringExtra(ConstantDetails.TAG_MESSAGE));
    }
}
