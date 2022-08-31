package com.example.atomnews;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.atomnews.databinding.ActivityDetailViewBinding;

public class DetailViewActivity extends AppCompatActivity {


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityDetailViewBinding binding;
        super.onCreate(savedInstanceState);
        binding = ActivityDetailViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //to hide status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //getting the link
        String link = getIntent().getStringExtra("link");

        binding.webView.loadUrl(link);
        WebSettings webSettings = binding.webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

//        setContentView(binding.webView);
        Toast.makeText(getApplicationContext(), link, Toast.LENGTH_LONG).show();
    }
}