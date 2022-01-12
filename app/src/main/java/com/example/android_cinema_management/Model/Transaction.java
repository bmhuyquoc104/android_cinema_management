package com.example.android_cinema_management.Model;

import java.util.HashMap;
import java.util.Map;

public class Transaction {

    private String transactionId;
    private int screen;
    private String movie;
    private String date;
    private String time;
    private Map<String, Object> cinema;
    private String ticketType;
    private String combo;
    private String seat;
    private int quantity;
    private double price;
    private String paymentMethod;
    private int point;
    private Map<String, Object> user;

    public Transaction() {
    }

    public Transaction(String transactionId, int screen, String movie, String date, String time, Map<String, Object> cinema, String ticketType, String combo, String seat, int quantity, double price, String paymentMethod, int point, Map<String, Object> user) {
        this.transactionId = transactionId;
        this.screen = screen;
        this.movie = movie;
        this.date = date;
        this.time = time;
        this.cinema = cinema;
        this.ticketType = ticketType;
        this.combo = combo;
        this.seat = seat;
        this.quantity = quantity;
        this.price = price;
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

    public int getScreen() {
        return screen;
    }

    public void setScreen(int screen) {
        this.screen = screen;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
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

    public Map<String, Object> getCinema() {
        return cinema;
    }

    public void setCinema(Map<String, Object> cinema) {
        this.cinema = cinema;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getCombo() {
        return combo;
    }

    public void setCombo(String combo) {
        this.combo = combo;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
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
                ", screen=" + screen +
                ", movie=" + movie +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", cinema=" + cinema +
                ", ticketType='" + ticketType + '\'' +
                ", combo='" + combo + '\'' +
                ", seat='" + seat + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", point=" + point +
                ", user=" + user +
                '}';
    }
}
