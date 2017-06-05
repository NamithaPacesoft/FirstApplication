package com.example.user.myapplication.asynctask;

/**
 * Created by User on 2017-06-01.
 */

public interface SampleAsycTaskListener {
    void startingExecution();
    void showProgress(String status);
    void finishExecution(Object result);

}
