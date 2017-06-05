package com.example.user.myapplication.services.binder.notworking;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by User on 2017-06-05.
 */

public class ImageBinderIntentService extends IntentService {


    private ImageBinder imageBinderObject=new ImageBinder();

    public class ImageBinder extends Binder {
        public ImageBinderIntentService getService(){
            return ImageBinderIntentService.this;
        }

    }

    public ImageBinderIntentService(){
        super("MyBinderIntentService");
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return imageBinderObject;
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    public Bitmap fnDownloadImage(final String  strUrl){

        Bitmap image=null;
        InputStream is = null;

        try{
            URL link=new URL(strUrl);
            HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
            is= httpConn.getInputStream();
            image = BitmapFactory.decodeStream(is);



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

        return  image;
    }

}
