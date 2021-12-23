package com.example.newsforyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NewsDetailActivity extends AppCompatActivity {

    private ImageButton ibBack;
    private ImageView ivLogo;
    private FloatingActionButton btnHeart;
    private FloatingActionButton btnComment;
    private FloatingActionButton btnReport;
    private FloatingActionButton btnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_news_detail);
        Intent intent = this.getIntent();
        super.onCreate(savedInstanceState);

        initUI();
        initListener();
    }

    private void initUI() {
        ibBack = (ImageButton) findViewById(R.id.ib_back_news_detail);
        ivLogo = (ImageView) findViewById(R.id.iv_logo_news_detail);
        btnHeart = (FloatingActionButton) findViewById(R.id.heart);
        btnComment = (FloatingActionButton) findViewById(R.id.comment);
        btnReport = (FloatingActionButton) findViewById(R.id.bug);
        btnComment = (FloatingActionButton) findViewById(R.id.download);
    }

    private void initListener() {
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}