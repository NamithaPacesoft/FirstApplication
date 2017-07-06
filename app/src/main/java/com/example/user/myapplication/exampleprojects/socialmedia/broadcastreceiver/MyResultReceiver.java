package com.example.user.myapplication.exampleprojects.socialmedia.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.user.myapplication.exampleprojects.socialmedia.activity.FacebookActivity;
import com.twitter.sdk.android.tweetcomposer.TweetUploadService;

/**
 * Created by User on 2017-07-03.
 */

public class MyResultReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle intentExtras = intent.getExtras();
       Log.i(getClass().getSimpleName(), "onReceive: Inside the broadcast receiver" );
        if (TweetUploadService.UPLOAD_SUCCESS.equals(intent.getAction())) {
            Log.i(getClass().getSimpleName(), "onReceive: success" );
            Toast.makeText(context.getApplicationContext(),"Success",Toast.LENGTH_LONG).show();
            // success
            // final Long tweetId = intentExtras.getLong(TweetUploadService.EXTRA_TWEET_ID);
        } else {
            Log.i(getClass().getSimpleName(), "onReceive: failure" );
            Toast.makeText(context.getApplicationContext(),"Failure",Toast.LENGTH_LONG).show();
            // failure
            //final Intent retryIntent = intentExtras.getParcelable(TweetUploadService.EXTRA_RETRY_INTENT);
        }
    }
}
