package com.example.user.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.user.myapplication.R;

/**
 * Created by User on 2017-05-30.
 */

public class CallBackMethodsTest  extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(getClass().getSimpleName(),"onCreate() has been called");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(getClass().getSimpleName(),"onResume() has been called");
    }


    @Override
    protected void onPause() {
        super.onPause();

        Log.i(getClass().getSimpleName(),"onPause() has been called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.i(getClass().getSimpleName(),"onDestory() has been called");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.i(getClass().getSimpleName(),"onStart() has been called");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(getClass().getSimpleName(),"onRestart() has been called");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.i(getClass().getSimpleName(),"onStop() has been called");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        Log.i(getClass().getSimpleName(),"onPostResume() has been called");
    }
}
