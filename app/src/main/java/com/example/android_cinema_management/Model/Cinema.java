package com.example.android_cinema_management.Model;

import androidx.annotation.NonNull;

public class Cinema {

    private String name;
    private String address;
    private double latitude;
    private double longitude;
    private double rate;
    private String contactNumber;
    private String imageUrl;
    private String locationName;
    private String cinemaId;
    private int review;
    private String city;
    public Cinema() {
    }

    public Cinema(String cinemaId,String name, String address, Double latitude, Double longitude, Double rate,String contactNumber, String imageUrl, String locationName, int review, String city) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rate = rate;
        this.contactNumber = contactNumber;
        this.imageUrl = imageUrl;
        this.locationName = locationName;
        this.cinemaId = cinemaId;
        this.review = review;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(String cinemaId) {
        this.cinemaId = cinemaId;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @NonNull
    @Override
    public String toString() {
        return "Cinema{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", rate=" + rate +
                ", contactNumber='" + contactNumber + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", locationName='" + locationName + '\'' +
                ", cinemaId='" + cinemaId + '\'' +
                ", review=" + review +
                ", city='" + city + '\'' +
                '}';
    }
}

