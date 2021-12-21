package com.example.newsforyou.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.newsforyou.Adapter.NewsAdapter;
import com.example.newsforyou.Class.News;
import com.example.newsforyou.DashboardActivity;
import com.example.newsforyou.ProfileActivity;
import com.example.newsforyou.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    private View mView;
    private ImageView ivProfile;
    private ArrayList<News> newsList;
    private ListView lv_news;
    private NewsAdapter mAdapter;
    private static final String TAG = SearchFragment.class.getName();
    private final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("News");
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_search, container, false);
        lv_news = mView.findViewById(R.id.lv_news_search);
        newsList = new ArrayList<>();
        mAdapter = new NewsAdapter(mView.getContext(),newsList);
        databaseReference.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot item : snapshot.getChildren()){
                            News news = item.getValue(News.class);
                            newsList.add(news);
                        }
                        lv_news.setAdapter(mAdapter);


                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                }
        );

        return mView;
    }

}
