package com.example.user.myapplication.adaptors.array;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.data.CommonWords;
import com.example.user.myapplication.data.ContactDetails;

import java.util.List;

/**
 * Created by User on 2017-06-12.
 */

public class CommonWordsArrayAdaptor extends ArrayAdapter<CommonWords> {
    private final LayoutInflater layoutInflater;
    private List<CommonWords> lstDetails;
    private Context context;

    public CommonWordsArrayAdaptor(Context context, List<CommonWords> lstDetails){
        super(context, R.layout.layout_simple_items,lstDetails);
        this.lstDetails=lstDetails;

        layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private Button btnDelete;
    private Button btnEdit;
    private TextView tvDescription;

    public void addDetails(CommonWords word){
        lstDetails.add(word);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        CommonWords word = lstDetails.get(position);

        if (convertView ==null){
            convertView = layoutInflater.inflate(R.layout.layout_simple_items,parent,false);
        }

        //AddChildView for the linear layout. Convert
        initializeMyComponents(convertView);
        addOnClickListener();


        tvDescription.setText(word.getDescription());



        return convertView;
    }

    private void initializeMyComponents( @Nullable View convertView){
        tvDescription = (TextView) convertView.findViewById(R.id.tv_description);
        btnDelete = (Button) convertView.findViewById(R.id.btn_delete);
        btnEdit = (Button) convertView.findViewById(R.id.btn_edit);

    }


    private void addOnClickListener(){
        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                btnDelete.setText("Clicked");


            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                btnEdit.setText("Clicked");
            }
        });
    }

}
