package com.example.atomnews.model;

public class NewsModel {
    final String des;
    final String newHeading;
    final String date;
    final String category;
    final String newImg;
    final String link;

    public NewsModel(String des, String newHeading, String date, String category, String newImg, String link) {
        this.des = des;
        this.newHeading = newHeading;
        this.date = date;
        this.category = category;
        this.newImg = newImg;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public String getDes() {
        return des;
    }

    public String getNewHeading() {
        return newHeading;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public String getNewsImg() {
        return newImg;
    }


}
