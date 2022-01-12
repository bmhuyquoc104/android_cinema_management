package com.example.android_cinema_management.Model;

import java.util.HashMap;

public class Transaction {

    private String transactionId;
    private String date;
    private String time;
    private String ticketType;
    private int point;
    private int quantity;
    private HashMap<String, Object> user;

    public Transaction() {
    }

    public Transaction(String transactionId, String date, String time, String ticketType, int point, int quantity, HashMap<String, Object> user) {
        this.transactionId = transactionId;
        this.date = date;
        this.time = time;
        this.ticketType = ticketType;
        this.point = point;
        this.quantity = quantity;
        this.user = user;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public HashMap<String, Object> getUser() {
        return user;
    }

    public void setUser(HashMap<String, Object> user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId='" + transactionId + '\'' +
                ", date='" + date + '\'' +
                ", ticketType='" + ticketType + '\'' +
                ", point=" + point +
                ", quantity=" + quantity +
                ", userMap=" + user +
                '}';
    }
}
