package com.example.user.myapplication.activity.withContentProviders;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.user.myapplication.R;

/**
 * Created by User on 2017-06-09.
 */

public class UserContactManipulationActivity extends AppCompatActivity  {

    Button btnViewContacts;
    Button btnAdd;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_contacts_main);
        initializeMyComponents();
        addActionListener();
    }

    private void initializeMyComponents(){
        btnViewContacts= (Button) findViewById(R.id.btn_list_contacts);
        btnAdd =(Button) findViewById(R.id.btn_add);
    }


    private void addActionListener(){

        btnViewContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fnRetrieveAllContacts();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                fnAddContacts();
            }
        });

    }


    private void fnRetrieveAllContacts(){
        Intent intentViewContacts = new Intent(this,UserContactViewActivity.class);
        startActivity(intentViewContacts);
    }

    private void fnAddContacts(){
        Intent intentAddContacts = new Intent(this,UserContactAddActivity.class);
        startActivity(intentAddContacts);
    }
}
