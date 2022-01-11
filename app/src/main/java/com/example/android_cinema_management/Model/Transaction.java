package com.example.android_cinema_management.Model;

import java.util.HashMap;

public class Transaction {

    private String id;
    private String date;
    private String time;
    private String ticketType;
    private int point;
    private int quantity;
    private HashMap<String, Object> userMap;

    public Transaction() {
    }

    public Transaction(String id, String date, String ticketType, int point, int quantity, HashMap<String, Object> userMap) {
        this.id = id;
        this.date = date;
        this.ticketType = ticketType;
        this.point = point;
        this.quantity = quantity;
        this.userMap = userMap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public HashMap<String, Object> getUserMap() {
        return userMap;
    }

    public void setUserMap(HashMap<String, Object> userMap) {
        this.userMap = userMap;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", ticketType='" + ticketType + '\'' +
                ", point=" + point +
                ", quantity=" + quantity +
                ", userMap=" + userMap +
                '}';
    }
}
