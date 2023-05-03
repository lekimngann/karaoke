package com.example.doann;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.Serializable;

public class Song implements Serializable {
    private int resourceId;
    private String name;
    private String loi;

    public Song(int resourceId, String name, String loi){
        this.resourceId = resourceId;
        this.name = name;
        this.loi = loi;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoi() {
        return loi;
    }

    public void setLoi(String loi) {
        this.loi = loi;
    }
}
