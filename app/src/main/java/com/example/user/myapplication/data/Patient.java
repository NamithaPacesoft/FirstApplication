package com.example.user.myapplication.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by User on 2017-06-28.
 */

public class Patient {

    private Integer patientId;
    private String registeredNumber;
    private String name;
    private String phoneNumber;
    private String email;
    private String profilePictureUrl;
    private String thumbnailUrl;
    private Date dateOfBirth;
    private Date creationDate;
    private Date lastUpdated;
    private boolean isActive;


    public Patient() {
    }

    public Patient(JSONObject jsonObject){
        try{
            patientId = jsonObject.getInt("patientId");
            registeredNumber = jsonObject.optString("registeredNumber","");
            name = jsonObject.optString("name","");
            phoneNumber = jsonObject.optString("phoneNumber","");
            email = jsonObject.optString("email","");
            profilePictureUrl = jsonObject.optString("profilePictureUrl","");
            thumbnailUrl = jsonObject.optString("thumbnailUrl","");
            isActive = jsonObject.optBoolean("active",false);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getRegisteredNumber() {
        return registeredNumber;
    }

    public void setRegisteredNumber(String registeredNumber) {
        this.registeredNumber = registeredNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
