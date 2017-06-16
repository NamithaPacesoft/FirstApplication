package com.example.user.myapplication.fragments.dynamic;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.myapplication.R;

import static com.example.user.myapplication.constants.ConstantDetails.TAG_MESSAGE;

/**
 * Created by User on 2017-06-14.
 */

public class ButtonFragment extends Fragment {

    View rootView;
    Button btnCheck;
    EditText tvInfo;
    ButtonListener listenerButton;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView=  inflater.inflate(R.layout.layout_single_button, container, false);
        btnCheck = (Button) rootView.findViewById(R.id.btn_check);
        tvInfo = (EditText)rootView.findViewById(R.id.tv_button_info);
        Log.i(getClass().getSimpleName(),"onCreateView() has been called");
        listenerButton =(ButtonListener)getActivity();
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerButton.showMessage(tvInfo.getText().toString());
            }
        });
        return rootView;
    }

    public interface ButtonListener{
         void showMessage(String message);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i(getClass().getSimpleName(),"onCreate() has been called");
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.i(getClass().getSimpleName(),"onActivityCreated() has been called");
        super.onActivityCreated(savedInstanceState);

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
        Log.i(getClass().getSimpleName(),"onDestroy() has been called");
        super.onDestroy();

    }

    @Override
    public void onDetach() {
        Log.i(getClass().getSimpleName(),"onDetach() has been called");
        super.onDetach();

    }

    @Override
    public void onAttach(Context context) {
        Log.i(getClass().getSimpleName(),"onAttach() has been called");
        super.onAttach(context);

    }
}
