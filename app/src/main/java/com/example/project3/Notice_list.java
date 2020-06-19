package com.example.project3;

public class Notice_list {
    String title;
    String time;

    public Notice_list(String title, String time, String num) {
        this.title = title;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String  getTime() {
        return time;
    }

    public void setTime(String  time) {
        this.time = time;
    }

}
