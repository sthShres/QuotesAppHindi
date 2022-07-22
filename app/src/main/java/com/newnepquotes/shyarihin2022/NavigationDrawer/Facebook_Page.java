package com.newnepquotes.shyarihin2022.NavigationDrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.newnepquotes.shyarihin2022.R;

public class Facebook_Page extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebook__page);


        webView = (WebView) findViewById(R.id.facebooklink);

        webView.loadUrl("https://www.facebook.com/mvtechgaming");
    }
}