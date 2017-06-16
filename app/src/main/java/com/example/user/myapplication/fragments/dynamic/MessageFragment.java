package com.example.user.myapplication.fragments.dynamic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.user.myapplication.R;

/**
 * Created by User on 2017-06-15.
 */

public class MessageFragment extends Fragment {

    View rootView;
    TextView tvMessage;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.layout_message, container, false);
        initializeMyComponent();
        return rootView;
    }

    private void initializeMyComponent(){
        tvMessage = (TextView) rootView.findViewById(R.id.tv_message);
    }

    public void showSelectedValue(String str){
        tvMessage.setText(str);
    }
}
