package com.example.user.myapplication.data;

/**
 * Created by User on 2017-06-19.
 */

public class MessageStatus {

    private String title;
    private boolean isRead;


    public  MessageStatus(String title,boolean isRead){
        this.title=title;
        this.isRead=isRead;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }
}
