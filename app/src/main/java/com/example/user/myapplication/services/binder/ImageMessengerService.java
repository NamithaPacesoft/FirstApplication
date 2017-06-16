package com.example.user.myapplication.services.binder;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.user.myapplication.constants.ConstantDetails;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by User on 2017-06-05.
 */

public class ImageMessengerService extends Service {


    private Messenger messengerSender;
    private Messenger messengerReciever = new Messenger(new ImageHander());
    public class ImageHander extends Handler {

        @Override
        public void handleMessage(Message msgReceived) {
           switch (msgReceived.what){
               case ConstantDetails.MSG_TYPE_DOWNLOAD_IMAGE:{

                        messengerSender = msgReceived.replyTo;
                        fnDownloadImage(msgReceived.getData().getString(ConstantDetails.TAG_URL));
               }default: super.handleMessage(msgReceived);

           }
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return messengerReciever.getBinder();
    }

    private void fnDownloadImage(final String strUrl){

       new HandlerThread("ImageDownloadThread"){
           @Override
           public void run() {
               Bitmap image=null;
               InputStream is = null;

               try{
                   Message msgImage = Message.obtain();
                   URL link=new URL(strUrl);
                   HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
                   is= httpConn.getInputStream();
                   image = BitmapFactory.decodeStream(is);

                   msgImage.obj = image;

                   fnPackTheMessage(msgImage);

               }catch ( android.os.NetworkOnMainThreadException ex){
                   Log.e(getClass().getSimpleName(),"Exception thrown " + ex.getMessage());
               }catch(Exception ex){
                   Log.e(getClass().getSimpleName(),"Exception thrown " + ex.getMessage());
               }
               finally {
                   try {
                       if (is != null) {
                           is.close();
                       }
                   } catch (Exception ex) {
                   }
               }
           }
       }.start();
    }


     public void fnPackTheMessage(Message msgImage){
        try{
            msgImage.what =ConstantDetails.MSG_TYPE_DOWNLOAD_IMAGE;
            messengerSender.send(msgImage);
        }catch(RemoteException ex){
            Log.e(getClass().getSimpleName(),"Exception thrown " + ex.getMessage());
        }catch (Exception ex){
            Log.e(getClass().getSimpleName(),"Exception thrown " + ex.getMessage());
        }
    }
/*
    public void fnDeliverTheMessage(Message msgReceived,Message msgPrepared){
        try{
            msgReceived.replyTo.send(msgPrepared);
        }catch(RemoteException ex){
            Log.e(getClass().getSimpleName(),"Exception thrown " + ex.getMessage());
        }

    }

     */

}
