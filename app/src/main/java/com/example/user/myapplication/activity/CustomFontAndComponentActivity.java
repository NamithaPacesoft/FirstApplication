package com.example.user.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.user.myapplication.R;
import com.example.user.myapplication.customcomponents.QuicksandTextView;
import com.example.user.myapplication.customcomponents.Rater;
import com.example.user.myapplication.customcomponents.Rater.RaterEventListener;

/**
 * Created by User on 2017-06-22.
 */

public class CustomFontAndComponentActivity extends AppCompatActivity implements RaterEventListener {

    Rater rater ;
    QuicksandTextView tvRater;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.layout_custom_components);
        rater = (Rater) findViewById(R.id.rater_example);
        tvRater = (QuicksandTextView) findViewById(R.id.tv_quicksand);
        rater.setListner(this);
    }


    @Override
    public void onRatingChanged(int currentRating,int oldRating,Rater rater) {
        tvRater.setText("Current Rating is " + rater.getCurrentRating() + "\n Old Rating is " + oldRating);
    }


}
