package com.example.user.myapplication.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.user.myapplication.data.CommonWords;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2017-07-06.
 */

public class WordsDataSource {
    // Database fields
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;

    private String[] allColumns = { DatabaseHelper.KEY_ID,
            DatabaseHelper.KEY_DESC};


   public WordsDataSource(Context context){
       dbHelper = new DatabaseHelper(context);
   }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }


    public CommonWords createComment(String word) {

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_DESC, word);
        long insertId = database.insert(DatabaseHelper.TABLE_COMMON_WORDS, null,
                values);
        Cursor cursor = database.query(DatabaseHelper.TABLE_COMMON_WORDS,
                allColumns, DatabaseHelper.KEY_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        CommonWords newWord = cursorToComment(cursor);
        cursor.close();
        return newWord;
    }

    private CommonWords cursorToComment(Cursor cursor) {
        CommonWords word = new CommonWords();
        word.setId(cursor.getInt(0));
        word.setDescription(cursor.getString(1));
        return word;
    }

    public List<CommonWords> getAllWords() {
        List<CommonWords> words = new ArrayList<CommonWords>();

        Cursor cursor = database.query(DatabaseHelper.TABLE_COMMON_WORDS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CommonWords comment = cursorToComment(cursor);
            words.add(comment);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return words;
    }

    public void deleteWord(CommonWords words) {
        long id = words.getId();
        Log.i(getClass().getSimpleName(),"Comment deleted with id: " + id);
        database.delete(DatabaseHelper.TABLE_COMMON_WORDS, DatabaseHelper.KEY_ID
                + " = " + id, null);
    }

}
