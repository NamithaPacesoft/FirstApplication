package com.example.user.myapplication.exampleprojects.locationbased;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.constants.ConstantDetails;
import com.example.user.myapplication.services.startedservice.intentservice.googlemaps.FetchAddressIntentService;


/**
 * Created by User on 2017-07-04.
 */

public class UserLocationActivity extends AppCompatActivity {

    LocationManager locationManager;
    TextView txtLat;
    TextView txtLog;
    TextView txt_address;
    protected Location mLastLocation;
    private AddressResultReceiver mResultReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_maps_location_lat_long);

        inititalizeMyComponents();

    }

    private void inititalizeMyComponents() {
        txtLat = (TextView) findViewById(R.id.txt_lat);
        txtLog = (TextView) findViewById(R.id.txt_long);
        txt_address = (TextView) findViewById(R.id.txt_address);
        initializeLocationManager();
        mResultReceiver = new AddressResultReceiver(new Handler());
    }

    LocationListener locationListener;
    private void initializeLocationManager() {
        // Acquire a reference to the system Location Manager
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                txtLat.setText( Double.toString(location.getLatitude()) );
                txtLog.setText( Double.toString(location.getLongitude()) );
                mLastLocation=location;
                startIntentService();
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }

        };

        requestLocationPermissions();
    }

    protected void startIntentService() {
        Intent intent = new Intent(this, FetchAddressIntentService.class);
        intent.putExtra(ConstantDetails.RECEIVER, mResultReceiver);
        intent.putExtra(ConstantDetails.LOCATION_DATA_EXTRA, mLastLocation);
        startService(intent);
    }


    @TargetApi(23)
    private  void requestLocationPermissions(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            // Register the listener with the Location Manager to receive location updates

            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION}, ConstantDetails.REQ_CODE_GOOGLE_MAPS_PERMISSIONS);
        }else{
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if(requestCode == ConstantDetails.REQ_CODE_GOOGLE_MAPS_PERMISSIONS){

            for (int i=0;i<grantResults.length;i++){
                if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                    requestLocationPermissions();
                }
            }
        }
    }


   public class AddressResultReceiver extends ResultReceiver {
        public AddressResultReceiver(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {

            // Show a toast message if an address was found.
            if (resultCode == ConstantDetails.SUCCESS_RESULT) {
                // Display the address string
                // or an error message sent from the intent service.
                String mAddressOutput = resultData.getString(ConstantDetails.RESULT_DATA_KEY);
                txt_address.setText(mAddressOutput);
            }

        }
    }

}
