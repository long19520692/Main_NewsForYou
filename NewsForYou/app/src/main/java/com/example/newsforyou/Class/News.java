package com.example.newsforyou.Class;

import java.time.LocalDateTime;
import java.util.Date;

public class News {
    int id;
    String title;
    String content;
    String author;
    int commentCount;
    int reportCount;
    int viewCount;
    int likeCount;
    String date;

    public News() {
        date = new Date().toString();
    }

    public News(String title, String content, String author,String date) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.commentCount = 0;
        this.reportCount = 0;
        this.viewCount = 0;
        this.likeCount = 0;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getReportCount() {
        return reportCount;
    }

    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", commentCount=" + commentCount +
                ", reportCount=" + reportCount +
                ", viewCount=" + viewCount +
                ", likeCount=" + likeCount +
                ", date='" + date + '\'' +
                '}';
    }
}
