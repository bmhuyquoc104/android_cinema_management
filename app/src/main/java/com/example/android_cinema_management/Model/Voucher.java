package com.example.android_cinema_management.Model;

import java.io.Serializable;
import java.util.HashMap;

public class Voucher implements Serializable {
    private String Id;
    private String image;
    private String name;
    private String pointRequired;
    private String price;

    public Voucher() {
    }

    public Voucher(String id, String image, String name, String pointRequired, String price) {
        Id = id;
        this.image = image;
        this.name = name;
        this.pointRequired = pointRequired;
        this.price = price;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
  
    public String getPointRequired() {
        return pointRequired;
    }

    public void setPointRequired(String pointRequired) {
        this.pointRequired = pointRequired;
    }
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Voucher{" +
                "Id='" + Id + '\'' +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", pointRequired='" + pointRequired + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
