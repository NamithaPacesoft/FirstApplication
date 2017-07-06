package com.example.user.myapplication.exampleprojects.serverrelated.volley.withouthelper;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by User on 2017-06-27.
 */

public class VolleyStringRequest extends Request<NetworkResponse> {

    private static final String PROTOCOL_CHARSET = "utf-8";
    private final Response.Listener<NetworkResponse> listener;
    private final String mRequestBody;
    private Map<String,String> header;


    public VolleyStringRequest(int method, String url, JSONObject jsonRequest, Response.Listener<NetworkResponse> listener,
                               Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listener = listener;
        mRequestBody = (jsonRequest == null) ? null : jsonRequest.toString();
    }

    public VolleyStringRequest(int method, String url, JSONArray jsonRequest, Response.Listener<NetworkResponse> listener,
                               Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listener = listener;
        mRequestBody = (jsonRequest == null) ? null : jsonRequest.toString();
    }

    public VolleyStringRequest(int method, String url, Response.Listener<NetworkResponse> listener,
                               Response.ErrorListener errorListener, Map<String, String> header) {
        super(method, url, errorListener);
        this.listener = listener;
        this.mRequestBody = null;
        this.header = header;
    }

    @Override
    protected void deliverResponse(NetworkResponse response) {
        this.listener.onResponse(response);
    }

    @Override
    protected Response<NetworkResponse> parseNetworkResponse(NetworkResponse response) {
        return Response.success(response, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    public byte[] getBody() {
        try {
            return mRequestBody == null ? null : mRequestBody.getBytes(PROTOCOL_CHARSET);
        } catch (UnsupportedEncodingException uee) {
            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s",
                    mRequestBody, PROTOCOL_CHARSET);
            return null;
        }
    }

}
