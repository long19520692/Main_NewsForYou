package com.example.newsforyou.Class;

import java.util.List;

public class Like {
    int id;
    String title;
    String email;

    public Like() {

    }

    public Like(int id, String title, String email) {
        this.id = id;
        this.title = title;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
