package com.example.newsforyou.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;
import com.example.newsforyou.Class.News;
import com.example.newsforyou.NewsDetailsActivity;
import com.example.newsforyou.R;

import java.util.List;


public class NewsAdapter extends ArrayAdapter<News> {
    private Context mContext;
    private List<News> mNewsList;
    private SharedPreferences sharedPrefs;

    public NewsAdapter(Context context, List<News> newsList) {
        super(context,R.layout.news_item,newsList);
        mContext = context;
        mNewsList = newsList;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        News currentNews = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.news_item, parent, false);
        }
        ImageView ivAvatar =  convertView.findViewById(R.id.iv_avatar_news_item);
        TextView tvTitle =convertView.findViewById(R.id.tv_history_setting);
        TextView tvDate = convertView.findViewById(R.id.tv_date_news_item);
        TextView tvView = convertView.findViewById(R.id.tv_view_news_item);
        TextView tvLike = convertView.findViewById(R.id.tv_like_news_item);
        TextView tvComment = convertView.findViewById(R.id.tv_comment_news_item);
        ImageView ivView = convertView.findViewById(R.id.iv_view_news_item);
        ImageView ivLike = convertView.findViewById(R.id.iv_like_news_item);
        ImageView ivComment = convertView.findViewById(R.id.iv_comment_news_item);
        tvTitle.setText(currentNews.getTitle());
        tvDate.setText(currentNews.getDate().toString());
        tvView.setText(Integer.toString(currentNews.getViewCount()));
        tvLike.setText(Integer.toString(currentNews.getLikeCount()));
        tvComment.setText(Integer.toString(currentNews.getCommentCount()));
        tvTitle.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, NewsDetailsActivity.class);
                        intent.putExtra("title",currentNews.getTitle());
                        intent.putExtra("author",currentNews.getAuthor());
                        intent.putExtra("content",currentNews.getContent());
                        intent.putExtra("date",currentNews.getDate());
                        mContext.startActivity(intent);
                    }
                }
        );
        Glide.with(mContext)
                .load("https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/Google_News_icon.svg/939px-Google_News_icon.svg.png")
                .signature(new ObjectKey("https://upload.wikimedia.org/wikipedia/commons/thumb/d/da/Google_News_icon.svg/939px-Google_News_icon.svg.png"))
               // .apply(RequestOptions.circleCropTransform()) // Round
                .into(ivAvatar);
        return convertView;
    }
}

