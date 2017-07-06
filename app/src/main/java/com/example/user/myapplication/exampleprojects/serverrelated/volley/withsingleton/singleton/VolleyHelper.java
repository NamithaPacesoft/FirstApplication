package com.example.user.myapplication.exampleprojects.serverrelated.volley.withsingleton.singleton;

import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.util.LruCache;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.user.myapplication.data.Patient;
import com.example.user.myapplication.exampleprojects.serverrelated.volley.withouthelper.VolleyStringRequest;
import com.example.user.myapplication.exampleprojects.serverrelated.volley.withsingleton.constants.NetworkConstants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.util.Map;

/**
 * Created by User on 2017-06-27.
 */

public class VolleyHelper {
    /**
     * The default number of retries
     */
    private static final int DEFAULT_MAX_RETRIES_POST = 0;
    /**
     * The default number of retries
     */
    private static final int DEFAULT_MAX_RETRIES_GET = 3;

    public static final int DEFAULT_TIMEOUT_MS = 30000;

    private static VolleyHelper mInstance;
    private final ImageLoader imageLoader;
    RequestQueue requestQueue;

    private VolleyHelper(Context context){
        requestQueue = getRequestQueue(context);

        imageLoader = new ImageLoader(requestQueue,new ImageLoader.ImageCache(){

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

    public ImageLoader getImageLoader(){
        return  imageLoader;
    }

    private RequestQueue getRequestQueue(Context context) {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized VolleyHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleyHelper(context);
        }
        return  mInstance;
    }

    public void sayHelloToTheServer(String name,APIObserver apiObserver){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        unauthenticatedUrlTask(NetworkConstants.HELLO_SERVER_URL, Request.Method.GET,NetworkConstants.HELLO_SERVER_API_ID,contentValues,apiObserver);
    }


    public void uploadPatient(Patient patient, APIObserver apiObserver){

        try{
            JSONObject patientJsonObject = new JSONObject();
            patientJsonObject.put("patientId",patient.getPatientId());
            patientJsonObject.put("registeredNumber",patient.getRegisteredNumber());
            patientJsonObject.put("name",patient.getName());
            patientJsonObject.put("phoneNumber",patient.getPhoneNumber());
            patientJsonObject.put("email",patient.getEmail());
            patientJsonObject.put("profilePictureUrl",patient.getProfilePictureUrl());
            patientJsonObject.put("thumbnailUrl",patient.getThumbnailUrl());
            patientJsonObject.put("active",patient.isActive());


            unauthenticatedTask(NetworkConstants.UPLOAD_PATIENT_URL,Request.Method.POST,NetworkConstants.UPLOAD_PATIENT_API_ID,patientJsonObject,apiObserver);
        }catch (JSONException e){

        }


    }

    //Common Methods
    private void unauthenticatedTask(String apiUrl, int requestMethod, int apiIndex, JSONObject jsonObject, APIObserver observer) {
        String url = NetworkConstants.BASE_URL + apiUrl;
        VolleyStringRequest volleyStringRequest = new VolleyStringRequest(requestMethod, url, jsonObject, new VolleyNetworkResponseListener(observer, apiIndex), new VolleyErrorListener(observer, apiIndex)) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
        };
        volleyStringRequest.setRetryPolicy(new DefaultRetryPolicy(DEFAULT_TIMEOUT_MS, getMaximumRetries(requestMethod), DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(volleyStringRequest);
    }



    private void authenticatedTask(String apiUrl, int requestMethod, int apiIndex, final Map<String, String> header, JSONObject jsonObject, APIObserver observer) {
        String url = NetworkConstants.BASE_URL + apiUrl;

        VolleyStringRequest volleyStringRequest = new VolleyStringRequest(requestMethod, url, jsonObject, new VolleyNetworkResponseListener(observer, apiIndex), new VolleyErrorListener(observer, apiIndex)) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return header;
            }
        };
        volleyStringRequest.setRetryPolicy(new DefaultRetryPolicy(DEFAULT_TIMEOUT_MS, getMaximumRetries(requestMethod), DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(volleyStringRequest);
    }

    private void authenticatedUrlTask(String apiUrl, int requestMethod, int apiIndex, final Map<String, String> header, ContentValues values, APIObserver observer) {
        String url = NetworkConstants.BASE_URL + apiUrl;

        Uri.Builder builder = new Uri.Builder();

        for (String key: values.keySet()) {
            builder.appendQueryParameter(key,values.getAsString(key));
        }

        url = url+builder.build().toString();

        VolleyStringRequest volleyStringRequest = new VolleyStringRequest(requestMethod, url, new VolleyNetworkResponseListener(observer, apiIndex), new VolleyErrorListener(observer, apiIndex), header) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return header;
            }
        };
        volleyStringRequest.setRetryPolicy(new DefaultRetryPolicy(DEFAULT_TIMEOUT_MS, getMaximumRetries(requestMethod), DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(volleyStringRequest);
    }

    private void unauthenticatedUrlTask(String apiUrl, int requestMethod, int apiIndex, ContentValues values, APIObserver observer) {
        String url = NetworkConstants.BASE_URL + apiUrl;

        Uri.Builder builder = new Uri.Builder();

        for (String key: values.keySet()) {
            builder.appendQueryParameter(key,values.getAsString(key));
        }

        url = url+builder.build().toString();

        VolleyStringRequest volleyStringRequest = new VolleyStringRequest(requestMethod, url, new VolleyNetworkResponseListener(observer, apiIndex), new VolleyErrorListener(observer, apiIndex), null);
        volleyStringRequest.setRetryPolicy(new DefaultRetryPolicy(DEFAULT_TIMEOUT_MS, getMaximumRetries(requestMethod), DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        requestQueue.add(volleyStringRequest);
    }


    private int getMaximumRetries(int RequestMethod) {
        int MAX_RETRIES = 0;
        switch (RequestMethod) {
            case Request.Method.POST: {
                MAX_RETRIES = DEFAULT_MAX_RETRIES_POST;
            }
            break;
            case Request.Method.GET: {
                MAX_RETRIES = DEFAULT_MAX_RETRIES_GET;
            }
            break;
        }
        return MAX_RETRIES;
    }



    public interface APIObserver {
        public void onAPIResponse(boolean success,String response,int responseCode,int apiIndex);
    }

    private static class VolleyNetworkResponseListener implements Response.Listener<NetworkResponse> {

        private int apiIndex = -1;
        private WeakReference<APIObserver> weakReference = null;

        public VolleyNetworkResponseListener(APIObserver apiObserver, int apiIndex) {
            weakReference = new WeakReference<>(apiObserver);
            this.apiIndex = apiIndex;
        }

        @Override
        public void onResponse(NetworkResponse response) {
            if (weakReference.get() != null) {

                String parsed;
                int responseCode = 0;
                try {
                    parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                    responseCode = response.statusCode;
                } catch (UnsupportedEncodingException e) {
                    parsed = new String(response.data);
                }
                weakReference.get().onAPIResponse(true, parsed, responseCode, apiIndex);
            }
        }
    }


    private static class VolleyErrorListener implements Response.ErrorListener {

        private int apiIndex = -1;
        private WeakReference<APIObserver> weakReference = null;

        public VolleyErrorListener(APIObserver apiObserver, int apiIndex) {
            weakReference = new WeakReference<>(apiObserver);
            this.apiIndex = apiIndex;
        }

        @Override
        public void onErrorResponse(VolleyError volleyError) {
            if (weakReference.get() != null) {
                int statusCode =  0;
                if(volleyError.networkResponse != null){
                    statusCode = volleyError.networkResponse.statusCode;
                }
                weakReference.get().onAPIResponse(false, volleyError.getMessage(), statusCode, apiIndex);
            }
        }
    }
}
