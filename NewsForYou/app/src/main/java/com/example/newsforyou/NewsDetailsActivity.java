package com.example.newsforyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NewsDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_news_details);
        Intent intent = this.getIntent();
        super.onCreate(savedInstanceState);
        TextView tv = findViewById(R.id.tv_news_detail);
        CharSequence str =    intent.getStringExtra("title")+ "-"+
                intent.getStringExtra("author")+ "-"+
                intent.getStringExtra("date")+ "-"+
                intent.getStringExtra("content");
        if (str != null){
            tv.setText(str);

        }

    }
}