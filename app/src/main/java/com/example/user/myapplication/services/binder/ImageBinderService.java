package com.example.user.myapplication.services.binder;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.NetworkOnMainThreadException;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.user.myapplication.constants.ConstantDetails;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by User on 2017-06-04.
 */

public class ImageBinderService extends Service {

    private ImageBinder imageBinderObject=new ImageBinder();

    private HandlerThread imageThread;
    public Bitmap imageDownoaded;

    private Handler imageHandler;



    public class ImageBinder extends Binder {
        public ImageBinderService getService(){
            return ImageBinderService.this;
        }


    }

 /*   public class CustomImageHandler extends Handler{

        public CustomImageHandler(Looper looper){
            super(looper);
        }
        @Override
        public void handleMessage(Message msg) {
            imageDownoaded = (Bitmap) msg.obj;
        }
    }
*/

    public Bitmap getDownloadedImage(){
        return imageDownoaded;
    }

    @Override
    public void onCreate() {
        Log.i(ConstantDetails.TAG_SERVICE_LOGGER + getClass().getSimpleName(), "\n\n onCreate: Called ");
        super.onCreate();

    }

    @Override
    public void onStart(Intent intent, int startId) {
        Log.i(ConstantDetails.TAG_SERVICE_LOGGER + getClass().getSimpleName(), "\n\n onStart: Called ");
        super.onStart(intent, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(ConstantDetails.TAG_SERVICE_LOGGER + getClass().getSimpleName(), "\n\n onBind: Called ");
        return imageBinderObject;
    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(ConstantDetails.TAG_SERVICE_LOGGER + getClass().getSimpleName(), "\n\n onUnbind: Called ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i(ConstantDetails.TAG_SERVICE_LOGGER + getClass().getSimpleName(), "\n\n onDestroy: Called ");
        super.onDestroy();
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(ConstantDetails.TAG_SERVICE_LOGGER + getClass().getSimpleName(), "\n\n onRebind: Called ");
        super.onRebind(intent);
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        Log.i(ConstantDetails.TAG_SERVICE_LOGGER + getClass().getSimpleName(), "\n\n onTaskRemoved: Called ");
        super.onTaskRemoved(rootIntent);
    }


    public Bitmap fnDownloadImage(final String strUrl){
        Bitmap image=null;
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });


        return image;
    }



    public void fnDownloadImage(final String  strUrl, final ImageView myImageView){
       final Message msgImage = new Message();
        imageThread = new HandlerThread("imageThread"){
            @Override
            public void run() {

                Bitmap image=null;
                InputStream is = null;

                try{

                    URL link=new URL(strUrl);
                    HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
                    is= httpConn.getInputStream();
                    image = BitmapFactory.decodeStream(is);

                    msgImage.obj = image;

                   // myImageView.setImageBitmap(image);
                    imageHandler.sendMessage(msgImage);
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
        };
        imageThread.start();
      //  Looper looperImageThread = imageThread.getLooper();

        imageHandler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(Message msg) {
              myImageView.setImageBitmap((Bitmap) msg.obj);
               // et.setText("It is working");
            }
        };
    }

}
