package com.example.user.myapplication.exampleprojects.serverrelated.volley.withsingleton.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.data.Patient;
import com.example.user.myapplication.exampleprojects.serverrelated.volley.withsingleton.constants.NetworkConstants;
import com.example.user.myapplication.exampleprojects.serverrelated.volley.withsingleton.singleton.VolleyHelper;

import org.json.JSONObject;

/**
 * Created by User on 2017-06-28.
 */

public class ServerRequestExampleActivity extends AppCompatActivity implements VolleyHelper.APIObserver {
    EditText nameEditText;
    Button helloButton;
    TextView responseTextView;



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
                uploadPatient();
            }
        });
    }

    private void sayHelloToTheServer() {
        VolleyHelper.getInstance(getApplicationContext()).sayHelloToTheServer(nameEditText.getText().toString(),this);
    }

    private void uploadPatient(){
        Patient patient =  new Patient();
        patient.setPatientId(1);
        patient.setName("Namitha");
        patient.setPhoneNumber("12345678890");
        patient.setEmail("hello@email.com");
        patient.setRegisteredNumber("Reg1234");
        patient.setProfilePictureUrl("some url");
        patient.setThumbnailUrl("some url");
        patient.setActive(true);

        VolleyHelper.getInstance(getApplicationContext()).uploadPatient(patient,this);
    }

    @Override
    public void onAPIResponse(boolean success, String response, int responseCode, int apiIndex) {
        switch (apiIndex) {
            case NetworkConstants.HELLO_SERVER_API_ID: {
                try {
                    if(success){
                        JSONObject jsonObject = new JSONObject(response);
                        boolean error = jsonObject.optBoolean("hasError");
                        if (!error) {
                            String displayString = "";

                            displayString += "Has Error? : "+jsonObject.getBoolean("hasError")+" \n";
                            displayString += "Server Message : "+jsonObject.getString("message")+" \n";

                            JSONObject model =  jsonObject.getJSONObject("model");
                            displayString += "Message : " + model.getString("message")+" \n";

                            responseTextView.setText(displayString);
                        } else {
                            // progressBar.setVisibility(View.INVISIBLE);
                        }
                    }else{

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            break;
            case NetworkConstants.UPLOAD_PATIENT_API_ID: {
                try {
                    if(success){
                        JSONObject jsonObject = new JSONObject(response);
                        boolean error = jsonObject.optBoolean("hasError");
                        if (!error) {
                            JSONObject model = jsonObject.getJSONObject("model");

                            if(model != null){
                                Patient patient = new Patient(model.getJSONObject("patient"));

                                String displayString = "";
                                displayString += "Patient Name : "+patient.getName()+" \n";
                                displayString += "Patient Phone : "+patient.getPhoneNumber()+" \n";

                                responseTextView.setText(displayString);
                            }

                        } else {
                            // progressBar.setVisibility(View.INVISIBLE);
                        }
                    }else{
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            break;
        }

    }
}
