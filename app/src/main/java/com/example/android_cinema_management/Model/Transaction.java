package com.example.android_cinema_management.Model;

import java.util.HashMap;
import java.util.Map;

public class Transaction {

    private String transactionId;
    private String screen;//
    private String movie;//
    private String date;//
    private String time;//
    private Map<String, Object> cinema;//
    private String ticket;//
    private String combo;//
    private String seat;//
    private String quantity;//
    private String price;//
    private String paymentMethod;//
    private String point;//
    private Map<String, Object> user;

    public Transaction() {
    }

    public Transaction(String transactionId, String screen, String movie, String date, String time, Map<String, Object> cinema, String ticket, String combo, String seat, String quantity, String price, String paymentMethod, String point, Map<String, Object> user) {
        this.transactionId = transactionId;
        this.screen = screen;
        this.movie = movie;
        this.date = date;
        this.time = time;
        this.cinema = cinema;
        this.ticket = ticket;
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

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
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

    public String getTicket() {
        return ticket;
    }

    public void setTicketType(String ticketType) {
        this.ticket = ticket;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
                ", screen=" + screen +
                ", movie=" + movie +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", cinema=" + cinema +
                ", ticketType='" + ticket + '\'' +
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
