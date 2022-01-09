package com.example.android_cinema_management.Model;

import androidx.annotation.NonNull;

import java.util.UUID;

public class User {
    private String email;
    private String fullName;
    private String password;
    private String gender;
    private String dateOfBirth;
    private String address;
    private String status;
    private String phone;
    private String role;
    private String id;

    public User() {
    }

    public User(String email, String fullName, String password, String gender, String dateOfBirth, String address, String status, String phone, String role, String id) {
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.status = status;
        this.phone = phone;
        this.role = role;
        this.id = id;
    }

    //Create only 1 admin for the app
    public static User createAdmin(){
        String id = UUID.randomUUID().toString();
        String name = "voquochuy";
        String password = "admin104";
        String email = "s3823236@admin.com";
        String gender = "male";
        String dateOfBirth = "01-04-2000";
        String address = "99 Nguyen Tuan Street, Ward 5, Go Vap District";
        String role = "admin";
        String phone = "0848731007";
        String status = "inactive";
        return new User(email, name, password, gender, dateOfBirth,address,status,phone,role,id);
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", address='" + address + '\'' +
                ", status='" + status + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}