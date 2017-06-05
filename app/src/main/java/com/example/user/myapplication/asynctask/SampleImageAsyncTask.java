package com.example.user.myapplication.asynctask;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by User on 2017-06-01.
 */

public class SampleImageAsyncTask extends AsyncTask<String,Void,Bitmap> {

    SampleAsycTaskListener myListener;
    public SampleImageAsyncTask(SampleAsycTaskListener objListener){

        myListener=objListener;
    }

    @Override
    protected Bitmap doInBackground(String... params) {

        String url = params[0];

        Bitmap bitmap =null;

        try{
            URL link=new URL(url);
            HttpURLConnection con = (HttpURLConnection)link.openConnection();
            InputStream is = con.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
        }catch (Exception ex){
            Log.e(getClass().getSimpleName(),"Exception thrown " + ex.getMessage());
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {

        myListener.finishExecution(bitmap);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        myListener.startingExecution();

    }
}
