package com.example.newsforyou;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NewsDetailActivity extends AppCompatActivity {

    private ImageButton ibBack;
    private ImageView ivLogo;
    private FloatingActionButton btnHeart;
    private FloatingActionButton btnComment;
    private FloatingActionButton btnReport;
    private FloatingActionButton btnDownload;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent = this.getIntent();
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_news_detail);

        initUI();
        initListener();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("News");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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