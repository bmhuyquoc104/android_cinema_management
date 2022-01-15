package com.example.android_cinema_management.Model;

import java.io.Serializable;

public class Combo implements Serializable {

    private String comboId;
    private String comboName;
    private String description;
    private String price;
    private String imageURL;

    public Combo() {
    }

    public Combo(String comboId, String comboName, String description, String price, String imageURL) {
        this.comboId = comboId;
        this.comboName = comboName;
        this.description = description;
        this.price = price;
        this.imageURL = imageURL;
    }

    public String getComboId() {
        return comboId;
    }

    public void setComboId(String comboId) {
        this.comboId = comboId;
    }

    public String getComboName() {
        return comboName;
    }

    public void setComboName(String comboName) {
        this.comboName = comboName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Combo{" +
                "comboId='" + comboId + '\'' +
                ", comboName='" + comboName + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
