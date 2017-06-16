package com.example.user.myapplication.activity.usingfragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.user.myapplication.R;
import com.example.user.myapplication.fragments.dynamic.ButtonFragment;
import com.example.user.myapplication.fragments.dynamic.InfoFragment;

/**
 * Created by User on 2017-06-14.
 */

public class FragmentTestActivity extends AppCompatActivity implements ButtonFragment.ButtonListener {

    InfoFragment fragmentInfo;
    ButtonFragment fragment;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(getClass().getSimpleName(),"----------------------------------");
        Log.i(getClass().getSimpleName(),"\n\nonCreate() has been called");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_with_fragments);

        if (savedInstanceState == null){
            addFragments();
        }else{
            fragmentInfo = (InfoFragment) getSupportFragmentManager().findFragmentByTag(InfoFragment.class.getSimpleName());
        }
    }


    private void addFragments(){

        //setRetainInstance for fragments.
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragment = new ButtonFragment();
        fragmentTransaction.add(R.id.ll_fragment_top,fragment, ButtonFragment.class.getSimpleName());


        fragmentInfo=new InfoFragment();
        fragmentTransaction.add(R.id.ll_fragment_bottom,fragmentInfo, InfoFragment.class.getSimpleName());
        fragmentTransaction.commit();
    }


    @Override
    public void showMessage(String message) {
        fragmentInfo.receiveMessage(message);
    }

    /*
    public void showAlertMessage(){
        AlertDialog.Builder builder=new AlertDialog;
        builder.setMessage("message")
        AlertDialog alretDialog = bulder.create();
        alretDialog.setCancelable(true);
        alretDialog.show
    }
    */

    @Override
    protected void onResume() {
        Log.i(getClass().getSimpleName(),"onResume() has been called");
        super.onResume();

    }


    @Override
    protected void onPause() {
        Log.i(getClass().getSimpleName()," Before super onPause() has been called");
        super.onPause();

        Log.i(getClass().getSimpleName()," After super onPause() has been called");
    }

    @Override
    protected void onDestroy() {
        Log.i(getClass().getSimpleName(),"Before onDestory() has been called");
        super.onDestroy();

        Log.i(getClass().getSimpleName(),"After onDestory() has been called");
    }

    @Override
    protected void onStart() {
        Log.i(getClass().getSimpleName(),"onStart() has been called");
        super.onStart();


    }


    @Override
    protected void onRestart() {
        Log.i(getClass().getSimpleName(),"onRestart() has been called");
        super.onRestart();

    }

    @Override
    protected void onStop() {
        Log.i(getClass().getSimpleName(),"onStop() has been called");
        super.onStop();


    }

    @Override
    protected void onPostResume() {
        Log.i(getClass().getSimpleName(),"onPostResume() has been called");
        super.onPostResume();


    }

}
