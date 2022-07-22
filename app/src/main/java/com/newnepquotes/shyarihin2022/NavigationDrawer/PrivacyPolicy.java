package com.newnepquotes.shyarihin2022.NavigationDrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.newnepquotes.shyarihin2022.R;

public class PrivacyPolicy extends AppCompatActivity {

    WebView webView;
    public String fileName = "privacy.html";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

        webView = (WebView) findViewById(R.id.privacypolicy);
        //displayoing in webview from htmll fil
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/" + fileName);
    }
}