package com.example.android_cinema_management.Model;

import java.io.Serializable;

public class News implements Serializable {
    private String Name, Month, Content, Image;

    public News(){}

    public News(String name, String month, String image, String content) {
        this.Name = name;
        this.Month = month;
        this.Content = content;
        this.Image = image;
    }


    public String getMonth() {
        return Month;
    }

    public void setDate(String month) {
        this.Month = month;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        this.Content = content;
    }

    public String getImage() {
        return Image;
    }
}
