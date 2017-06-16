package com.example.user.myapplication.activity.intents;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.user.myapplication.R;

import java.net.URI;

/**
 * Created by User on 2017-06-12.
 */

public class ExampleImplicitActivity extends AppCompatActivity {

    Button btnCheck;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_single_button);

        initializeMyComponents();
        addActionListener();
    }

    private void initializeMyComponents(){
        btnCheck = (Button) findViewById(R.id.btn_check);
    }

    private void addActionListener(){
        btnCheck.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startMessageIntents();
            }
        });
    }

    private void startMessageIntents(){
      //  Intent  implicitIntent=new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.google.com"));
      //  implicitIntent.addCategory(Intent.CATEGORY_BROWSABLE);

        Intent  implicitIntent=new Intent(Intent.ACTION_SEND);
        implicitIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"mrpreeth2000@gmail.com"} );
        implicitIntent.putExtra(Intent.EXTRA_SUBJECT,"Sample mail");
        implicitIntent.putExtra(Intent.EXTRA_TEXT,"Hello how are you");
        implicitIntent.setType("text/plain");


        //Associating a chooser
        Intent implicitChooser=Intent.createChooser(implicitIntent,"Pick an application");


        // Verify that the intent will resolve to an activity
        if (implicitIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(implicitChooser);
            //startActivity(implicitIntent);
        }


    }

}
