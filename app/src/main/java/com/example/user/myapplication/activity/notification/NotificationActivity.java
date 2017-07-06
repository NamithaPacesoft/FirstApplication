package com.example.user.myapplication.activity.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.myapplication.R;
import com.example.user.myapplication.activity.SecondaryActivity;
import com.example.user.myapplication.constants.ConstantDetails;

/**
 * Created by User on 2017-06-24.
 */

public class NotificationActivity extends AppCompatActivity {

    EditText etText;
    Button btnSend;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_single_button);
        initializeMyComponents();

        addActionListener();
    }

    private void initializeMyComponents(){
        etText=(EditText) findViewById(R.id.tv_button_info);
        btnSend =(Button) findViewById(R.id.btn_check);
    }
    int mId=1;
    private void addActionListener(){
        btnSend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {



                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(getApplicationContext())
                                .setSmallIcon(R.drawable.ic_today)
                                .setContentTitle("My notification")
                                .setContentText("Hello World!")
                                .setAutoCancel(true);
                // Creates an explicit intent for an Activity in your app
                Intent parentIntent = new Intent(getApplicationContext(), NotificationActivity.class);
                Intent childIntent = new Intent(getApplicationContext(), NotificationMessageActivity.class);

                childIntent.putExtra(ConstantDetails.TAG_MESSAGE,etText.getText().toString());
                // The stack builder object will contain an artificial back stack for the
                // started Activity.
                // This ensures that navigating backward from the Activity leads out of
                // your application to the Home screen.
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
                // Adds the back stack for the Intent (but not the Intent itself)
                stackBuilder.addParentStack(NotificationMessageActivity.class);
                // Adds the Intent that starts the Activity to the top of the stack
                stackBuilder.addNextIntent(parentIntent);
                stackBuilder.addNextIntent(childIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_ONE_SHOT
                        );
                mBuilder.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                // mId allows you to update the notification later on.
                mNotificationManager.notify(mId, mBuilder.build());
            }
        });
    }
}
