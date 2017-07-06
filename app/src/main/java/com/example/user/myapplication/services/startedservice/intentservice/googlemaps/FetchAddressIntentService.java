package com.example.user.myapplication.services.startedservice.intentservice.googlemaps;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import com.example.user.myapplication.constants.ConstantDetails;
import com.example.user.myapplication.exampleprojects.locationbased.UserLocationActivity.AddressResultReceiver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by User on 2017-07-04.
 */

public class FetchAddressIntentService extends IntentService {


    private static final String TAG ="Fetch Address" ;
    Context context;
    private ResultReceiver receiver;

    public FetchAddressIntentService(){
        super("FetchAddressIntentService");
    }

    public FetchAddressIntentService(Context context){
        super("FetchAddressIntentService");
        this.context=context;
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String errorMessage = "";
        Geocoder geocoder = new Geocoder(this.getApplicationContext(), Locale.getDefault());
        Location location;



        location = intent.getParcelableExtra(
                ConstantDetails.LOCATION_DATA_EXTRA);

        List<Address> addresses = null;

        try {
            receiver = intent.getParcelableExtra(ConstantDetails.RECEIVER);
            // Get the location passed to this service through an extra.


            addresses = geocoder.getFromLocation(
                    location.getLatitude(),
                    location.getLongitude(),
                    // In this sample, get just a single address.
                    1);
        } catch (IOException ioException) {
            // Catch network or other I/O problems.
            errorMessage = ("Service not available");
            Log.e(TAG, errorMessage, ioException);
        } catch (IllegalArgumentException illegalArgumentException) {
            // Catch invalid latitude or longitude values.
            errorMessage = "invalid latitude and longitude";
            Log.e(TAG, errorMessage + ". " +
                    "Latitude = " + location.getLatitude() +
                    ", Longitude = " +
                    location.getLongitude(), illegalArgumentException);
        }catch(Exception ex){
            Log.e(TAG, errorMessage + ". " +
                    "Latitude = " + location.getLatitude() +
                    ", Longitude = " +
                    location.getLongitude(), ex);
        }

        // Handle case where no address was found.
        if (addresses == null || addresses.size()  == 0) {
            if (errorMessage.isEmpty()) {
                errorMessage ="No address found";
                Log.e(TAG, errorMessage);
            }
            deliverResultToReceiver(ConstantDetails.FAILURE_RESULT, errorMessage);
        } else {
            Address address = addresses.get(0);
            ArrayList<String> addressFragments = new ArrayList<String>();

            // Fetch the address lines using getAddressLine,
            // join them, and send them to the thread.
            for(int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                addressFragments.add(address.getAddressLine(i));
            }
            Log.i(TAG, "Address found");
            deliverResultToReceiver(ConstantDetails.SUCCESS_RESULT,
                    TextUtils.join(System.getProperty("line.separator"),
                            addressFragments));
        }
    }


    private void deliverResultToReceiver(int resultCode, String message) {
        Bundle bundle = new Bundle();
        bundle.putString(ConstantDetails.RESULT_DATA_KEY, message);
        receiver.send(resultCode, bundle);
    }
}
