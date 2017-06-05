package com.example.user.myapplication.asynctask;

import android.os.AsyncTask;

/**
 * Created by User on 2017-06-01.
 */

public class SampleAsycTask extends AsyncTask<String,String,String> {


    SampleAsycTaskListener myListener;


    public SampleAsycTask(SampleAsycTaskListener objListener){
        myListener=objListener;
    }
    @Override
    protected String doInBackground(String... params) {
        Integer timeInSecs = Integer.parseInt(params[0]);
        long count = (timeInSecs*10000000L);

        for(long i = 0; i < count;i++){
        }
        return "";

    }

    @Override
    protected void onPreExecute() {

        myListener.startingExecution();
    }

    @Override
    protected void onPostExecute(String s) {


        myListener.finishExecution(s);
    }
}
