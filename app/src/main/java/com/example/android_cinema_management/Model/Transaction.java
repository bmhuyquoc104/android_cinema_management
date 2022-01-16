package com.example.android_cinema_management.Model;

import java.util.HashMap;
import java.util.Map;

public class Transaction {

    private String transactionId;
    private String movie;//
    private String cinema;//
    private String date;//
    private String time;//
    private String screen;//
    private String ticketType;//
    private String ticketQuantity;//
    private String comboType;//
    private String comboQuantity;//
    private String price;//
    private String seat;//
    private String paymentMethod;//
    private String point;//
    private Map<String, Object> user;

    public Transaction() {
    }

    public Transaction(String transactionId, String movie, String cinema, String date, String time, String screen, String ticketType, String ticketQuantity, String comboType, String comboQuantity, String price, String seat, String paymentMethod, String point, Map<String, Object> user) {
        this.transactionId = transactionId;
        this.movie = movie;
        this.cinema = cinema;
        this.date = date;
        this.time = time;
        this.screen = screen;
        this.ticketType = ticketType;
        this.ticketQuantity = ticketQuantity;
        this.comboType = comboType;
        this.comboQuantity = comboQuantity;
        this.price = price;
        this.seat = seat;
        this.paymentMethod = paymentMethod;
        this.point = point;
        this.user = user;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    public String getCinema() {
        return cinema;
    }

    public void setCinema(String cinema) {
        this.cinema = cinema;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getTicketQuantity() {
        return ticketQuantity;
    }

    public void setTicketQuantity(String ticketQuantity) {
        this.ticketQuantity = ticketQuantity;
    }

    public String getComboType() {
        return comboType;
    }

    public void setComboType(String comboType) {
        this.comboType = comboType;
    }

    public String getComboQuantity() {
        return comboQuantity;
    }

    public void setComboQuantity(String comboQuantity) {
        this.comboQuantity = comboQuantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public Map<String, Object> getUser() {
        return user;
    }

    public void setUser(Map<String, Object> user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", movie='" + movie + '\'' +
                ", cinema='" + cinema + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", screen='" + screen + '\'' +
                ", ticketType='" + ticketType + '\'' +
                ", ticketQuantity='" + ticketQuantity + '\'' +
                ", comboType='" + comboType + '\'' +
                ", comboQuantity='" + comboQuantity + '\'' +
                ", price='" + price + '\'' +
                ", seat='" + seat + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", point='" + point + '\'' +
                ", user=" + user +
                '}';
    }
}
