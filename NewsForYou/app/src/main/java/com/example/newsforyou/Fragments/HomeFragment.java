package com.example.newsforyou.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsforyou.Adapter.NewsAdapter;
import com.example.newsforyou.Class.News;
import com.example.newsforyou.DashboardActivity;
import com.example.newsforyou.ProfileActivity;
import com.example.newsforyou.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HomeFragment extends Fragment {

    private View mView;
    private ImageView ivProfile;
    private ArrayList<News> newsList;
    private ListView lv_news;
    private NewsAdapter mAdapter;
    private ImageView ivSetting;

    StorageReference storageReference;
    StorageReference avatarRef;

    private static final String TAG = HomeFragment.class.getName();
    private final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("News");

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        lv_news = mView.findViewById(R.id.lv_news);
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

        storageReference = FirebaseStorage.getInstance().getReference();

        avatarRef = storageReference.child("avatar.jpg");
        avatarRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(ivProfile);
            }
        });

        ivProfile = (ImageView) mView.findViewById(R.id.iv_avatar);
        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        ivSetting = (ImageView) mView.findViewById(R.id.iv_setting_home);
        ivSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                //
                //
                //
            }
        });

        return mView;
    }
}
