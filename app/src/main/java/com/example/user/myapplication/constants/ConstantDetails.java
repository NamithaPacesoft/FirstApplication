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
    public static final int DIALOG_TIME=1;

    public static final String TAG_BUTTON_FRAGMENT="BUTTON_FRAGMENT";

    public static final int REQ_CODE_GOOGLE=9001;
    public static final int REQ_CODE_GOOGLE_MAPS_PERMISSIONS=100;


    public static final int SUCCESS_RESULT = 0;
    public static final int FAILURE_RESULT = 1;
    public static final String PACKAGE_NAME =
            "com.google.android.gms.location.sample.locationaddress";
    public static final String RECEIVER = PACKAGE_NAME + ".RECEIVER";
    public static final String RESULT_DATA_KEY = PACKAGE_NAME +
            ".RESULT_DATA_KEY";
    public static final String LOCATION_DATA_EXTRA = PACKAGE_NAME +
            ".LOCATION_DATA_EXTRA";
}
