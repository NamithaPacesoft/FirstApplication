package com.example.user.myapplication.services.startedservice;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.user.myapplication.activity.notworking.ServiceImageActivity;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by User on 2017-06-03.
 */

public class ImageService extends Service {




    private Handler imageThreadHandler;
    //private ResultReceiver receiverImage;


    class CustomImagerHandler extends Handler{
        public CustomImagerHandler(Looper looper){
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {


           //Cannot send message from a started service to the Activity
            //For this purpose the Bound Serice needs to be used.
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags,final int startId) {
        final String strUrl=  intent.getStringExtra(ServiceImageActivity.URL);

        //receiverImage = intent.getParcelableExtra(ServiceImageActivity.TAG_RECEIVER);

        HandlerThread  htImage=new HandlerThread("ExampleThread"){
            @Override
            public void run() {


                Bitmap image=null;
                Message msgImage= new Message();
                msgImage.arg1=startId;
                InputStream is = null;
                try{
                    URL link=new URL(strUrl);
                    HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
                    is= httpConn.getInputStream();
                    image = BitmapFactory.decodeStream(is);
                    msgImage.obj  =image;
                    imageThreadHandler.sendMessage(msgImage);
                }catch (Exception ex){
                    Log.e(getClass().getSimpleName(),"Exception thrown " + ex.getMessage());
                }finally {
                    try {
                        if (is != null) {
                            is.close();
                        }
                    } catch (Exception ex) {
                    }
                }
            }


        };

        imageThreadHandler = new CustomImagerHandler(htImage.getLooper());
        htImage.start();
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
