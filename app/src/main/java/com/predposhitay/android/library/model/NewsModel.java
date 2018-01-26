package com.predposhitay.android.library.model;


import java.util.ArrayList;

public class NewsModel {
    String title;
    String shorts;
    String date;
    String image;
    String text;

    public NewsModel(String title, String shorts, String date, String image) {
        this.title = title;
        this.shorts = shorts;
        this.date = date;
        this.image = image;
    }

    public NewsModel() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShorts() {
        return shorts;
    }

    public void setShorts(String shorts) {
        this.shorts = shorts;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
