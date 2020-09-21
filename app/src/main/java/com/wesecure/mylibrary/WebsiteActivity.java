package com.wesecure.mylibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebsiteActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website_acitivity);

        Intent intent = getIntent();
        if (intent != null) {
            String url = intent.getStringExtra("url");

            webView = findViewById(R.id.webView);
            //open url in default browser outside your app
            webView.loadUrl(url);
            // open url in your own app
            webView.setWebViewClient(new WebViewClient());
            // enabling javascript to try to hack it later own
            webView.getSettings().setJavaScriptEnabled(true);
        }

    }

    //allow user to navigate on the site they're visiting not back to your app
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        }else {
            super.onBackPressed();
        }
    }
}