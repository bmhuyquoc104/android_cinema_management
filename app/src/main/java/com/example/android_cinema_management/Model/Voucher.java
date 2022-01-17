package com.example.android_cinema_management.Model;

import java.io.Serializable;
import java.util.HashMap;

public class Voucher implements Serializable {
    private String voucherImage;
    private String voucherName;
    private String voucherPrice;
    private String pointRequire;
    private String voucherId;



    public Voucher(String voucherImage, String voucherName, String voucherPrice, String pointRequire, String voucherId) {
        this.voucherImage = voucherImage;
        this.voucherName = voucherName;
        this.voucherPrice = voucherPrice;
        this.pointRequire = pointRequire;
        this.voucherId = voucherId;
    }

    public String getVoucherImage() {
        return voucherImage;
    }

    public void setVoucherImage(String voucherImage) {
        this.voucherImage = voucherImage;
    }

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName;
    }

    public String getVoucherPrice() {
        return voucherPrice;
    }

    public void setVoucherPrice(String voucherPrice) {
        this.voucherPrice = voucherPrice;
    }

    public String getPointRequire() {
        return pointRequire;
    }

    public void setPointRequire(String pointRequire) {
        this.pointRequire = pointRequire;
    }


    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    @Override
    public String toString() {
        return "Voucher{" +
                "voucherImage='" + voucherImage + '\'' +
                ", voucherName='" + voucherName + '\'' +
                ", voucherPrice='" + voucherPrice + '\'' +
                ", pointRequire='" + pointRequire + '\'' +
                ", voucherId='" + voucherId + '\'' +
                '}';
    }
}
