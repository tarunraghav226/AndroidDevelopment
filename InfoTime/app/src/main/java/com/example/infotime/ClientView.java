package com.example.infotime;

import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ClientView extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String URL){
        view.loadUrl(URL);
        return true;
    }
}
