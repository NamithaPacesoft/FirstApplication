package com.example.user.myapplication.exampleprojects.serverrelated.volley.withsingleton.singleton;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by User on 2017-06-28.
 */

public class VolleyImageHelper {

    private static VolleyImageHelper imageHelper;
    private static Context context;
    private RequestQueue requestQueueImage;
    private ImageLoader imageLoader;

    private VolleyImageHelper(Context context){
        this.context=context;
        getRequestQueue();
        imageLoader = new ImageLoader(requestQueueImage,new ImageLoader.ImageCache(){

            private final LruCache<String, Bitmap>
                    cache = new LruCache<String, Bitmap>(20);
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    public static synchronized VolleyImageHelper  getInstance(Context context){
        if(imageHelper ==null){
            imageHelper = new VolleyImageHelper(context);
        }
        return  imageHelper;
    }

    public RequestQueue getRequestQueue(){
        try{
            if (requestQueueImage== null){
                Cache cache =new DiskBasedCache(context.getCacheDir(),10*1024*1024);
                //This is using Http =Basic Network
                Network network = new BasicNetwork(new HurlStack());
                requestQueueImage = new RequestQueue(cache,network);
                requestQueueImage.start();
            }
        }catch (Exception ex){
            Log.i(getClass().getSimpleName(),ex.getMessage());
        }

        return requestQueueImage;
    }

    public ImageLoader getImageLoader(){
        return imageLoader;
    }



}
