package com.example.project3;

//공지 리스트뷰
public class List_Item {
    String title;
    String  time;
    String num;

    public List_Item(String title, String time, String num) {
        this.title = title;
        this.time = time;
        this.num = num;
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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
