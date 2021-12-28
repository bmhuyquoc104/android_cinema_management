package com.example.android_cinema_management.Model;

public class Discount {
    private String Name, Date;

    public Discount(String name, String date) {
        this.Name = name;
        this.Date = date;
    }


    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }
}
