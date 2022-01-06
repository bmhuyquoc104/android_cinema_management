package com.example.android_cinema_management.Model;

import java.io.Serializable;

public class Discount {
    private String Name, Month, Content;

    public Discount(){}

    public Discount(String name, String month, String content) {
        this.Name = name;
        this.Month = month;
        this.Content = content;
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
}
