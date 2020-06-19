package com.example.project3;

import android.widget.Button;

//댓글
public class Comment_list {
    String comment;
    String time;
    Button button;

    public Comment_list(String comment, String time, Button button) {
        this.comment = comment;
        this.time = time;
        this.button = button;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}
