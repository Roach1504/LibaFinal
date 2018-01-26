package com.predposhitay.android.library.ui.search;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.predposhitay.android.library.R;

public class Searchfragment extends Fragment {
    private WebView mWebView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.long_fragment, container, false);
        mWebView = (WebView) view.findViewById(R.id.webLong);
        // устанавливаем Zoom control
        mWebView.getSettings().setSupportZoom(true);
        // включаем поддержку JavaScript
        mWebView.getSettings().setJavaScriptEnabled(true);
        // указываем страницу загрузки
        mWebView.loadUrl("http://cbs-ulan-ude.ru/index2.php?option=com_irbis&Itemid=136");
        return view;
    }
}

