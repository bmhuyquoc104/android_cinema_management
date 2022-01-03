package com.example.android_cinema_management.Model;

import androidx.annotation.NonNull;

public class Seat {
    private int seatId;
    private boolean isBooked;
    private String price;

    public Seat(int seatId, boolean isBooked, String price) {
        this.seatId = seatId;
        this.isBooked = isBooked;
        this.price = price;
    }

    public int getSeatId() {
        return seatId;
    }

    public void setSeatId(int seatId) {
        this.seatId = seatId;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @NonNull
    @Override
    public String toString() {
        return "Seat{" +
                "seatId=" + seatId +
                ", isBooked=" + isBooked +
                ", price='" + price + '\'' +
                '}';
    }
}
