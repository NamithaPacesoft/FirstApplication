package com.example.user.myapplication.fragments.dynamic;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import com.example.user.myapplication.R;

import static com.example.user.myapplication.constants.ConstantDetails.TAG_MESSAGE;


/**
 * Created by User on 2017-06-14.
 */

public class InfoFragment extends Fragment {
    View rootView;
    TextView tvInfo;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(getClass().getSimpleName(),"onCreate() has been called");
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(getClass().getSimpleName(),"onCreateView() has been called");
        rootView=  inflater.inflate(R.layout.layout_info, container, false);
        tvInfo = (TextView) rootView.findViewById(R.id.tv_info);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i(getClass().getSimpleName(),"onActivityCreated() has been called");
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            tvInfo.setText(savedInstanceState.getString(TAG_MESSAGE));
        }
    }

    @Override
    public void onStart() {
        Log.i(getClass().getSimpleName(),"onStart() has been called");
        super.onStart();

    }

    @Override
    public void onResume() {
        Log.i(getClass().getSimpleName(),"onResume() has been called");
        super.onResume();

    }

    @Override
    public void onPause() {
        Log.i(getClass().getSimpleName(),"onPause() has been called");
        super.onPause();

    }

    @Override
    public void onStop() {
        Log.i(getClass().getSimpleName(),"onStop() has been called");
        super.onStop();

    }

    @Override
    public void onDestroyView() {
        Log.i(getClass().getSimpleName(),"onDestroyView() has been called");
        super.onDestroyView();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(getClass().getSimpleName(),"onDestroy() has been called");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(getClass().getSimpleName(),"onDetach() has been called");
    }

    public void receiveMessage(String text){
        tvInfo.setText(text);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(getClass().getSimpleName(),"onAttach() has been called");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(getClass().getSimpleName(),"onSaveInstanceState() has been called");
        if(!tvInfo.getText().toString().trim().equals("")){
            outState.putString(TAG_MESSAGE,tvInfo.getText().toString());
        }

    }
}
