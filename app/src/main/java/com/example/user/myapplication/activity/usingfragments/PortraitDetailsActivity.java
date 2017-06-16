package com.example.user.myapplication.activity.usingfragments;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import com.example.user.myapplication.R;
import com.example.user.myapplication.constants.ConstantDetails;
import com.example.user.myapplication.fragments.dynamic.MessageFragment;

/**
 * Created by User on 2017-06-16.
 */

public class PortraitDetailsActivity extends AppCompatActivity {

    private MessageFragment fragmentMessage;
    private String selectedValue;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_single_fragment);

        selectedValue = getIntent().getStringExtra(ConstantDetails.TAG_MESSAGE);
    }

    @Override
    protected void onResume() {
        super.onResume();

        fragmentMessage =(MessageFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_message);
        fragmentMessage.showSelectedValue(selectedValue);
    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}
