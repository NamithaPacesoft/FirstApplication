package com.example.user.myapplication.activity.withContentProviders;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;


import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.myapplication.R;
import com.example.user.myapplication.adaptors.array.ContactListArrayAdaptor;
import com.example.user.myapplication.constants.ConstantDetails;
import com.example.user.myapplication.data.ContactDetails;

import java.util.ArrayList;

/**
 * Created by User on 2017-06-09.
 */

public class UserContactViewActivity  extends AppCompatActivity{

    ListView lvContacts;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_contact_list);

        initializeMyComponents();
        //fillTheList();

        fillTheList_CustomData();
    }

    private void initializeMyComponents(){
        lvContacts = (ListView) findViewById(R.id.lv_contacts);

    }

    private  void fillTheList_CustomData(){
        ArrayList<ContactDetails> lstContacts =new ArrayList<ContactDetails>() ;

        getContactsFromPhone(lstContacts);
        ContactListArrayAdaptor lstContactsAdaptor= new ContactListArrayAdaptor(this,lstContacts);

        lvContacts.setAdapter(lstContactsAdaptor);
        lstContactsAdaptor.notifyDataSetChanged();
    }

    private void fillTheList(){

        String strNames[]=getContactsFromPhone();

        ArrayAdapter<String> lstContactsAdaptor = new ArrayAdapter<String>(this,R.layout.layout_contacts_list_items,strNames);

        lvContacts.setAdapter(lstContactsAdaptor);
        lstContactsAdaptor.notifyDataSetChanged();
    }

    private void getContactsFromPhone(ArrayList<ContactDetails> lstContacts){

        ContactDetails objContacts= new ContactDetails() ;
        Cursor cursorContacts=null;
        Cursor cursorPhone =null;
        ContentResolver contentResolver=getContentResolver();

        String strContactID;

        try{
            cursorContacts = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);


            if (cursorContacts.getCount()>0){

                while (cursorContacts.moveToNext()){
                    objContacts = new ContactDetails();

                    strContactID = cursorContacts.getString(cursorContacts.getColumnIndex(ContactsContract.Contacts._ID));
                    objContacts.setName(cursorContacts.getString(cursorContacts.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
                    objContacts.setContactID(strContactID);

                    int hasPhoneNumber = Integer.parseInt(cursorContacts.getString(cursorContacts.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) ;

                    if (hasPhoneNumber>0){
                        cursorPhone = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                                ,null
                                , ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?"
                                , new String[]{strContactID}
                                ,null);

                        int i=0;
                        //image;
                        while(cursorPhone.moveToNext()){
                            String strPhone=   cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                            if (i==0){
                                objContacts.setPhoneNumber(strPhone);
                            }else{
                                objContacts.setMobileNumber(strPhone);
                            }
                            i++;
                        }
                    }
                    lstContacts.add(objContacts);
                }

            }
        }catch (Exception ex){
            Log.e(getClass().getSimpleName(),"Exception thrown while retrieving the detials : " + ex.getMessage());
        }finally {
            if (cursorContacts!=null){
                cursorContacts.close();
            }
            if(cursorPhone !=null){
                cursorPhone.close();
            }
        }


    }

    private String[] getContactsFromPhone(){
        String strNames[] =null ;
        Cursor cursorContacts=null;
        Cursor cursorPhone =null;
        ContentResolver contentResolver=getContentResolver();

        String strContactID;

        try{
            cursorContacts = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,null,null,null,null);


            if (cursorContacts.getCount()>0){
                strNames = new String[cursorContacts.getCount()];
                int i=0;
                while (cursorContacts.moveToNext()){
                    strContactID = cursorContacts.getString(cursorContacts.getColumnIndex(ContactsContract.Contacts._ID));
                    strNames[i] = cursorContacts.getString(cursorContacts.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));


                    int hasPhoneNumber = Integer.parseInt(cursorContacts.getString(cursorContacts.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) ;

                    if (hasPhoneNumber>0){
                       cursorPhone = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                                ,null
                                , ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?"
                                , new String[]{strContactID}
                                ,null);

                        while(cursorPhone.moveToNext()){
                            String strPhone=   cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                            strNames[i] = strNames[i] + "\n" + strPhone;
                        }
                    }

                    i++;
                }

            }
        }catch (Exception ex){
            Log.e(getClass().getSimpleName(),"Exception thrown while retrieving the detials : " + ex.getMessage());
        }finally {
            if (cursorContacts!=null){
                cursorContacts.close();
            }
            if(cursorPhone !=null){
                cursorPhone.close();
            }
        }

        return strNames;
    }
}
