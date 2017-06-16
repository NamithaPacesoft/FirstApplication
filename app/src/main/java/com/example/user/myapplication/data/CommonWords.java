package com.example.user.myapplication.data;

/**
 * Created by User on 2017-06-12.
 */

public class CommonWords {

    int _id;
    private String description;

    public CommonWords(){
        _id=0;
        description = "";
    }

    public CommonWords(int id, String description){
        this._id=id;
        this.description=description;

    }
    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }





}
