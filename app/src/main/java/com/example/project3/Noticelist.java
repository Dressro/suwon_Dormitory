package com.example.project3;

public class Noticelist {
    String subtitle;
    String title;

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Noticelist(String subtitle, String title) {
        this.subtitle = subtitle;
        this.title = title;
    }
}
