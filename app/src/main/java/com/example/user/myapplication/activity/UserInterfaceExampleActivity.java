package com.example.user.myapplication.activity;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.user.myapplication.R;
import com.example.user.myapplication.adaptors.array.MessageStatusArrayAdaptor;
import com.example.user.myapplication.constants.ConstantDetails;
import com.example.user.myapplication.data.MessageStatus;
import com.example.user.myapplication.fragments.fixed.TimePickerFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by User on 2017-06-18.
 */

public class UserInterfaceExampleActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spState;
    ArrayAdapter<CharSequence> adaptorSpinner;
    ListView lvMessages;
    ArrayList<MessageStatus> lstStatus = new ArrayList<MessageStatus>();
    MessageStatusArrayAdaptor adaptorMessageStatus;
    Button btnTime;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_uer_interface_example);

        initializeDataValues();
        initializeMyComponents();

        addActionListener();

        setAdaptors();
    }

    private  void initializeMyComponents(){
        spState =(Spinner) findViewById(R.id.sp_filter);
        spState.setOnItemSelectedListener(this);
        lvMessages=(ListView) findViewById(R.id.lv_messages);
        btnTime =(Button) findViewById(R.id.btn_pick_time);

    }

    private void addActionListener(){
        btnTime.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
             //showDialog(ConstantDetails.DIALOG_TIME);
                setTime();

            }
        });
    }

    int hour_x;
    int min_x;

    private void setTime(){
        new TimePickerDialog(this,R.style.MyDialog,timePickerListener, Calendar.HOUR_OF_DAY,Calendar.MINUTE,false).show();
    }


    protected TimePickerDialog.OnTimeSetListener timePickerListener= new TimePickerDialog.OnTimeSetListener(){
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            hour_x=hourOfDay;
            min_x=minute;
            btnTime.setText(hour_x +":" + min_x  );
        }


    };

    /*
    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == ConstantDetails.DIALOG_TIME){
            return new TimePickerDialog(this,timePickerListener, Calendar.HOUR_OF_DAY,Calendar.MINUTE,false);
        }else{
           super.onCreateDialog(id);
        }

        return  null;
    }
*/




    private  void setAdaptors(){
        //setting spinner adaptor
        adaptorSpinner = ArrayAdapter.createFromResource(this,R.array.array_message_status,R.layout.simple_spinner_item);
        adaptorSpinner.setDropDownViewResource(R.layout.simple_spinner_layout_item);

        spState.setAdapter(adaptorSpinner);


        //setting listview adaptors
        adaptorMessageStatus =new MessageStatusArrayAdaptor(this,lstStatus);

        lvMessages.setAdapter(adaptorMessageStatus);
        adaptorMessageStatus.notifyDataSetChanged();

    }

    private void initializeDataValues(){
       lstStatus.add(new MessageStatus("Todays Special",true));
        lstStatus.add(new MessageStatus("New arrivals",false));
        lstStatus.add(new MessageStatus("Read the latest news ",true));
        lstStatus.add(new MessageStatus("Market status",false));
        lstStatus.add(new MessageStatus("Plants and Trees",true));
        lstStatus.add(new MessageStatus("Sports",false));

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        List<MessageStatus> lstFilteredStatus =new ArrayList<MessageStatus>();
        if (spState.getSelectedItemId()==1) {
            for (int i=0;i<lstStatus.size()-1;i++){
                if (lstStatus.get(i).isRead()){
                    lstFilteredStatus.add(lstStatus.get(i));
                }
            }
        }else if(spState.getSelectedItemId()==2){
            for (int i=0;i<lstStatus.size()-1;i++){
                if (!lstStatus.get(i).isRead()){
                    lstFilteredStatus.add(lstStatus.get(i));
                }
            }
        }else{
            lstFilteredStatus.addAll(lstStatus);
        }



        adaptorMessageStatus =new MessageStatusArrayAdaptor(this,lstFilteredStatus);

        lvMessages.setAdapter(adaptorMessageStatus);
        adaptorMessageStatus.notifyDataSetChanged();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
