package com.example.user.myapplication.customcomponents.listeners;

import android.widget.CompoundButton;

/**
 * Created by User on 2017-06-22.
 */

public interface OnCheckUncheckListner  {

    public void checked(int currentRating);
    public void unchecked(int currentRating);
    public void onRatingChanged(int rating);
}
