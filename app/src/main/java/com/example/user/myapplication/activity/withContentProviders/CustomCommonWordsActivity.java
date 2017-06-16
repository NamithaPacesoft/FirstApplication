package com.example.user.myapplication.activity.withContentProviders;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.adaptors.array.CommonWordsArrayAdaptor;
import com.example.user.myapplication.contentprovider.CommonWordsContentProvider;
import com.example.user.myapplication.data.CommonWords;
import com.example.user.myapplication.data.ContactDetails;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2017-06-12.
 */

public class CustomCommonWordsActivity extends AppCompatActivity {

    ListView lstView;
    EditText etCommonWords;
    Button btnAdd;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_simple_listview);

        initializeMyComponents();
        addActionListener();

        fillCommonWords();
    }

    public void initializeMyComponents(){
        lstView= (ListView)findViewById(R.id.lv_simple);
        etCommonWords=(EditText) findViewById(R.id.et_word);
        btnAdd = (Button) findViewById(R.id.btn_add);

    }

    public void addActionListener(){
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                if(etCommonWords.getText().equals(etCommonWords.getHint()) || (etCommonWords.getText().length()==0)){

                }else{
                    addValues();

                }


            }
        });
    }

    public void addValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CommonWordsContentProvider.COLUMN_DESC,etCommonWords.getText().toString());

        Uri uri=getContentResolver().insert(CommonWordsContentProvider.CONTENT_URI, contentValues);
        getWords(uri,lstCommonWords);

        CommonWordsArrayAdaptor wordsAdaptor=new CommonWordsArrayAdaptor(this,lstCommonWords);

        lstView.setAdapter(wordsAdaptor);
        wordsAdaptor.notifyDataSetChanged();
    }

    ArrayList<CommonWords> lstCommonWords ;
    public void fillCommonWords(){

        lstCommonWords = new ArrayList<CommonWords>();
        getWords(lstCommonWords);
        CommonWordsArrayAdaptor wordsAdaptor=new CommonWordsArrayAdaptor(this,lstCommonWords);

        lstView.setAdapter(wordsAdaptor);
        wordsAdaptor.notifyDataSetChanged();
    }


    private void getWords(ArrayList<CommonWords> lstWords){

        CommonWords commonWords= new CommonWords() ;
        Cursor cursorWords=null;

        ContentResolver contentResolver=getContentResolver();

        String strID;

        try{
            cursorWords = contentResolver.query(CommonWordsContentProvider.CONTENT_URI,null,null,null,null);


            if (cursorWords.getCount()>0){

                while (cursorWords.moveToNext()){
                    commonWords = new CommonWords();

                    strID = cursorWords.getString(cursorWords.getColumnIndex(CommonWordsContentProvider.COLUMN_ID));
                    commonWords.setDescription(cursorWords.getString(cursorWords.getColumnIndex(CommonWordsContentProvider.COLUMN_DESC)));
                    commonWords.setId(Integer.parseInt(strID));

                    lstWords.add(commonWords);
                }

            }
        }catch (Exception ex){
            Log.e(getClass().getSimpleName(),"Exception thrown while retrieving the detials : " + ex.getMessage());
        }finally {
            if (cursorWords!=null){
                cursorWords.close();
            }

        }


    }

    private void getWords(Uri uri,ArrayList<CommonWords> lstWords){

        CommonWords commonWords= new CommonWords() ;
        Cursor cursorWords=null;

        ContentResolver contentResolver=getContentResolver();

        String strID;

        try{
            cursorWords = contentResolver.query(uri,null,null,null,null);


            if (cursorWords.getCount()>0){

                while (cursorWords.moveToNext()){
                    commonWords = new CommonWords();

                    strID = cursorWords.getString(cursorWords.getColumnIndex(CommonWordsContentProvider.COLUMN_ID));
                    commonWords.setDescription(cursorWords.getString(cursorWords.getColumnIndex(CommonWordsContentProvider.COLUMN_DESC)));
                    commonWords.setId(Integer.parseInt(strID));

                    lstWords.add(commonWords);
                }

            }
        }catch (Exception ex){
            Log.e(getClass().getSimpleName(),"Exception thrown while retrieving the detials : " + ex.getMessage());
        }finally {
            if (cursorWords!=null){
                cursorWords.close();
            }

        }


    }



}
