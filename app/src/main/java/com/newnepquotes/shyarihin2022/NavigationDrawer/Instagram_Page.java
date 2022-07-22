package com.newnepquotes.shyarihin2022.NavigationDrawer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.newnepquotes.shyarihin2022.R;

public class Instagram_Page extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram__page);

        webView = (WebView) findViewById(R.id.instagramlink);

        webView.loadUrl("https://www.instagram.com/mvtech_gaming");
    }
}