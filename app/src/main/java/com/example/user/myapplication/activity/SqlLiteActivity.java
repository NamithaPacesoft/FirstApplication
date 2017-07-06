package com.example.user.myapplication.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.adaptors.array.CommonWordsArrayAdaptor;
import com.example.user.myapplication.data.CommonWords;
import com.example.user.myapplication.sqllite.DatabaseHelper;
import com.example.user.myapplication.sqllite.WordsDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2017-07-06.
 */

public class SqlLiteActivity extends AppCompatActivity {

    ListView lstView;
    EditText etCommonWords;
    Button btnAdd;
    List<CommonWords> lstCommonWords ;
    WordsDataSource dataSource;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_simple_listview);

        initializeMyComponents();
        addActionListener();
    }

    public void initializeMyComponents(){
        lstView= (ListView)findViewById(R.id.lv_simple);
        etCommonWords=(EditText) findViewById(R.id.et_word);
        btnAdd = (Button) findViewById(R.id.btn_add);
        lstCommonWords= new ArrayList<CommonWords>();

        dataSource =new WordsDataSource(this);
        dataSource.open();

        getDataFromDatabase();
        CommonWordsArrayAdaptor wordsAdaptor=new CommonWordsArrayAdaptor(this,lstCommonWords,dataSource);
        lstView.setAdapter(wordsAdaptor);
        wordsAdaptor.notifyDataSetChanged();
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

    public void addValues( ){
        CommonWordsArrayAdaptor wordsAdaptor = (CommonWordsArrayAdaptor) lstView.getAdapter();
        CommonWords words= dataSource.createComment(etCommonWords.getText().toString());
        wordsAdaptor.add(words);
        wordsAdaptor.notifyDataSetChanged();

    }

    public void getDataFromDatabase(){
        lstCommonWords = dataSource.getAllWords();
    }

    @Override
    protected void onResume() {
        dataSource.open();
        super.onResume();
    }


    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }
}
