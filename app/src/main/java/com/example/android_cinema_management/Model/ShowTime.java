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
     * Function to check whether the showTime is valid or not
     * */
    public void removeInvalidShowTime(ArrayList<ShowTime>showTimes){
        //Get current date time
        Date time = Calendar.getInstance().getTime();
        //Format the date for later comparison
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //Format the time for later comparison
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df2 = new SimpleDateFormat("kk:mm");
        String currentTime = df2.format(time);
        String currentDate = df.format(time);

        //Loop through alll show times and figure out which show time is expired and removed
        for(int i = 0; i < showTimes.size(); i++) {
            if(showTimes.get(i).getDate().compareTo(currentDate) == 0) {
                if (showTimes.get(i).getTime().compareTo(currentTime) >= 0) {
                    System.out.println("keep");
                } else {
                    System.out.println("remove1");
                    //Remove the expired show time
                    showTimes.remove(i);
                }
            }
            else if(showTimes.get(i).getDate().compareTo(currentDate) > 0){
                System.out.println("keep3");

            } else if(showTimes.get(i).getDate().compareTo(currentDate) <0){
                System.out.println("remove2");
                //Remove the expired show time
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

