package com.example.android_cinema_management.Model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    private int screenId;
    private int seats;
    private List<String> showTime;
    private String status;
    private ArrayList<Seat> seat;

    public Screen(int screenId, int seats, List<String> showTime, String status, ArrayList<Seat> seat) {
        this.screenId = screenId;
        this.seats = seats;
        this.showTime = showTime;
        this.status = status;
        this.seat = seat;
    }

    public int getScreenId() {
        return screenId;
    }

    public void setScreenId(int screenId) {
        this.screenId = screenId;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public List<String> getShowTime() {
        return showTime;
    }

    public void setShowTime(List<String> showTime) {
        this.showTime = showTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Seat> getSeat() {
        return seat;
    }

    public void setSeat(ArrayList<Seat> seat) {
        this.seat = seat;
    }

    @NonNull
    @Override
    public String toString() {
        return "Screen{" +
                "screenId=" + screenId +
                ", seats=" + seats +
                ", showTime=" + showTime +
                ", status='" + status + '\'' +
                ", seat=" + seat +
                '}';
    }
}
