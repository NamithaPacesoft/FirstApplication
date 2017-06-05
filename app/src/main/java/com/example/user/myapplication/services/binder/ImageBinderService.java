package com.example.user.myapplication.services.binder;

import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Binder;
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
        super.onCreate();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return imageBinderObject;
    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        imageThread.interrupt();
        imageThread.quit();
        super.onDestroy();
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
