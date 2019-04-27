package com.example.gogamesystem.bean;

public class News {
    private String title;
    private String date;
    private int img;

    public News(String title, String date, int img) {
        this.title = title;
        this.date = date;
        this.img = img;
    }

    public News() {

    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
