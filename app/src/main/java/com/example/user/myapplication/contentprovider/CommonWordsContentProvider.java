package com.example.user.myapplication.contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.MergeCursor;
import android.net.Uri;

import com.example.user.myapplication.data.CommonWords;


import java.util.ArrayList;

public class CommonWordsContentProvider extends ContentProvider {

    public static final String CONTENT ="content://";

    //needs to be unique; so using the package name of the content provider class is a good idea.
    public static final String AUTHORITY="com.example.user.myapplication.contentprovider";
    public static final String PATH_TABLE="CommonWords";

    public static final int CODE_ALL_ROWS =1;
    public static final int CODE_ID_BASED_ROW = 2;

    public static final Uri  CONTENT_URI = Uri.parse(CONTENT+AUTHORITY +"/"+PATH_TABLE);
    //My data source
    static ArrayList<CommonWords> lstCommonWords = new ArrayList<CommonWords>();

    static {
        int i=0;

        //My data source. from which i will be sending the cursor.
        lstCommonWords.add(new CommonWords(++i,"What"));
        lstCommonWords.add(new CommonWords(++i,"When"));
        lstCommonWords.add(new CommonWords(++i,"Where"));
        lstCommonWords.add(new CommonWords(++i,"Why"));
        lstCommonWords.add(new CommonWords(++i,"this"));
        lstCommonWords.add(new CommonWords(++i,"that"));
        lstCommonWords.add(new CommonWords(++i,"and"));
        lstCommonWords.add(new CommonWords(++i,"is"));
        lstCommonWords.add(new CommonWords(++i,"or"));
        lstCommonWords.add(new CommonWords(++i,"was"));
    }

    public static final String COLUMN_ID="_id";
    public static final String COLUMN_DESC="Description";


    private static final UriMatcher URI_MATCHER_WORDS = new UriMatcher(UriMatcher.NO_MATCH);
    static{

        URI_MATCHER_WORDS.addURI(AUTHORITY,PATH_TABLE,CODE_ALL_ROWS);
        URI_MATCHER_WORDS.addURI(AUTHORITY,PATH_TABLE +"/#",CODE_ID_BASED_ROW);

    }

    //vnd.android.cursor.dir and vnd.android.cursor.item are mimetypes.
    public static final String CONTENT_TYPE = "vnd.android.cursor.dir" + "/" + AUTHORITY + "." +PATH_TABLE;
    public static final String CONTENT_ROW_TYPE ="vnd.android.cursor.item/"+AUTHORITY+"."+PATH_TABLE;



    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        int count =0;
        switch (URI_MATCHER_WORDS.match(uri)){
            case CODE_ID_BASED_ROW:{
                int id = Integer.parseInt(uri.getLastPathSegment());
                if (!(id<0 || id >= lstCommonWords.size())){
                    lstCommonWords.remove(id);
                }
            }break;
            default:  throw new UnsupportedOperationException("Not yet implemented");
        }

        return count;
    }

    @Override
    public String getType(Uri uri) {
        switch (URI_MATCHER_WORDS.match(uri) ){
            case CODE_ALL_ROWS: return CONTENT_TYPE;
            case CODE_ID_BASED_ROW: return CONTENT_ROW_TYPE;
            default:  throw new UnsupportedOperationException("Not yet implemented");
        }

    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri result = null;
        lstCommonWords.add(new CommonWords(lstCommonWords.size()+1,values.getAsString(COLUMN_DESC)));
        result = ContentUris.withAppendedId(uri, lstCommonWords.size() - 1);
        getContext().getContentResolver().notifyChange(uri, null);
        return  result;
    }

    @Override
    public boolean onCreate() {



        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        switch (URI_MATCHER_WORDS.match(uri)){
            case CODE_ALL_ROWS:{
                MatrixCursor cursor= new MatrixCursor(new String[]{COLUMN_ID,COLUMN_DESC});
               // int id = (int) ContentUris.parseId(uri);
                for (int i=0;i<lstCommonWords.size()-1;i++){
                        cursor.addRow( new String[]{ ""+lstCommonWords.get(i).getId(),lstCommonWords.get(i).getDescription()});
                }

                return cursor;
            }
            case CODE_ID_BASED_ROW:{
                MatrixCursor cursor= new MatrixCursor(new String[]{COLUMN_ID,COLUMN_DESC});
                int id = (int) ContentUris.parseId(uri);
                for (int i=0;i<lstCommonWords.size()-1;i++){

                    if (id == lstCommonWords.get(i).getId()){
                        cursor.addRow( new String[]{ ""+lstCommonWords.get(i).getId(),lstCommonWords.get(i).getDescription()});
                    }
                }

                return cursor;
            }
            default: throw new UnsupportedOperationException("Not yet implemented");
        }

    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {

        throw new UnsupportedOperationException("Not yet implemented");
    }
}
