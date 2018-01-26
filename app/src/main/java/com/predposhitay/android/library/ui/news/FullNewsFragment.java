package com.predposhitay.android.library.ui.news;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.predposhitay.android.library.R;


public class FullNewsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = (View) inflater.inflate(R.layout.full_new, container, false);

        savedInstanceState = getArguments();
        if(savedInstanceState != null) {
            String html = savedInstanceState.getString("html");
            int num = html.indexOf("<div");
            String text = html.substring(0,num);
            Log.e("HTML_text", text);
            WebView webViewText = (WebView) view.findViewById(R.id.webViewtext);
            webViewText.loadData(text, "text/html; charset=UTF-8", "ru_RU");
            Log.e("HTML", savedInstanceState.getString("html"));
            WebView webView = (WebView) view.findViewById(R.id.webView);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setUseWideViewPort(true);
            webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
            webView.setScrollbarFadingEnabled(false);
            webView.loadData(savedInstanceState.getString("html"), "text/html; charset=UTF-8", "ru_RU");
        }
        return view;
    }
}
