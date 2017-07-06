package com.example.user.myapplication.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.myapplication.data.CommonWords;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2017-07-06.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    // Database Version
    public static final int DATABASE_VERSION = 1;
    // Database Name
    public static final String DATABASE_NAME = "TutorialDatabase";

    //Table name
    public static final String TABLE_COMMON_WORDS ="CommonWords";
    //Column names
    public static final String KEY_ID = "wordsId";
    public static final String KEY_DESC = "commonWord";

    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery ="CREATE TABLE " + TABLE_COMMON_WORDS + "("
                + KEY_ID +" INTEGER PRIMARY KEY autoincrement,"
                + KEY_DESC + " TEXT)";
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COMMON_WORDS);
        // Creating tables again
        onCreate(db);
    }

}

