package com.example.android_cinema_management.Model;


import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ShowTime {
    private String time;
    private String showTimeType;
    private Movie movie;
    private String date;

    public ShowTime(String time, String showTimeType, Movie movie, String date) {
        this.time = time;
        this.showTimeType = showTimeType;
        this.movie = movie;
        this.date = date;
    }

    /**
     * Function to check whether the time is valid or not
     * */
    public void removeInvalidTime(ArrayList<ShowTime>showTimes){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Date time = Calendar.getInstance().getTime();
        String currentTime = df.format(time);
        for (int i = 0; i < showTimes.size() ; i++){
            if (currentTime.compareTo(showTimes.get(i).getTime()) >= 0){
                showTimes.remove(i);
            }
        }
    }

    /**
     * Function to check whether the date is valid or not
     * */
    public void removeInvalidDate(ArrayList<ShowTime>showTimes){
        Date date = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = df.format(date);
        for (int i = 0; i < showTimes.size() ; i++){
            if (currentDate.compareTo(showTimes.get(i).getDate()) > 0){
                showTimes.remove(i);
            }
        }
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getShowTimeType() {
        return showTimeType;
    }

    public void setShowTimeType(String showTimeType) {
        this.showTimeType = showTimeType;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ShowTime{" +
                "time='" + time + '\'' +
                ", showTimeType='" + showTimeType + '\'' +
                ", movie=" + movie +
                ", date='" + date + '\'' +
                '}';
    }
}

