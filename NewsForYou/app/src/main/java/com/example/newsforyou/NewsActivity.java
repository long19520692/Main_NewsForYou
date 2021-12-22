package com.example.newsforyou;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        FloatingActionButton heart = findViewById(R.id.heart);
        heart.setOnClickListener(view -> Toast.makeText(MainActivity.this, "Bạn đã thích bài viết này.", Toast.LENGTH_LONG).show());



    }

}