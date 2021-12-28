package com.example.android_cinema_management.Model;

public class Cinema {

    private String name;
    private String address;
    private String latitude;
    private String longitude;
    private String contactNumber;
    private String imageUrl;
    private String locationName;
    private String cinemaId;
    public Cinema() {
    }

    public Cinema(String cinemaId,String name, String address, String latitude, String longitude,String contactNumber, String imageUrl, String locationName) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.contactNumber = contactNumber;
        this.imageUrl = imageUrl;
        this.locationName = locationName;
        this.cinemaId = cinemaId;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
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

    @Override
    public String toString() {
        return "Cinema{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", locationName='" + locationName + '\'' +
                ", cinemaId='" + cinemaId + '\'' +
                '}';
    }
}
