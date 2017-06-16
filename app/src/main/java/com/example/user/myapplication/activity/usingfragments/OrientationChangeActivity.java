package com.example.user.myapplication.activity.usingfragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.example.user.myapplication.R;
import com.example.user.myapplication.constants.ConstantDetails;
import com.example.user.myapplication.fragments.dynamic.MessageFragment;
import com.example.user.myapplication.fragments.fixed.ListViewFragment;

import java.util.List;

/**
 * Created by User on 2017-06-16.
 */

public class OrientationChangeActivity extends AppCompatActivity implements ListViewFragment.FragmentClickable {

    FrameLayout frameOrietation;
    boolean isLandscape;
    MessageFragment fragmentMessage;

    ListViewFragment fragmentListView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.layout_orientation_change);

        initializeMyComponents();

        if(savedInstanceState !=null){
            fragmentListView = (ListViewFragment) getSupportFragmentManager().findFragmentByTag(ListViewFragment.class.getSimpleName());

            if (fragmentListView == null){
                //Create the fragment
                initializeMyFragment();
            }
        }else{
            //Create the fragment
            initializeMyFragment();
        }
    }


    private  void initializeMyComponents(){
        frameOrietation = (FrameLayout) findViewById(R.id.frame_list);

        if(frameOrietation !=null){
            isLandscape=true;
        }else{
            isLandscape=false;
        }
    }

     private void initializeMyFragment(){
         if(isLandscape){
             //Set the fragment to the Frame layout
             fragmentMessage = new MessageFragment();
             FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
             transaction.add(frameOrietation.getId(),fragmentMessage,MessageFragment.class.getSimpleName());
             transaction.commit();
         }
     }


    @Override
    public void selectedValue(String str) {
        if (isLandscape){
            fragmentMessage.showSelectedValue(str);
        }else{
            Intent messageIntent= new Intent(this,PortraitDetailsActivity.class);
            messageIntent.putExtra(ConstantDetails.TAG_MESSAGE,str);
            startActivity(messageIntent);
            //startActivityForResult(messageIntent,12);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
