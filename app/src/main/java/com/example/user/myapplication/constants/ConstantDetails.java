package com.example.user.myapplication.constants;

/**
 * Created by User on 2017-06-04.
 */

public final  class ConstantDetails {

    //A final class cannot be extended, it can extend other classes/
    //A private constructor stops the instantiation. So, an object cannot be created from this Class.
    //Eg: for final class is java.lang.Math
    private ConstantDetails(){

    }

    public static final String TAG_URL ="URL";
    public static final String TAG_IMAGE="IMAGE";
    public static final int  MSG_TYPE_DOWNLOAD_IMAGE=1;
    public static final String TAG_SERVICE_LOGGER="Calling from Service";
    public static final String TAG_CLASS_LOGGER="Calling from Class";
    public static final String TAG_MESSAGE="Message";

    public static final String TAG_BUTTON_FRAGMENT="BUTTON_FRAGMENT";


}
