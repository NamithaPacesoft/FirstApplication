package com.example.user.myapplication.adaptors.array;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.data.ContactDetails;

import java.util.List;

/**
 * Created by User on 2017-06-10.
 */

public class ContactListArrayAdaptor extends ArrayAdapter<ContactDetails> {

        private final LayoutInflater layoutInflater;
        private List<ContactDetails> lstContactDetails;


        public ContactListArrayAdaptor(Context context,List<ContactDetails> lstDetails){
            super(context, R.layout.layout_contacts_list_items,lstDetails);
            lstContactDetails=lstDetails;

            layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }


    TextView tvName;
    TextView tvPhone;
    TextView tvMobile;
    Button btnDelete;
    int count =0;

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        ContactDetails contact = lstContactDetails.get(position);

        Log.i(getClass().getSimpleName(), "count :" + (++ count));
        if (convertView ==null){
            convertView = layoutInflater.inflate(R.layout.layout_contacts_list_items,parent,false);
        }

        initializeMyComponents(convertView);
        addOnClickListener();


        tvName.setText(contact.getName());
        tvMobile.setText(contact.getMobileNumber());
        tvPhone.setText(contact.getPhoneNumber());

        return convertView;
    }

    private void initializeMyComponents( @Nullable View convertView){
        tvName = (TextView) convertView.findViewById(R.id.tv_contacts_name);
        tvPhone = (TextView) convertView.findViewById(R.id.tv_phone_number);
        tvMobile = (TextView) convertView.findViewById(R.id.tv_mobile_number);
        btnDelete = (Button) convertView.findViewById(R.id.btn_delete);
    }

    private  void addOnClickListener(){
        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                btnDelete.setText("Clicked");
            }
        });
    }
}
