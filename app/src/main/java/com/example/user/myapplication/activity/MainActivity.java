package com.example.user.myapplication.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.user.myapplication.R;

public class MainActivity extends AppCompatActivity  {

    private EditText etName;
    private Button btnSave;
    public static final String KEY_MESSAGE="welcome";
    public static final String KEY_LOCATION="loc";
    public static final int KEY_RESULT =12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(getClass().getSimpleName(),"onCreate() has been called");
        initializeMyActivity();

        setListeners();
    }

    private void initializeMyActivity(){
        etName = (EditText) findViewById(R.id.et_name);
        btnSave = (Button) findViewById(R.id.btn_send);
    }

    private void setListeners(){
        btnSave.setOnClickListener(new  View.OnClickListener(){

            @Override
            public void onClick(View v){
                fnSaveClicked();
            }
        });
    }

    public void fnSaveClicked(){
            Intent intentWelcome = new Intent(this,SecondaryActivity.class);
            intentWelcome.putExtra(KEY_MESSAGE,etName.getText().toString());

            startActivityForResult(intentWelcome, KEY_RESULT);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String strLocation;

        if (requestCode== KEY_RESULT){
            if (resultCode == RESULT_OK){
              strLocation=  data.getStringExtra(KEY_LOCATION);
                Toast.makeText(this, "Your Location is " + strLocation, Toast.LENGTH_SHORT).show();
            }
        }
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
