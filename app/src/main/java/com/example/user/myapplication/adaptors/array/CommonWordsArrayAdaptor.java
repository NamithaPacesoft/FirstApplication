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
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.data.CommonWords;
import com.example.user.myapplication.data.ContactDetails;
import com.example.user.myapplication.sqllite.WordsDataSource;
import com.facebook.login.widget.LoginButton;

import java.util.List;

/**
 * Created by User on 2017-06-12.
 */

public class CommonWordsArrayAdaptor extends ArrayAdapter<CommonWords> implements View.OnClickListener {
    private final LayoutInflater layoutInflater;
    private List<CommonWords> lstDetails;
    private Context context;
    private WordsDataSource dataSource;



    public CommonWordsArrayAdaptor(Context context, List<CommonWords> lstDetails,WordsDataSource source){
        super(context, R.layout.layout_simple_items,lstDetails);
        this.lstDetails=lstDetails;
        dataSource = source;
        layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public void addDetails(CommonWords word){
        lstDetails.add(word);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if (convertView ==null){
            convertView = layoutInflater.inflate(R.layout.layout_simple_items,parent,false);
        }
       // convertView.setTag(position);
        //AddChldView for the linear layout. Convert
        initializeMyComponents(convertView,position);
        //addOnClickListener(position);




        return convertView;
    }

    private void initializeMyComponents(@Nullable View convertView, final int position){
        CommonWords word = lstDetails.get(position);

         final RelativeLayout rlEditMode;
         final RelativeLayout rlReadMode;
         final EditText etWord;
         Button btnCancel;
         Button btnOk;
         Button btnDelete;
         Button btnEdit;
         TextView tvDescription;


        rlEditMode = (RelativeLayout) convertView.findViewById(R.id.rl_edit_container);
        rlReadMode = (RelativeLayout) convertView.findViewById(R.id.rl_view_container);

        rlEditMode.setVisibility(convertView.GONE);
        rlReadMode.setVisibility(convertView.VISIBLE);
        etWord =(EditText) convertView.findViewById(R.id.et_common_word);
        rlReadMode.setTag(etWord);

        tvDescription = (TextView) convertView.findViewById(R.id.tv_description);
        btnDelete = (Button) convertView.findViewById(R.id.btn_delete);
        btnEdit = (Button) convertView.findViewById(R.id.btn_edit);

        btnOk = (Button) convertView.findViewById(R.id.btn_ok);
        btnCancel = (Button) convertView.findViewById(R.id.btn_cancel);

      //  btnDelete.setOnClickListener(this);
       // btnEdit.setOnClickListener(this);
       // btnCancel.setOnClickListener(this);
      //  btnOk.setOnClickListener(this);

        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(dataSource!=null){
                    dataSource.deleteWord(lstDetails.get(position));
                }
                lstDetails.remove(position);
                CommonWordsArrayAdaptor.this.notifyDataSetChanged();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                etWord.setText( lstDetails.get(position).getDescription());
                rlEditMode.setVisibility(View.VISIBLE);
                rlReadMode.setVisibility(View.GONE);
            }
        });

        btnOk.setTag(position);
        btnCancel.setTag(position);
        btnDelete.setTag(position);
        btnEdit.setTag(position);

        tvDescription.setText(word.getDescription());

    }


    /*

    private void addOnClickListener(final int position){
        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(dataSource!=null){
                    dataSource.deleteWord(lstDetails.get(position));
                }
                lstDetails.remove(position);
                CommonWordsArrayAdaptor.this.notifyDataSetChanged();
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

            }
        });
    }

*/
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_edit:
                int  pos = Integer.parseInt(v.getTag().toString());
                Log.i("Relative layout Name", "onClick: " + v.getParent().getClass().getName());
               // Log.i("Find View By Id", (((View)  v.getParent()).findViewById(R.id.et_common_word)).getClass().getName() );
                ((EditText) ((View)  v.getParent()).getTag()).setText(lstDetails.get(pos).getDescription());
                //.setText();


                ((View)(v.getParent()).getParent()).findViewById(R.id.rl_view_container).setVisibility(View.GONE);
                ((View)(v.getParent()).getParent()).findViewById(R.id.rl_edit_container).setVisibility(View.VISIBLE);
                //rlEditMode.setVisibility(View.VISIBLE);
                break;

            case R.id.btn_delete:

                if(dataSource!=null){
                    dataSource.deleteWord(lstDetails.get(Integer.parseInt(v.getTag().toString())));
                }
                lstDetails.remove(Integer.parseInt(v.getTag().toString()));
                CommonWordsArrayAdaptor.this.notifyDataSetChanged();
                break;

            case  R.id.btn_cancel:

               // etWord.setText("");
              //  rlEditMode.setVisibility(View.GONE);
               // rlReadMode.setVisibility(View.VISIBLE);
                break;

            case R.id.btn_ok :


               // etWord.setText("");
               // rlEditMode.setVisibility(View.GONE);
               // rlReadMode.setVisibility(View.VISIBLE);
        }
    }
}
