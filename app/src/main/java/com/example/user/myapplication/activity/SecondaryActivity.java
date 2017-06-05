package com.example.user.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.activity.MainActivity;

/**
 * Created by User on 2017-05-28.
 */

public class SecondaryActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView lblMessage;
    private Button btnSave;
    private EditText etLocation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        initialiseMyActivity();
        addListener();
        String strName = getIntent().getStringExtra(MainActivity.KEY_MESSAGE);

        if(strName !=null  ){
            lblMessage.setText("Welcome; " + strName);
        }

    }

    private void initialiseMyActivity(){
        lblMessage=(TextView)findViewById(R.id.lbl_message);
        btnSave=(Button) findViewById(R.id.btn_loc_save);
        etLocation= (EditText) findViewById(R.id.et_location);


    }

    private void addListener(){
       /* btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentResult = new Intent();
                intentResult.putExtra(MainActivity.KEY_LOCATION,etLocation.getText().toString());
                setResult(RESULT_OK,intentResult);
                finish();
            }
        });*/

        btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == btnSave.getId()){
            Intent intentResult = new Intent();
            intentResult.putExtra(MainActivity.KEY_LOCATION,etLocation.getText().toString());
            setResult(RESULT_OK,intentResult);
            finish();
        }

    }
}
