package com.kamel.akra.ui.prayerTime;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;

import android.os.Bundle;

import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;

import com.kamel.akra.R;
import com.kamel.akra.base.BaseActivity;
import com.kamel.akra.databinding.ActivityPrayerTimeBinding;
import com.kamel.akra.ui.home.MainActivity;


public class PrayerTime extends BaseActivity {

    ActivityPrayerTimeBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_prayer_time);
        getSupportActionBar().hide();


        binding.activityRadioBack.setOnClickListener(view -> {
            Intent intent=new Intent(PrayerTime.this, MainActivity.class);
            startActivity(intent);
        });

        binding.prayerWebView.getSettings().setJavaScriptEnabled(true);
        binding.prayerWebView.setWebChromeClient(new WebChromeClient());
        binding.prayerWebView.setWebViewClient(new WebViewClient());
        binding.prayerWebView.loadUrl("https://timesprayer.com/prayer-times-in-cairo.html");


    }


}