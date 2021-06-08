package com.zdrop.paginationwithpagedlib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;
    private WebViewClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_activity);

        webView = findViewById(R.id.web_view);
        client = new WebViewClient(){
            @Override public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.d("ASA", "Url : " + url);
                return false;
            }
        };

        webView.setWebViewClient(client);
        getWebViewSettings();
        loadUrl("http://dev.zdrop.com.bd:11000/member/my-account");
    }

    private void getWebViewSettings() {
        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
//        webSettings.setLoadWithOverviewMode(true);
//        webSettings.setUseWideViewPort(true);
//        webSettings.setBuiltInZoomControls(true);
//        webSettings.setDisplayZoomControls(false);
//        webSettings.setSupportZoom(true);
//        webSettings.setAllowFileAccess(true);
//        webSettings.setAllowContentAccess(true);
//        webSettings.setLoadWithOverviewMode(true);
//        webSettings.setDefaultTextEncodingName("utf-8");
    }


    private void loadUrl(String url){
        webView.loadUrl(url);
    }
}