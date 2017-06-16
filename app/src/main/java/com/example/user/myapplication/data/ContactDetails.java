package com.example.user.myapplication.data;

/**
 * Created by User on 2017-06-10.
 */

public class ContactDetails {

    private String strContactID;
    private String strFirstName;
    private String iPhoneNumber;
    private String iMobileNumber;

    public String getName(){
        return strFirstName;
    }

    public void setName( String strName){
        this.strFirstName = strName;
    }

    public String getContactID(){
        return strContactID;
    }

    public void setContactID( String strContactID){
        this.strContactID = strContactID;
    }

    public String getPhoneNumber(){
        return iPhoneNumber;
    }

    public void setPhoneNumber(String number){
        this.iPhoneNumber=number;
    }

    public String getMobileNumber(){
        return iMobileNumber;
    }

    public void setMobileNumber(String number){
        this.iMobileNumber=number;
    }
}
