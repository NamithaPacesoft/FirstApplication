package com.example.user.myapplication.exampleprojects.serverrelated.volley.withouthelper;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.user.myapplication.R;

import org.json.JSONObject;

/**
 * Created by User on 2017-06-27.
 */

public class ServerRequestExampleActivity extends AppCompatActivity implements Response.Listener<String>, Response.ErrorListener{

    EditText nameEditText;
    Button helloButton;
    TextView responseTextView;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_httprequest_activity);

        nameEditText = (EditText) findViewById(R.id.et_name);
        helloButton = (Button) findViewById(R.id.bt_hello);
        responseTextView = (TextView) findViewById(R.id.tv_server_response);

        helloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sayHelloToTheServer();
            }
        });
        requestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    private void sayHelloToTheServer() {
        StringRequest stringRequest = new StringRequest("https://www.yengram.com/example/sayhello?name="+nameEditText.getText().toString(),this,this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onResponse(String response) {
        try{
            String displayString = "";
            JSONObject jsonObject = new JSONObject(response);

            displayString += "Has Error? : "+jsonObject.getBoolean("hasError")+" \n";
            displayString += "Server Message : "+jsonObject.getString("message")+" \n";

            JSONObject model =  jsonObject.getJSONObject("model");
            displayString += "Message : " + model.getString("message")+" \n";

            nameEditText.setText(displayString);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        responseTextView.setText(error.getLocalizedMessage());
    }
}
