package com.example.android_cinema_management.Model;

import java.io.Serializable;

public class Discount implements Serializable {
    private String discountId, Name, Month, Content, Image;

    public Discount(){}

    public Discount(String discountId, String name, String month, String content, String image) {
        this.discountId = discountId;
        Name = name;
        Month = month;
        Content = content;
        Image = image;
    }

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "discountId='" + discountId + '\'' +
                ", Name='" + Name + '\'' +
                ", Month='" + Month + '\'' +
                ", Content='" + Content + '\'' +
                ", Image='" + Image + '\'' +
                '}';
    }
}
