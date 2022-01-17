package com.example.android_cinema_management.Model;

import java.io.Serializable;

public class Discount implements Serializable {
    private String discountId, name, month, content, image;

    public Discount(){}

    public Discount(String discountId, String name, String month, String content, String image) {
        this.discountId = discountId;
        this.name = name;
        this.month = month;
        this.content = content;
        this.image = image;
    }

    public String getDiscountId() {
        return discountId;
    }

    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "discountId='" + discountId + '\'' +
                ", name='" + name + '\'' +
                ", month='" + month + '\'' +
                ", content='" + content + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
