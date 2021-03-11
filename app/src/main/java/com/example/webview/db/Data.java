package com.example.webview.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Data {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String apodo;
    public String url;

    public Data(String apodo, String url) {
        this.apodo = apodo;
        this.url = url;
    }
}
