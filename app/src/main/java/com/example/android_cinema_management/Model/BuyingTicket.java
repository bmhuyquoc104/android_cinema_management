package com.example.android_cinema_management.Model;

import java.util.HashMap;

public class BuyingTicket {

    private String id;
    private String date;
    private int seatNumber;
    private int roomNumber;
    private HashMap<String, Object> cinemaMap;
    private HashMap<String, Object> movieMap;

    public BuyingTicket() {
    }

    public BuyingTicket(String id, String date, int seatNumber, int roomNumber, HashMap<String, Object> cinemaMap, HashMap<String, Object> movieMap) {
        this.id = id;
        this.date = date;
        this.seatNumber = seatNumber;
        this.roomNumber = roomNumber;
        this.cinemaMap = cinemaMap;
        this.movieMap = movieMap;
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

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public HashMap<String, Object> getCinemaMap() {
        return cinemaMap;
    }

    public void setCinemaMap(HashMap<String, Object> cinemaMap) {
        this.cinemaMap = cinemaMap;
    }

    public HashMap<String, Object> getMovieMap() {
        return movieMap;
    }

    public void setMovieMap(HashMap<String, Object> movieMap) {
        this.movieMap = movieMap;
    }

    @Override
    public String toString() {
        return "BuyingTicket{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", seatNumber=" + seatNumber +
                ", roomNumber=" + roomNumber +
                ", cinemaMap=" + cinemaMap +
                ", movieMap=" + movieMap +
                '}';
    }
}
