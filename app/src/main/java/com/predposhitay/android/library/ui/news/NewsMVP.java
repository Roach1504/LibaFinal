package com.predposhitay.android.library.ui.news;


import android.widget.ImageView;

import com.predposhitay.android.library.model.NewsModel;

import java.util.ArrayList;

public interface NewsMVP {
    void loadNews(ArrayList<NewsModel> newsModels);
    void showMessage(String message);
    void startLoad();
    void stopLoad();
    void startFullNew(String html);

    void imageLoad(ImageView imag, String url);
}
